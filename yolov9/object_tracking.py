import os
import cv2
import torch
import numpy as np
from absl import app, flags
from absl.flags import FLAGS
from deep_sort_realtime.deepsort_tracker import DeepSort
from models.common import DetectMultiBackend, AutoShape
from collections import deque

# Command line flags
flags.DEFINE_string('video', None, 'Path to input video or webcam index (0)')
flags.DEFINE_string('output', './output/output.mp4', 'Path to output video')
flags.DEFINE_string('image', None, 'Path to advertisement image')
flags.DEFINE_float('conf', 0.50, 'Confidence threshold')
flags.DEFINE_integer('class_id', None, 'Class ID to track')

# Check that the required flags have been provided
flags.mark_flag_as_required('video')
flags.mark_flag_as_required('image')


class BBoxSmoother:
    def __init__(self, window_size=5):
        self.window_size = window_size
        self.bboxes = deque(maxlen=window_size)

    def update(self, bbox):
        if bbox is not None:
            self.bboxes.append(bbox)
            if len(self.bboxes) == self.bboxes.maxlen:
                # Compute the average bbox
                avg_bbox = np.mean(self.bboxes, axis=0)
                return tuple(int(coord) for coord in avg_bbox)
        return bbox


def adjust_image_colors(ad_image, avg_color):
    # Calculate the average colour of the ad image
    avg_ad_color = np.mean(ad_image[:, :, :3], axis=(0, 1))

    # Calculate the scale factors for each channel to match the average video colour
    scale_factors = avg_color / avg_ad_color

    # Adjust ad image colours
    for c in range(3):
        ad_image[:, :, c] = np.clip(ad_image[:, :, c] * scale_factors[c], 0, 255)
    return ad_image


def calculate_average_video_color(cap):
    total_frames = 0
    sum_color = np.zeros(3)
    while True:
        ret, frame = cap.read()
        if not ret:
            break
        sum_color += np.mean(frame, axis=(0, 1))
        total_frames += 1
    average_color = sum_color / total_frames
    cap.set(cv2.CAP_PROP_POS_FRAMES, 0)  # Reset video to first frame
    return average_color


def main(_argv):
    video_input = FLAGS.video
    ad_image_path = FLAGS.image

    # Ensure the output directory exists
    output_dir = os.path.dirname(FLAGS.output)
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Initialize video capture
    if video_input.isdigit():
        video_input = int(video_input)
    cap = cv2.VideoCapture(video_input)
    if not cap.isOpened():
        print('Error: Unable to open video source.')
        return

    # Calculate the average video color
    average_video_color = calculate_average_video_color(cap)

    # Load advertisement image
    ad_image = cv2.imread(ad_image_path, cv2.IMREAD_UNCHANGED)
    if ad_image is None:
        raise ValueError(f"Error: The file {ad_image_path} could not be read.")

    # Adjust the advertisement image based on the average video color
    adjusted_ad_image = adjust_image_colors(ad_image, average_video_color)

    frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    fps = int(cap.get(cv2.CAP_PROP_FPS))

    # Change FourCC code to 'mp4v' for MP4 files or 'avc1' for broader compatibility
    fourcc = cv2.VideoWriter_fourcc(*'mp4v')
    writer = cv2.VideoWriter(FLAGS.output, fourcc, fps, (frame_width, frame_height))

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

    # Main video processing loop
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
            # Modify available objects as necessary, list of objects can be found in ../configs/coco.names
            if class_id in [class_names.index('tvmonitor')]:
                if confidence > highest_confidence:
                    highest_confidence = confidence
                    best_bbox = (x1, y1, x2, y2)

        bbox_smoother = BBoxSmoother(window_size=5)

        if best_bbox:
            best_bbox = bbox_smoother.update(best_bbox)
            x1, y1, x2, y2 = best_bbox
            shrink_amount = 15  # Shrink the bounding box slightly to show some borders, adjust as necessary
            new_x1 = x1 + shrink_amount
            new_y1 = y1 + shrink_amount
            new_x2 = x2 - shrink_amount
            new_y2 = y2 - shrink_amount

            # Resize the adjusted advertisement image
            w, h = new_x2 - new_x1, new_y2 - new_y1
            resized_ad_image = cv2.resize(adjusted_ad_image, (w, h))

            # Alpha blending
            alpha_s = resized_ad_image[:, :, 2] / 255.0  # Assuming ad image has an alpha channel
            alpha_l = 1.0 - alpha_s
            for c in range(3):
                frame[new_y1:new_y2, new_x1:new_x2, c] = (alpha_s * resized_ad_image[:, :, c] +
                                                          alpha_l * frame[new_y1:new_y2, new_x1:new_x2, c])

        # Write frame to video
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