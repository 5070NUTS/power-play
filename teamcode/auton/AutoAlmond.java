/*
Copyright 2022 FIRST Tech Challenge Team 64201

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
package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

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

public class AutoAlmond extends LinearOpMode {
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor viperSlide;
    private Servo leftClaw;
    private Servo rightClaw;


    double position1;
    double position2;
    double time = 0;


    @Override
    public void runOpMode() {
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        viperSlide = hardwareMap.get(DcMotor.class, "viperSlide");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            sleep(5000);
            frontLeft.setPower(-0.7);
            frontRight.setPower(0.7);
            backLeft.setPower(-0.7);
            backRight.setPower(0.7);
            sleep(659);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            break;

           /* telemetry.addData("Status", "Running");
            telemetry.update();
            if (gamepad1.left_bumper) {
                double current = leftClaw.getPosition();
                leftClaw.setPosition(current + 0.02);
                telemetry.addData("left claw", leftClaw.getPosition());
            }
            if (gamepad1.right_bumper) {
                double current = rightClaw.getPosition();
                leftClaw.setPosition(current + 0.02);
            }*/
//            if(time<5){
//                backLeft.setPower(-.1);
//                backRight.setPower(.1);
//                frontLeft.setPower(-.1);
//                frontRight.setPower(.1);
//
//            }
//
//            else if (time>5){
//                backLeft.setPower(0);
//                backRight.setPower(0);
//                frontLeft.setPower(0);
//                frontRight.setPower(0);
//
//            }
//
//
//            telemetry.addData("Servo Position",leftClaw.getPosition());
//            telemetry.addData("Status", "Running");
//            telemetry.update();
//

        }




    }
}