package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm implements Action {
	private final double target;
	private final PIDController controller;
	private final DcMotor shoulder;
	private final static double ticksPerDegree = (double) 700 /180;
	private final double f;
	private final MultipleTelemetry multipleTelemetry;

	public Arm(double target, PIDFCoefficients pidfCoefficients, HardwareMap hw, Telemetry telemetry) {
		this.target = target;
		controller = new PIDController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d);
		controller.setTolerance(1.8 * ticksPerDegree);
		f = pidfCoefficients.f;
		multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
		shoulder = hw.get(DcMotor.class,"shoulder");
		shoulder.setDirection(DcMotorSimple.Direction.REVERSE);
	}

	@Override
	public boolean run(@NonNull TelemetryPacket packet) {
		double currentPosition = shoulder.getCurrentPosition();
		double power = controller.calculate(currentPosition, target);
		double feedforward = Math.cos(target/ticksPerDegree) * f;
		shoulder.setPower(power + feedforward);

		multipleTelemetry.addData("Target", target);
		multipleTelemetry.addData("Current Position", currentPosition);
		multipleTelemetry.addData("Motor Power", power + feedforward);
		multipleTelemetry.update();

		// Return false to end action, true to continue
		return controller.atSetPoint();
	}
}
