/*
 * Copyright (c) 2020 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.telletubbies;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/*
 * This sample demonstrates a basic (but battle-tested and essentiallypi
 * 100% accurate) method of detecting the skystone when lined up with
 * the sample regions over the first 3 stones.
 */
@Autonomous
public class camera extends LinearOpMode {
    OpenCvInternalCamera phoneCam;
    SkystoneDeterminationPipeline pipeline;

    //Dont change anything below here until you see the next comment
    //private Servo claw;
    private Blinker control_Hub;
    private Blinker expansion_Hub_1;
    private HardwareDevice webcam_1;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo coneExtender;
    private Servo coneGrabber;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private Gyroscope imu;
    private DcMotor viperSlide;

    @Override
    public void runOpMode() {
        //claw = hardwareMap.get(Servo.class, "Claw");
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        expansion_Hub_1 = hardwareMap.get(Blinker.class, "Expansion Hub 1");
//        webcam_1 = hardwareMap.get(HardwareDevice.class, "Webcam 1");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        coneExtender = hardwareMap.get(Servo.class, "coneExtender");
        coneGrabber = hardwareMap.get(Servo.class, "coneGrabber");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        viperSlide = hardwareMap.get(DcMotor.class, "viperSlide");
        viperSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        phoneCam.setPipeline(pipeline);

        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                phoneCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });


        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("fR", frontRight.getCurrentPosition());
            telemetry.addData("fL", frontLeft.getCurrentPosition());
            telemetry.addData("bR", backRight.getCurrentPosition());
            telemetry.addData("bL", backLeft.getCurrentPosition());
            telemetry.addData("avg",pipeline.getAvgY());

            telemetry.update();
        }
    }

    public class SkystoneDeterminationPipeline extends OpenCvPipeline {
            final Scalar RED = new Scalar(255, 0, 0);
            final Scalar BLACK = new Scalar(0, 0, 0);
            final Scalar WHITE = new Scalar(255, 255, 255);

            int position;

            final Point CONE_TOP_LEFT_ANCHOR_POINT = new Point(130, 200);

            Point cone_pointA = new Point(CONE_TOP_LEFT_ANCHOR_POINT.x, CONE_TOP_LEFT_ANCHOR_POINT.y);
            Point cone_pointB = new Point(
                    CONE_TOP_LEFT_ANCHOR_POINT.x + 20, // adding region width
                    CONE_TOP_LEFT_ANCHOR_POINT.y + 20); // adding region height

            Mat region1_Y;
            Mat YCrCb = new Mat();
            Mat Y = new Mat();
            int avgConeY;

            void inputToChannels(Mat input) {
                Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
                Core.extractChannel(YCrCb, Y, 0);
            }

            @Override
            public void init(Mat firstFrame) {
                inputToChannels(firstFrame);
                region1_Y = Y.submat(new Rect(cone_pointA, cone_pointB));
            }

            @Override
            public Mat processFrame(Mat input) {
                inputToChannels(input);
                avgConeY = (int) Core.mean(region1_Y).val[0];

                if (avgConeY < 50) {
                    position = 3;
                    Imgproc.rectangle(input, cone_pointA, cone_pointB, BLACK, -1);
                } else if (avgConeY < 150) {
                    position = 1;
                    Imgproc.rectangle(input, cone_pointA, cone_pointB, RED, -1);
                } else {
                    position = 2;
                    Imgproc.rectangle(input, cone_pointA, cone_pointB, WHITE, -1);
                }
                return input;
            }

            public int getAvgY() {
                return avgConeY;
            }

            public int getPosition() {
                return position;
            }
        }
    }
