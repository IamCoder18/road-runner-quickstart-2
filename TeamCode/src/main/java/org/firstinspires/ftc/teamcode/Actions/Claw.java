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
		this.claw = hw.get(Servo.class, "claw");
		this.wrist = hw.get(Servo.class, "wrist");
		this.target = target;

		this.claw.scaleRange(-1, 1);
		this.wrist.scaleRange(-1, 1);
	}

	/**
	 * Moves the claw and wrist to the target position depending on the targets given in the
	 * constructor. Unfortunately, you can't get the servo positions in the FTC SDK, so you
	 * need to add a short wait after every move to ensure the servos have time to move.
	 */
	@Override
	public boolean run(@NonNull TelemetryPacket telemetryPacket) {
		this.claw.setPosition(target);
		this.wrist.setPosition(target);

		telemetryPacket.put("Claw Target", target);
		telemetryPacket.put("Wrist Target", target);

		return true;
	}
}
