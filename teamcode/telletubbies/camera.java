//*package org.firstinspires.ftc.teamcode;// the webcam libraries
//
//import android.icu.text.Transliterator;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.videoio.VideoCapture;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvInternalCamera;
//import org.openftc.easyopencv.OpenCvPipeline;
//
//public class camera extends OpMode {
//    OpenCvCamera webcam;
//    ColorPipeline pipe;
//    VideoCapture capture = new VideoCapture();
//
//    @Override
//    public void init() {
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        pipe = new ColorPipeline();
//        capture.open(0);
//        webcam = new Webcam(capture, cameraMonitorViewId);
//        webcam.setPipeline(pipe);
//        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
//    }
//
//    @Override
//    public void loop() {
//        // do other things
//    }
//
//    @Override
//    public void stop() {
//        webcam.stopStreaming();
//        capture.release();
//    }
//
//    public class ColorPipeline extends OpenCvPipeline {
//        private Mat hsv = new Mat();
//        private Mat threshold = new Mat();
//
//        @Override
//        public Mat processFrame(Mat input) {
//            Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);
//
//            Scalar lowerRed = new Scalar(0, 100, 100);
//            Scalar upperRed = new Scalar(10, 255, 255);
//            Core.inRange(hsv, lowerRed, upperRed, threshold);
//            Imgproc.cvtColor(threshold, input, Imgproc.COLOR_GRAY2RGB);
//            telemetry.addData("red", Core.countNonZero(threshold));
//
//            Scalar lowerBlue = new Scalar(100, 150, 0);
//            Scalar upperBlue = new Scalar(140, 255, 255);
//            Core.inRange(hsv, lowerBlue, upperBlue, threshold);
//            Imgproc.cvtColor(threshold, input, Imgproc.COLOR_GRAY2RGB);
//            telemetry.addData("blue", Core.countNonZero(threshold));
//
//            Scalar lowerGreen = new Scalar(40, 100, 100);
//            Scalar upperGreen = new Scalar(70, 255, 255);
//            Core.inRange(hsv, lowerGreen, upperGreen, threshold);
//            Imgproc.cvtColor(threshold, input, Imgproc.COLOR_GRAY2RGB);
//            telemetry.addData("green", Core.countNonZero(threshold));
//
//            return input;
//        }
//    }
//}
