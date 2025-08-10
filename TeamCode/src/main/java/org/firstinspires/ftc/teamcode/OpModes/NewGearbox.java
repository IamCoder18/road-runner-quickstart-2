package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class NewGearbox extends OpMode {

    DcMotor m1;
    DcMotor m2;
    @Override
    public void init() {
       m1 = hardwareMap.get(DcMotor.class,"shoulder");
       m2 = hardwareMap.get(DcMotor.class,"test");

       m2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        m1.setPower(gamepad1.left_stick_y);
        m2.setPower((gamepad1.left_stick_y));

    }
}
