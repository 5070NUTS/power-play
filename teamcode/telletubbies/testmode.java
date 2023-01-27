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
package org.firstinspires.ftc.teamcode.telletubbies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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
@TeleOp

public class testmode extends LinearOpMode {
  //  private DcMotor backLeft;
   // private DcMotor backRight;
  //  private DcMotor frontLeft;
   // private DcMotor frontRight;
   // private DcMotor clawMotor;
    private Servo leftClaw;
  //  private Servo rightClaw;
 //   private Servo coneExtender;
//    private Servo coneGrabber;



    @Override
    public void runOpMode() {
     //   backLeft = hardwareMap.get(DcMotor.class, "backLeft");
       // backRight = hardwareMap.get(DcMotor.class, "backRight");
        //frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    //    frontRight = hardwareMap.get(DcMotor.class, "frontRight");
    //    clawMotor = hardwareMap.get(DcMotor.class, "clawMotor");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
    //    rightClaw = hardwareMap.get(Servo.class, "rightClaw");


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            /*
            //Controller inputs (some of them at least)
            double drive = (-1*(gamepad1.left_stick_y));
            double strafe = (gamepad1.left_stick_x);
            double rotate = (gamepad1.right_stick_x);
            double uppy = (gamepad2.left_stick_y);

            //Servo positions for the cone fixer
            final double retractedState = 0.1;
            final double extendedState = 0.1;
            final double enterCone = 0.1;
            final double flipCone = 0.1;

            //Mecanum Drive Code
            double FL = -drive-strafe-rotate;
            double FR = drive-strafe-rotate;
            double BL = -drive+strafe-rotate;
            double BR = drive+strafe-rotate;

            //Set mecanum power to each motor
            backLeft.setPower(BL);
            backRight.setPower(BR);
            frontLeft.setPower(FL);
            frontRight.setPower(FR);
            clawMotor.setPower(uppy);
*/
            //Claw controls
            if (gamepad2.right_bumper) {
                leftClaw.setPosition(.8);
          //      rightClaw.setPosition(.1);
            }
            if (gamepad2.left_bumper) {
                leftClaw.setPosition(.3);
      //          rightClaw.setPosition(.4);
            }
/*
            //Below is the section of code for the cone fixer and corresponding servos
            if (gamepad2.dpad_left) {
                coneExtender.setPosition(extendedState);
            }
            if (gamepad2.dpad_right) {
                coneExtender.setPosition(retractedState);
            }
            if (gamepad2.dpad_up) {
                coneGrabber.setPosition(flipCone);
            }
            if (gamepad2.dpad_down) {
                coneGrabber.setPosition(enterCone);
            }
*/


            telemetry.addData("Servo Position",leftClaw.getPosition());
        //    telemetry.addData("Status", "Running");
        //    telemetry.addData("Cone Fixer", coneGrabber.getPosition());
        //    telemetry.addData("Cone Extender", coneExtender.getPosition());
            telemetry.update();


        }




    }
}