package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Actions.Arm;

@Autonomous
public class RoadrunnerArmTest extends OpMode {
	Arm arm;
	PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0.037, 0, 0.001, 0.001);
	MultipleTelemetry multipleTelemetry;

	@Override
	public void init() {
		multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

		telemetry.addData("Status", "Initialized");
		telemetry.update();

		arm = new Arm(40, pidfCoefficients, hardwareMap);
	}

	@Override
	public void loop() {
		Actions.runBlocking(arm);
	}
}
