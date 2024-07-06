import os
import cv2
import torch
import numpy as np
from absl import app, flags
from absl.flags import FLAGS
from deep_sort_realtime.deepsort_tracker import DeepSort
from models.common import DetectMultiBackend, AutoShape

# Define command line flags
flags.DEFINE_string('video', None, 'Path to input video or webcam index (0)')
flags.DEFINE_string('output', './output/output.mp4', 'Path to output video')
flags.DEFINE_string('image', None, 'Path to advertisement image')
flags.DEFINE_float('conf', 0.50, 'Confidence threshold')
flags.DEFINE_integer('class_id', None, 'Class ID to track')

# Ensure that required flags are provided
flags.mark_flag_as_required('video')
flags.mark_flag_as_required('image')



def main(_argv):
    video_input = FLAGS.video
    ad_image_path = FLAGS.image

    # Ensure the output directory exists
    output_dir = os.path.dirname(FLAGS.output)
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Initialize videos capture
    if video_input.isdigit():
        video_input = int(video_input)
    cap = cv2.VideoCapture(video_input)
    if not cap.isOpened():
        print('Error: Unable to open videos source.')
        return

    frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    fps = int(cap.get(cv2.CAP_PROP_FPS))

    # Change FourCC code to 'mp4v' for MP4 files or 'avc1' for broader compatibility
    fourcc = cv2.VideoWriter_fourcc(*'mp4v')
    writer = cv2.VideoWriter(FLAGS.output, fourcc, fps, (frame_width, frame_height))

    # Load advertisement image
    ad_image = cv2.imread(ad_image_path, cv2.IMREAD_UNCHANGED)
    if ad_image is None:
        raise ValueError(f"Error: The file {ad_image_path} could not be read.")

    # Initialize the tracker and YOLO model
    tracker = DeepSort(max_age=50)
    device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
    model = DetectMultiBackend(weights='./weights/yolov9-e.pt', device=device, fuse=True)
    model = AutoShape(model)

    # Load class labels
    classes_path = "../configs/coco.names"
    with open(classes_path, "r") as f:
        class_names = f.read().strip().split("\n")
    colors = np.random.randint(0, 255, size=(len(class_names), 3))

    # Main videos processing loop
    while True:
        ret, frame = cap.read()
        if not ret:
            break

        results = model(frame)
        highest_confidence = 0
        best_bbox = None

        for det in results.pred[0]:
            label, confidence, bbox = det[5], det[4], det[:4]
            if confidence < FLAGS.conf:
                continue

            x1, y1, x2, y2 = map(int, bbox)
            class_id = int(label)
            if class_id in [class_names.index('tvmonitor')]:
                if confidence > highest_confidence:
                    highest_confidence = confidence
                    best_bbox = (x1, y1, x2, y2)

        if best_bbox:
            x1, y1, x2, y2 = best_bbox
            # Resize ad image to the bounding box size and blend
            w, h = x2 - x1, y2 - y1
            resized_ad_image = cv2.resize(ad_image, (w, h))
            alpha_s = resized_ad_image[:, :, 2] / 255.0  # Assuming ad image has an alpha channel
            alpha_l = 1.0 - alpha_s
            for c in range(3):
                frame[y1:y2, x1:x2, c] = alpha_s * resized_ad_image[:, :, c] + alpha_l * frame[y1:y2, x1:x2, c]

            # Draw bounding box and label
            # color = colors[class_id]
            # cv2.rectangle(frame, (x1, y1), (x2, y2), color.tolist(), 2)
            # cv2.putText(frame, f"{class_names[class_id]}: {highest_confidence:.2f}", (x1, y1 - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2)

        # Write frame to videos
        writer.write(frame)

        # Display the resulting frame
        cv2.imshow('Video', frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    # Cleanup
    cap.release()
    writer.release()
    cv2.destroyAllWindows()


if __name__ == '__main__':
    app.run(main)
