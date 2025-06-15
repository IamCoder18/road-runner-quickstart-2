package org.firstinspires.ftc.teamcode.Actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
	private final Servo claw;
	private final double target;

	public Claw(HardwareMap hw, double target) {
		this.claw = claw;
		this.target = target;
	}
}
