package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class ServoArm extends OpMode {

    Telemetry telemetry;

    Servo Arm1;
    Servo Arm2;

    DcMotor LiftMotorRight;
    DcMotor LiftMotorLeft;

     private double target = 0;
    @Override
    public void init() {
        Arm1 = hardwareMap.get(Servo.class,"Arm1");
        Arm2 = hardwareMap.get(Servo.class,"Arm2");

        LiftMotorRight = hardwareMap.get(DcMotor.class, "liftMotorRight");
        LiftMotorLeft = hardwareMap.get(DcMotor.class, "liftMotorLeft");
        LiftMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LiftMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Arm2.setDirection(Servo.Direction.REVERSE);
        LiftMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        target = 0;
    }

    public void loop() {

        if (target <= 1 && target >= 0){
            target += -gamepad1.left_stick_y / 500;
        }
        if (target < 0){
            target = 0;
        } else if (target > 1) {
            target = 1;
        }


        if(gamepad1.dpad_up){
            target = 1;
        }
        if(gamepad1.dpad_down){
            target = 0;
        }
        if(gamepad1.dpad_left){
            target = 0.5;
        }


        Arm1.setPosition(target);
        Arm2.setPosition(target);

        LiftMotorRight.setPower(gamepad1.right_stick_y);
        LiftMotorLeft.setPower(gamepad1.right_stick_y);

        telemetry.addData("lift height",(LiftMotorLeft.getCurrentPosition() + LiftMotorRight.getCurrentPosition())/2);
        telemetry.update();
        }
}