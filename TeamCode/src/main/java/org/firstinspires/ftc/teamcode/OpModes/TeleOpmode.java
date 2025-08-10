package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp
public class TeleOpmode extends OpMode {

    Servo Arm1;
    Servo Arm2;
    Servo claw;
    Servo wrist;

    DcMotor LiftMotorRight;
    DcMotor LiftMotorLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;

     double target = 1;

      public TelemetryPacket telemetry;
    @Override
    public void init() {
        Arm1 = hardwareMap.get(Servo.class,"Arm1");
        Arm2 = hardwareMap.get(Servo.class,"Arm2");
        claw = hardwareMap.get(Servo.class,"claw");
        wrist = hardwareMap.get(Servo.class, "wrist");

        LiftMotorRight = hardwareMap.get(DcMotor.class, "liftMotorRight");
        LiftMotorLeft = hardwareMap.get(DcMotor.class, "liftMotorLeft");

        LiftMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);

         frontLeft = hardwareMap.get(DcMotor.class, "leftFront");
         frontRight = hardwareMap.get(DcMotor.class, "rightFront");
         rearLeft = hardwareMap.get(DcMotor.class, "leftRear");
         rearRight = hardwareMap.get(DcMotor.class, "rightRear");

         frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
         rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

         telemetry = new TelemetryPacket(false);


    }

    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeft.setPower(frontLeftPower);
        rearLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        rearRight.setPower(backRightPower);


        if (gamepad2.right_stick_y != 0) {
            LiftMotorRight.setPower(gamepad2.right_stick_y);
            LiftMotorLeft.setPower(gamepad2.right_stick_y);

            if ((LiftMotorLeft.getCurrentPosition() + LiftMotorRight.getCurrentPosition())/2 >= 2000){
                LiftMotorLeft.setTargetPosition(2000);
                LiftMotorRight.setTargetPosition(2000);
            }
        }

        wrist.setPosition(gamepad2.right_trigger);

        if (gamepad2.a){
            claw.setPosition(0);
        }else{claw.setPosition(1);}

//        if (target <= 1 && target >= 0){
//            target += -gamepad2.left_stick_y / 500;
//        }

        target += -gamepad2.left_stick_y / 500;
        if (target < 0){
            target = 0;
        } else if (target > 1) {
            target = 1;
        }

        if(gamepad2.dpad_up){
            target = 1;
        }
        if(gamepad2.dpad_down){
            target = 0.45;
        }
        if(gamepad2.dpad_left){
            target = 0.5;
        }
        Arm1.setPosition(target);
        Arm2.setPosition(target);

        telemetry.put("arm pos", Arm1.getPosition());
        telemetry.put("claw", claw.getPosition());
        telemetry.put("Wrist", wrist.getPosition());
       // telemetry.update();




    }
}
