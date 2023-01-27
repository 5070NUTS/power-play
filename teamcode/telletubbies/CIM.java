package org.firstinspires.ftc.teamcode.telletubbies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class CIM extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor viperSlide;
    private Servo leftClaw;
    private Servo rightClaw;
    private Servo coneExtender;
    private Servo coneGrabber;

    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        viperSlide = hardwareMap.get(DcMotor.class, "viperSlide");
        coneExtender = hardwareMap.get(Servo.class, "coneExtender");
        coneGrabber = hardwareMap.get(Servo.class, "coneGrabber");
        //viperSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ElapsedTime timer = new ElapsedTime();
        //extender = hardwareMap.get(Servo.class, "extender");
        //grabber = hardwareMap.get(Servo.class, "grabber");

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
            telemetry.addData("left stick", gamepad2.left_stick_y);
            telemetry.addData("right_trigger", gamepad2.right_trigger);
            telemetry.addData("a", gamepad2.a);
            telemetry.update();

            if (gamepad2.a) { // lowest
                viperSlide.setTargetPosition(1000);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.8);
            }


            if (gamepad2.y) { // highest, -4500 on viper encoder
                viperSlide.setTargetPosition(-4500);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.8);
            }
            if (gamepad2.x) {
                viperSlide.setTargetPosition(0);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.8);
            }
            if (gamepad2.b) {
                viperSlide.setTargetPosition(0);
                viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                viperSlide.setPower(0.8);
            }

            if (gamepad2.left_trigger > 0.3 || gamepad2.right_trigger > 0.3) {
                //do something idek man
            }

            viperSlide.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            if (extended == 0 && gamepad2.left_stick_button && timer.time() > 0.1) { // close
                coneGrabber.setPosition(0.8);
                sleep(100);
                coneExtender.setPosition(0.8);
                sleep(100);
                coneGrabber.setPosition(0.697);

                extended = 1;


                timer.reset();
            }
            if (extended == 1 && gamepad2.right_stick_button && timer.time() > 0.1) { // open
                coneGrabber.setPosition(0.697);
                sleep(100);
                coneExtender.setPosition(0.13);
                sleep(100);
                coneGrabber.setPosition(0.8);
                timer.reset();

                extended = 0;
            }

            if (extended == 1 && gamepad2.y) {
                coneGrabber.setPosition(0.75);
            }

            if (extended == 1 && gamepad2.a) {
                coneGrabber.setPosition(0.697);
            }

        }
    }
}
