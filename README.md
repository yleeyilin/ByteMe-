
# AdBlend

AdBlend is an innovative application designed to revolutionize the way content creators integrate sponsored content into their TikTok videos. By utilizing advanced generative AI, AdBlend seamlessly incorporates sponsored images into TikTok videos, making the integration of engaging and monetizable content effortless for creators of all levels.

## Features and Functionality

- **Seamless Sponsored Content Integration:** AdBlend uses generative AI to blend sponsored images into TikTok videos naturally and seamlessly, ensuring that the final content looks professional and engaging.
- **User-Friendly Interface:** Developed with Jetpack Compose, the application offers a modern and intuitive interface that simplifies the content creation process.
- **Real-Time Object Detection:** Leveraging the YOLOv9 object detection model, AdBlend accurately identifies key areas within the video for optimal placement of sponsored content and isolates that area.
- **Enhanced Video Processing:** With the power of OpenCV, AdBlend replaces the identified area with the image of the ad, ensuring the integrated content blends seamlessly with the original footage.

## Installation

1. **Clone this repository:**
  ```sh
  git clone https://github.com/yleeyilin/ByteMe-.git
  ```

2. **Integrate Google Services**
   
   Download the `google-services.json` file from [here](https://drive.google.com/file/d/1ZbnsG7GIpvTawbmIPxsAfMH9zFBeRysB/view?usp=sharing) and copy it into `app/`.

2. **Create a new environment:**
  - Using Conda:
  ```sh
  conda env create -f conda.yml
  conda activate yolov9-deepsort
  ```
  - Using pip:
  ```sh
  python3 -m virtualenv -p python3.11 yolov9-deepsort
  source yolov9-deepsort/bin/activate
  pip install -r requirements.txt
  ```

4. **Download model weights:**
  ```sh
  cd YOLOv9_DeepSORT
  mkdir weights
  wget -P weights https://github.com/WongKinYiu/yolov9/releases/download/v0.1/yolov9-e.pt
  ```

## Usage

1. **Prepare the video file:**
   - Place the video file in the desired location.
   - Update the `video` flag with the path to the video file or set it to `0` to use the webcam as the input.

2. **Download YOLOv9 model:**
   - Ensure the corresponding model weights are available. (YOLOv9-S/YOLOv9-M/[YOLOv9-C](https://github.com/WongKinYiu/yolov9/releases/download/v0.1/yolov9-c.pt)/[YOLOv9-E](https://github.com/WongKinYiu/yolov9/releases/download/v0.1/yolov9-e.pt))

3. **Configure the output video:**
   - Update the `output` flag to specify the path and filename of the output video file.

4. **Set the confidence threshold:**
   - Adjust the `conf` flag to set the confidence threshold for object detection. Objects with confidence below this threshold will be filtered out.

5. **Detect and track certain objects in the video:**
   - Modify the `class_id` flag to specify the class ID for detection. Refer to the `coco.names` file for other options. For example, set it to `0` to track only persons.

6. **Specify the ad image:**
   - Use the `image` flag to provide the path to the advertisement image you want to blend into the video.

7. **Run the code:**
   ```sh
   # Run object tracking and ad blending
   python object_tracking.py --video ./data/test.mp4 --output ./output/output.mp4 --image ./path/to/ad_image.png

   # Run object tracking and ad blending on webcam (set video flag to 0)
   python object_tracking.py --video 0 --output ./output/webcam.mp4 --image ./path/to/ad_image.png

   # Run person tracking and ad blending (set class_id flag to 0 for person)
   python object_tracking.py --video ./data/test.mp4 --output ./output/output.mp4 --class_id 0 --image ./path/to/ad_image.png
   ```

## Acknowledgements

- This code is built upon the YOLOv9 model and the DeepSORT algorithm.
- Credits to the authors and contributors of the respective repositories used in this project.

## References

- [Implementation of paper - YOLOv9: Learning What You Want to Learn Using Programmable Gradient Information](https://github.com/WongKinYiu/yolov9/blob/main/README.md)
- [Simple Online and Realtime Tracking with a Deep Association Metric](https://arxiv.org/abs/1703.07402)


