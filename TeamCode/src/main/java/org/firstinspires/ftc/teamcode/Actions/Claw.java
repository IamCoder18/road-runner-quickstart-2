package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw implements Action {
	private final Servo claw;
	private final double target;

	public Claw(HardwareMap hw, double target) {
		this.claw = hw.get(Servo.class, "claw");
		this.claw.scaleRange(0, 1);

		this.target = target;
	}

	/**
	 * Moves the claw to the target position depending on the targets given in the constructor.
	 * Unfortunately, you can't get the servo positions in the FTC SDK, so you need to add a
	 * short wait after every move to ensure the servo have time to move to its target position.
	 */
	@Override
	public boolean run(@NonNull TelemetryPacket telemetryPacket) {
		this.claw.setPosition(target);
		telemetryPacket.put("Claw Target", target);

		return true;
	}
}
