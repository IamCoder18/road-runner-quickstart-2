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
	private final double targetPositionTicks;
	private final PIDController controller;
	private final DcMotor shoulder;
	private final static double TICKS_PER_DEGREE = (double) 700 / 180;
	private final double feedforwardGain;

	public Arm(double targetPosition, PIDFCoefficients pidfCoefficients, HardwareMap hw) {
		this.targetPositionTicks = targetPosition * TICKS_PER_DEGREE;
		controller = new PIDController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d);
		controller.setTolerance(1.8 * TICKS_PER_DEGREE); // 1.8 degrees
		feedforwardGain = pidfCoefficients.f;

		shoulder = hw.get(DcMotor.class,"shoulder");
		shoulder.setDirection(DcMotorSimple.Direction.REVERSE);
		shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	@Override
	public boolean run(@NonNull TelemetryPacket packet) {
		double currentPosition = shoulder.getCurrentPosition();
		double power = controller.calculate(currentPosition, targetPositionTicks);
		// TODO: See if we can replace with PIDF controller
		double feedforward = Math.cos(targetPositionTicks / TICKS_PER_DEGREE) * feedforwardGain;

		shoulder.setPower(power + feedforward);

		packet.put("Arm Target Ticks", targetPositionTicks);
		packet.put("Arm Current Ticks", currentPosition);
		packet.put("Arm Motor Power", power + feedforward);
		FtcDashboard.getInstance().sendTelemetryPacket(packet);

		return !controller.atSetPoint();
	}
}
