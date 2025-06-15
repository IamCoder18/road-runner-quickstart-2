package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw implements Action {
	private final Servo claw;

	private final Servo wrist;
	private final double target;

	public Claw(HardwareMap hw, double target) {
		claw = hw.get(Servo.class,"claw");
		wrist = hw.get(Servo.class,"wrist");
		this.target = target;
	}

	@Override
	public boolean run(@NonNull TelemetryPacket telemetryPacket) {
		return false;
	}
}
