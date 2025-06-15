package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Reset implements Action {
	private final MotorGroup liftMotor;
	private final Motor shoulder;

	public Reset(HardwareMap hardwareMap) {
		Motor liftMotorLeft = new Motor(hardwareMap, "liftMotorLeft", Motor.GoBILDA.RPM_117);
		Motor liftMotorRight = new Motor(hardwareMap, "liftMotorRight", Motor.GoBILDA.RPM_117);
		liftMotor = new MotorGroup(liftMotorLeft, liftMotorRight);
		shoulder = new Motor(hardwareMap, "shoulder", 2759.12, 60.89);
		Servo claw = hardwareMap.get(Servo.class, "claw");
		Servo wrist = hardwareMap.get(Servo.class, "wrist");

		liftMotor.setInverted(false);
		shoulder.setInverted(true);
		claw.scaleRange(0, 1);
		wrist.scaleRange(0, 1);
	}

	@Override
	public boolean run(@NonNull TelemetryPacket telemetryPacket) {
		liftMotor.resetEncoder();
		shoulder.resetEncoder();
		liftMotor.setRunMode(Motor.RunMode.RawPower);
		shoulder.setRunMode(Motor.RunMode.RawPower);

		return true;
	}
}
