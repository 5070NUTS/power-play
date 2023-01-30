package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class wholeLottaAuto extends LinearOpMode {
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

    //viper slide
    public void slide(int position, double power) {
        viperSlide.setTargetPosition(position);
        viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlide.setPower(power);
    }

    //moving forward
    public void forward(int forward, double power){
        //sets encoders to 0
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sets a target encoder value
        frontLeft.setTargetPosition(forward);
        frontRight.setTargetPosition(forward);
        backLeft.setTargetPosition(forward);
        backRight.setTargetPosition(forward);
        //goes to target position
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //gives motors power (0-1)
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    //moving back
    public void back(int back, double power){
        //sets encoders to 0
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sets a target encoder value
        frontLeft.setTargetPosition(back);
        frontRight.setTargetPosition(back);
        backLeft.setTargetPosition(back);
        backRight.setTargetPosition(back);
        //goes to target position
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //gives motors power (0-1)
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }
    public void strafeLeft(int strafeLeft, double power){
        //sets encoders to 0
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sets a target encoder value
        frontLeft.setTargetPosition(strafeLeft);
        frontRight.setTargetPosition(strafeLeft);
        backLeft.setTargetPosition(strafeLeft);
        backRight.setTargetPosition(strafeLeft);
        //goes to target position
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //gives motors power (0-1)
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    //strafing left
    public void strafeRight(int strafeRight, double power){
        //sets encoders to 0
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sets a target encoder value
        frontLeft.setTargetPosition(strafeRight);
        frontRight.setTargetPosition(strafeRight);
        backLeft.setTargetPosition(strafeRight);
        backRight.setTargetPosition(strafeRight);
        //goes to target position
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //gives motors power (0-1)
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }



    @Override
    public void runOpMode(){
        waitForStart();
        while (opModeIsActive()){

        }
    }
}
