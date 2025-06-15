package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Actions.Claw;

@Autonomous
public class RoadrunnerClawTest extends OpMode {
	PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0.025, 0, 0, 0.0006);
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
				new Claw(hardwareMap, 1)
		);
	}

	@Override
	public void loop() {

	}
}
