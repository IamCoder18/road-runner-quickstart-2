package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests.Version2;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Actions.Lift;

@Autonomous
public class RoadrunnerLiftTest extends OpMode {
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
				new Lift(hardwareMap, 10)
		);
	}

	@Override
	public void loop() {

	}
}
