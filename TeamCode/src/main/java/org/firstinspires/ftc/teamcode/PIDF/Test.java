package org.firstinspires.ftc.teamcode.PIDF;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class Test extends OpMode {
	@Override
	public void init() {
		DcMotor arm = hardwareMap.get(DcMotor.class, "shoulder");
		arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		arm.setPower(0.2);
	}

	@Override
	public void loop() {}
}
