/*
Copyright 2023 FIRST Tech Challenge Team 5070

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode.telletubbies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * to prevent this OpMode from being added to the Driver Station
 */
@Autonomous

public class WhyAutonomous extends LinearOpMode {
    private Servo claw;
    private HardwareDevice webcam_1;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo coneExtender;
    private Servo coneGrabber;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private Gyroscope imu;
    private DcMotor viperSlide;

    public void slide(int POSITION, double POWER) {
        viperSlide.setTargetPosition(POSITION);
        viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlide.setPower(POWER);
    }

    public void FLdrive(int POSITION, double POWER) {
        frontLeft.setTargetPosition(POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setPower(POWER);
    }
    public void FRdrive(int POSITION, double POWER) {
        frontRight.setTargetPosition(POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setPower(POWER);
    }
    public void BLdrive(int POSITION, double POWER) {
        backLeft.setTargetPosition(POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setPower(POWER);
    }
    public void BRdrive(int POSITION, double POWER) {
        backRight.setTargetPosition(POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setPower(POWER);
    }


    public void forward(int forward, double power) {
        int flTarget = frontLeft.getCurrentPosition() + forward;
        int frTarget = frontRight.getCurrentPosition() + forward;
        int blTarget = backLeft.getCurrentPosition() + forward;
        int brTarget = backRight.getCurrentPosition() + forward;

        frontLeft.setTargetPosition(flTarget);
        frontRight.setTargetPosition(frTarget);
        backLeft.setTargetPosition(blTarget);
        backRight.setTargetPosition(brTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        resetRuntime();
        frontLeft.setPower((power));
        backLeft.setPower((power));
        frontRight.setPower((power));
        backRight.setPower((power));

        while (opModeIsActive() && (frontRight.isBusy() || frontLeft.isBusy() || backRight.isBusy() || backLeft.isBusy())) {
            telemetry.addData("fl",  frontLeft.getTargetPosition());
            telemetry.addData("flp",  frontLeft.getCurrentPosition());
            telemetry.addData("pp2",  frontLeft.getPower());
            telemetry.update();
        }

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        telemetry.addData("wtf",  "KILLING MYSELF");
    }

    public void setPower(DcMotor m, double p) {
        m.setPower(-p);
    }

    @Override
    public void runOpMode() {
        claw = hardwareMap.get(Servo.class, "Claw");
        webcam_1 = hardwareMap.get(HardwareDevice.class, "Webcam 1");
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

        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //close claw
            claw.setPosition(0.018);
            telemetry.addData("frontLeft",frontLeft.getCurrentPosition());
            telemetry.addData("frontRight",frontRight.getCurrentPosition());
            telemetry.addData("backLeft",backLeft.getCurrentPosition());
            telemetry.addData("backRight",backRight.getCurrentPosition());
            telemetry.update();

            forward(3000, 0.3);

//            //drive forward
//            frontLeft.setPower(-.65);
//            frontRight.setPower(.7);
//            backLeft.setPower(-.65);
//            backRight.setPower(.7);
//            sleep(1865);
//            //slaves lol
//            frontLeft.setPower(0);
//            frontRight.setPower(0);
//            backLeft.setPower(0);
//            backRight.setPower(0);
//
//            //strafe
//            slide(-4100, 1);
//            frontLeft.setPower(-.7);
//            frontRight.setPower(-.7);
//            backLeft.setPower(.7);
//            backRight.setPower(.7);
//            sleep(810);
//            frontLeft.setPower(0);
//            frontRight.setPower(0);
//            backLeft.setPower(0);
//            backRight.setPower(0);
//            telemetry.addData("Status", "Running");
//            telemetry.update();
//
//            //score
//            sleep(1750);
//            frontLeft.setPower(-.65);
//            frontRight.setPower(.7);
//            backLeft.setPower(-.65);
//            backRight.setPower(.7);
//            sleep(150);
//            frontLeft.setPower(0);
//            frontRight.setPower(0);
//            backLeft.setPower(0);
//            backRight.setPower(0);
//            sleep(1050);
//            claw.setPosition(0.08);
//
//            //parking
//            frontLeft.setPower(.65);
//            frontRight.setPower(-.7);
//            backLeft.setPower(.65);
//            backRight.setPower(-.7);
//            sleep(150);
//            frontLeft.setPower(0);
//            frontRight.setPower(0);
//            backLeft.setPower(0);
//            backRight.setPower(0);
//            slide(0,1);
//            sleep(600);
//
//            frontLeft.setPower(-.7);
//            frontRight.setPower(-.7);
//            backLeft.setPower(.7);
//            backRight.setPower(.7);
//            sleep(500);
//            frontLeft.setPower(0);
//            frontRight.setPower(0);
//            backLeft.setPower(0);
//            backRight.setPower(0);
//
//            frontLeft.setPower(.65);
//            frontRight.setPower(-.7);
//            backLeft.setPower(.65);
//            backRight.setPower(-.7);
//            sleep(750);
//            frontLeft.setPower(0);
//            frontRight.setPower(0);
//            backLeft.setPower(0);
//            backRight.setPower(0);
//            sleep(5000);
        }
    }
}

