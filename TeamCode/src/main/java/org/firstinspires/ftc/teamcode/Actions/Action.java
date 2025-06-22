package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Action {
	private final Motor liftMotorLeft;
	private final Motor liftMotorRight;
	private final Motor shoulder;
	private final Servo claw;
	private final Servo wrist;
	private final PIDFCoefficients liftCoefficients = new PIDFCoefficients(0, 0, 0, 0.000); // TODO: add the PIDFs back
	private final PIDFController liftController = new PIDFController(liftCoefficients.p, liftCoefficients.i, liftCoefficients.d, liftCoefficients.f);
	private final PIDFCoefficients shoulderCoefficients = new PIDFCoefficients(0.0009, 0, 0.0001, 0.0005);
	private final PIDFController shoulderController = new PIDFController(shoulderCoefficients.p, shoulderCoefficients.i, shoulderCoefficients.d, shoulderCoefficients.f);
	private double clawTarget = 0;
	private double wristTarget = 0;
	private double liftTarget = 0;
	private double shoulderTarget = 0;
	private final MultipleTelemetry telemetry;
	private Timing.Timer holdTimer;

	public Action(HardwareMap hardwareMap, Telemetry originalTelemetry) {
		liftMotorLeft = new Motor(hardwareMap, "liftMotorLeft", Motor.GoBILDA.RPM_117);
		liftMotorRight = new Motor(hardwareMap, "liftMotorRight", Motor.GoBILDA.RPM_117);
		shoulder = new Motor(hardwareMap, "shoulder");
		claw = hardwareMap.get(Servo.class, "claw");
		wrist = hardwareMap.get(Servo.class, "wrist");

		double liftTolerance = 0.5;
		liftController.setTolerance(liftTolerance);
		double shoulderTolerance = 1.8;
		shoulderController.setTolerance(shoulderTolerance);

		liftMotorLeft.setInverted(false);
		liftMotorRight.setInverted(false);
		shoulder.setInverted(true);
		claw.scaleRange(0, 1);
		wrist.scaleRange(0, 1);
		liftMotorLeft.resetEncoder();
		liftMotorRight.resetEncoder();
		shoulder.resetEncoder();
		liftMotorLeft.setRunMode(Motor.RunMode.RawPower);
		liftMotorRight.setRunMode(Motor.RunMode.RawPower);
		shoulder.setRunMode(Motor.RunMode.RawPower);

		telemetry = new MultipleTelemetry(originalTelemetry, FtcDashboard.getInstance().getTelemetry());
	}

	public Lift lift(double inches) {
		double liftTicksPerInch = 1;
		liftTarget = inches * liftTicksPerInch;
		return new Lift();
	}

	public Shoulder shoulder(double degrees) {
		double shoulderTicksPerDegree = 22.755;
		shoulderTarget = degrees * shoulderTicksPerDegree;
		return new Shoulder();
	}

	public Claw claw(double target) {
		clawTarget = target;
		return new Claw();
	}

	public Wrist wrist(double target) {
		wristTarget = target;
		return new Wrist();
	}

	public Hold hold(double waitTime) {
		holdTimer = new Timing.Timer((long) waitTime);
		holdTimer.start();
		return new Hold();
	}

	public class Lift implements com.acmerobotics.roadrunner.Action {
		@Override
		public boolean run(@NonNull TelemetryPacket packet) {
			moveAndHold();

			return !liftController.atSetPoint();
		}
	}

	public class Shoulder implements com.acmerobotics.roadrunner.Action {
		@Override
		public boolean run(@NonNull TelemetryPacket packet) {
			moveAndHold();

			return !shoulderController.atSetPoint();
		}
	}

	public class Hold implements com.acmerobotics.roadrunner.Action {
		@Override
		public boolean run(@NonNull TelemetryPacket packet) {
			moveAndHold();

			return !holdTimer.done();
		}
	}

	private void moveAndHold() {
		double currentLiftPosition = (liftMotorLeft.getCurrentPosition() + liftMotorRight.getCurrentPosition()) / 2;
		double liftPidOutput = liftController.calculate(currentLiftPosition, liftTarget);
		liftMotorLeft.set(liftPidOutput);
		liftMotorRight.set(liftPidOutput);

		double currentShoulderPosition = shoulder.getCurrentPosition();
		double shoulderPidOutput = shoulderController.calculate(currentShoulderPosition, shoulderTarget);
		shoulder.set(shoulderPidOutput);

		telemetry.addData("Lift Target Ticks", liftTarget);
		telemetry.addData("Lift Current Ticks", currentLiftPosition);
		telemetry.addData("Lift Motor Power", liftPidOutput);
		telemetry.addData("Shoulder Target Ticks", shoulderTarget);
		telemetry.addData("Shoulder Current Ticks", currentShoulderPosition);
		telemetry.addData("Shoulder Motor Power", shoulderPidOutput);
		telemetry.update();
	}

	public class Claw implements com.acmerobotics.roadrunner.Action {
		@Override
		public boolean run(@NonNull TelemetryPacket packet) {
			claw.setPosition(clawTarget);
			packet.put("Claw Target", clawTarget);

			return false;
		}
	}

	public class Wrist implements com.acmerobotics.roadrunner.Action {
		@Override
		public boolean run(@NonNull TelemetryPacket packet) {
			wrist.setPosition(wristTarget);
			packet.put("Wrist Target", wristTarget);

			return false;
		}
	}
}
