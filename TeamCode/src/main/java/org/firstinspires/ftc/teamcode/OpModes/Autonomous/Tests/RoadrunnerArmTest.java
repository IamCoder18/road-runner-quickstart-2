package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Actions.Arm;

@Autonomous
public class RoadrunnerArmTest extends OpMode {
	PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0.037, 0, 0, 0.001);
	MultipleTelemetry multipleTelemetry;

	@Override
	public void init() {
		multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

		telemetry.addData("Status", "Initialized");
		telemetry.update();
	}

	@Override
	public void start(){
		Actions.runBlocking(
				new SequentialAction(
						new SleepAction(3),
						new Arm(300, pidfCoefficients, hardwareMap),
						new SleepAction(3),
						new Arm(1000, pidfCoefficients, hardwareMap),
						new SleepAction(3),
						new Arm(300,pidfCoefficients,hardwareMap),
						new SleepAction(2)
				)
		);
	}

	@Override
	public void loop() {

	}
}
