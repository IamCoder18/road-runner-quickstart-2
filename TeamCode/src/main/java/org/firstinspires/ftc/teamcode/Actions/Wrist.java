package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.NewServo;

public class Wrist implements Action {
	private final NewServo wrist;
	private final double target;

	public Wrist(HardwareMap hw, double target) {
		this.wrist = new NewServo(hw,"wrist");
	    this.wrist.setRange(0, 1);

		this.target = target;
	}

	@Override
	public boolean run(@NonNull TelemetryPacket telemetryPacket) {
		this.wrist.moveToPosition(target);
		telemetryPacket.put("Wrist Target", target);

		return wrist.getPos() == target;
	}
}
