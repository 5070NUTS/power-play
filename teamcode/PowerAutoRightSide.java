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
package org.firstinspires.ftc.teamcode;

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
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@Autonomous

public class PowerAutoRightSide extends LinearOpMode {
    private Servo claw;
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

    public void slide(int POSITION, double POWER) {
        viperSlide.setTargetPosition(POSITION);
        viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlide.setPower(POWER);
    }


    @Override
    public void runOpMode() {
        claw = hardwareMap.get(Servo.class, "Claw");
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        expansion_Hub_1 = hardwareMap.get(Blinker.class, "Expansion Hub 1");
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

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //close claw
            claw.setPosition(0.018);
            //drive forward
            frontLeft.setPower(-.7);
            frontRight.setPower(.7);
            backLeft.setPower(-.7);
            backRight.setPower(.7);
            sleep(1900);
            //slaves lol
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            //strafe
            slide(-4100, 1);
            frontLeft.setPower(.7);
            frontRight.setPower(.7);
            backLeft.setPower(-.7);
            backRight.setPower(-.7);
            sleep(810);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            telemetry.addData("Status", "Running");
            telemetry.update();

            //score
            sleep(1750);
            frontLeft.setPower(-.7);
            frontRight.setPower(.65);
            backLeft.setPower(-.7);
            backRight.setPower(.65);
            sleep(50);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            sleep(3000);
            claw.setPosition(0.08);
            sleep(2000);
            //parking
            frontLeft.setPower(.7);
            frontRight.setPower(-.65);
            backLeft.setPower(.7);
            backRight.setPower(-.65);
            sleep(150);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            slide(0,1);
            sleep(600);

            frontLeft.setPower(.7);
            frontRight.setPower(.7);
            backLeft.setPower(-.7);
            backRight.setPower(-.7);
            sleep(500);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            frontLeft.setPower(.7);
            frontRight.setPower(-.65);
            backLeft.setPower(.7);
            backRight.setPower(-.65);
            sleep(750);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            sleep(5000);


            break;
        }
    }
}

