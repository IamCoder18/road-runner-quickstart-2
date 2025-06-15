package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Arm implements Action {
	private final double target;
	private final PIDController controller;
	public final DcMotor shoulder;
	private final static double ticksPerDegree = (double) 1;
	private final double f;

	public Arm(double target, PIDFCoefficients pidfCoefficients, HardwareMap hw) {
		this.target = target * ticksPerDegree;
		controller = new PIDController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d);
		controller.setTolerance(1.8 * ticksPerDegree);
		f = pidfCoefficients.f;
		shoulder = hw.get(DcMotor.class,"shoulder");
		shoulder.setDirection(DcMotorSimple.Direction.REVERSE);
		shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	@Override
	public boolean run(@NonNull TelemetryPacket packet) {
		double currentPosition = shoulder.getCurrentPosition();
		double power = controller.calculate(currentPosition, target);
		double feedforward = Math.cos(target/ticksPerDegree) * f;
		shoulder.setPower(power + feedforward);

		packet.put("Target", target);
		packet.put("Current Position", currentPosition);
		packet.put("Motor Power", power + feedforward);
		FtcDashboard.getInstance().sendTelemetryPacket(packet);

		return !controller.atSetPoint();
	}
}
