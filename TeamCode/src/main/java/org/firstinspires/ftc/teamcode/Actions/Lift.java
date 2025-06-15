package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Lift implements Action {
	private final double targetPositionTicks;
	private final PIDFController controller;
	private final DcMotor liftMotor;
	private final static double TICKS_PER_INCH = 100;

	public Lift(double targetPosition, PIDFCoefficients pidfCoefficients, HardwareMap hardwareMap) {
		this.targetPositionTicks = targetPosition * TICKS_PER_INCH;
		this.controller = new PIDFController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f);
		this.controller.setTolerance(0.5 * TICKS_PER_INCH); // 0.5 inches

		liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
		liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	@Override
	public boolean run(@NonNull TelemetryPacket packet) {
		double currentPosition = liftMotor.getCurrentPosition();
		double pidOutput = controller.calculate(currentPosition, targetPositionTicks);

		liftMotor.setPower(pidOutput);

		packet.put("Lift Target Ticks", targetPositionTicks);
		packet.put("Lift Current Ticks", currentPosition);
		packet.put("Lift Motor Power", pidOutput);
		FtcDashboard.getInstance().sendTelemetryPacket(packet);

		return !controller.atSetPoint();
	}
}