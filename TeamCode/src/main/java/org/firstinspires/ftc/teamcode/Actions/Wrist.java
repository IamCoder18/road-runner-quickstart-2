package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist implements Action {
	private final Servo wrist;
	private final double target;

	public Wrist(HardwareMap hw, double target) {
		this.wrist = hw.get(Servo.class, "wrist");
		this.wrist.scaleRange(0, 1);

		this.target = target;
	}

	@Override
	public boolean run(@NonNull TelemetryPacket telemetryPacket) {
		this.wrist.setPosition(target);
		telemetryPacket.put("Wrist Target", target);

		return true;
	}
}
