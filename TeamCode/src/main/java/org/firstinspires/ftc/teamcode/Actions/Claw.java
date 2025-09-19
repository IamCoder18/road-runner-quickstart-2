package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.NewServo;

import java.sql.Time;

public class Claw implements Action {
	private final NewServo claw;
	private final double target;

	private ServoController controller;
	private java.sql.Time time = new java.sql.Time(0);

	public Claw(HardwareMap hw, double target) {
		this.claw = new NewServo(hw,"claw");
	    this.claw.setRange(0, 1);
//		this.time = timer;
		this.target = target;
	}

	/**
	 * Moves the claw to the target position depending on the targets given in the constructor.
	 * Unfortunately, you can't get the servo positions in the FTC SDK, so you need to add a
	 * short wait after every move to ensure the servo have time to move to its target position.
	 */
	@Override
	public boolean run(@NonNull TelemetryPacket telemetryPacket) {

		this.claw.moveToPosition(target);
		telemetryPacket.put("Claw Target", target);


		return claw.getPos() == target;
	}
}
