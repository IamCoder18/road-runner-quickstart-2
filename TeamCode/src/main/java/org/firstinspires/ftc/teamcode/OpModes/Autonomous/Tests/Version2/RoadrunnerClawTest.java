package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests.Version2;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Actions.Claw;
import org.firstinspires.ftc.teamcode.Actions.Wrist;

@Autonomous
public class RoadrunnerClawTest extends OpMode {
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
					new Claw(hardwareMap, 1),
					new SleepAction(3),
					new Wrist(hardwareMap, 1),
					new SleepAction(3),
					new Claw(hardwareMap, 0),
					new SleepAction(3),
					new Wrist(hardwareMap, 0)
				)
		);
	}

	@Override
	public void loop() {

	}
}
