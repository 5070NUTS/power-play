package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class TwoCim extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor viperSlide;
    private Servo Claw;
    private Servo coneExtender;
    private Servo coneGrabber;

    public void slide(int POSITION, double POWER) {
        viperSlide.setTargetPosition(POSITION);
        viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlide.setPower(POWER);
    }

    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        viperSlide = hardwareMap.get(DcMotor.class, "viperSlide");
        coneExtender = hardwareMap.get(Servo.class, "coneExtender");
        coneGrabber = hardwareMap.get(Servo.class, "coneGrabber");
        Claw = hardwareMap.get(Servo.class, "Claw");
        viperSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ElapsedTime timer = new ElapsedTime();

        int level = 0;
        int topconestackcone = -639;
        int conestackcone = topconestackcone;
        final int coneheight = -171;
        int viperPosition = 0;

        //braking
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viperSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int extended = 0;               //This tells the robot that the cone grabber is folded
        // driver = gamepad1
        // operator = gamepad 2

        waitForStart();
        //     viperSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()) {
            //driving
//            frontLeft.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.right_trigger - gamepad1.left_trigger);
//            frontLeft.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.right_trigger + gamepad1.left_trigger);
//            backLeft.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.right_trigger + gamepad1.left_trigger);
//            backRight.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x);

            double drive = (-1*(gamepad1.left_stick_y));
            double strafe = (gamepad1.left_stick_x);
            double rotate = (gamepad1.right_stick_x);



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

            telemetry.addData("viper", viperSlide.getCurrentPosition());
            telemetry.addData("cone extender", coneExtender.getPosition());
            telemetry.addData("cone grabber", coneGrabber.getPosition());
            telemetry.addData("dpad down", gamepad2.dpad_down);
            telemetry.addData("dpad up", gamepad2.dpad_up);
            telemetry.update();


            if (gamepad2.y) {
                slide(-4100, 1);
            }
            if (gamepad2.x) {
                slide(-2950, 1);
                ;
            }
            if (gamepad2.b) {
                slide(-1800, 1);
            }
            if (gamepad2.a && extended == 0) {
                slide(0, 1);
            }
            if (gamepad2.a && extended == 1) {
                slide(-400, 1);
            }

            if (gamepad1.start) {
                conestackcone = topconestackcone;
                viperSlide.setTargetPosition(conestackcone);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.8);
            }
            if(gamepad1.back) {
                conestackcone = conestackcone-coneheight;
                viperSlide.setTargetPosition(conestackcone);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.8);
            }

            viperPosition = viperSlide.getCurrentPosition();

            if (extended == 0 && gamepad1.left_stick_button) { // Checks that the mechanism is retracted
                if (viperPosition > -900) { //Checks height; if too low then move up and out of the way

                    slide(-1000, 1);      //Move slide up and out of the way
                    Claw.setPosition(0.09);              //Open Claw
                    sleep(500);                //Wait
                    coneGrabber.setPosition(0.8);         //Failsafe, move to default position
                    sleep(100);                 //Wait
                    coneExtender.setPosition(0.8);         //Begin extend
                    sleep(100);                  //Wait
                    coneGrabber.setPosition(0.695);        //Unfold
                    sleep(1000);                 //Wait 1 second
                    slide(-400, 1);         //Move slide down to level
                    extended = 1;                          //Tell system that it's extended now
                }
                else if (viperPosition <-900) { //If height is ok then don't move out of the way, just wait a bit
                    Claw.setPosition(0.08);
                    sleep(100);
                    coneGrabber.setPosition(0.8);
                    sleep(100);
                    coneExtender.setPosition(0.8);
                    sleep(100);
                    coneGrabber.setPosition(0.695);
                    slide(-400, 1);
                    extended = 1;
                }
            }
            if (extended == 1 && gamepad1.right_stick_button) { // open
                if (viperPosition > -900) {
                    slide(-1000, 1);
                    sleep(500);
                    coneGrabber.setPosition(0.695);
                    sleep(100);
                    coneExtender.setPosition(0.13);
                    sleep(100);
                    coneGrabber.setPosition(0.8);
                    extended = 0;
                }
                else if (viperPosition < -900) {
                    coneGrabber.setPosition(0.695);
                    sleep(100);
                    coneExtender.setPosition(0.13);
                    sleep(100);
                    coneGrabber.setPosition(0.8);
                    extended = 0;
                }
            }

            if (extended == 1 && gamepad1.dpad_up) {
                coneGrabber.setPosition(0.75);
            }

            if (gamepad2.dpad_up && extended != 1) {
                int curpos = viperSlide.getCurrentPosition();
                curpos += 10;
                viperSlide.setTargetPosition(curpos);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.5);
            }
            if (gamepad2.dpad_down && extended != 1) {
                int curpos = viperSlide.getCurrentPosition();
                curpos -= 10;
                viperSlide.setTargetPosition(curpos);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.5);
            }

            if (extended == 1 && gamepad1.dpad_down) {
                coneGrabber.setPosition(0.695);
            }
            if(gamepad2.right_bumper || gamepad1.right_bumper) {
                Claw.setPosition(0.018);
            }
            if(gamepad2.left_bumper|| gamepad1.left_bumper) {
                Claw.setPosition(0.08);
            }
        }
    }
}