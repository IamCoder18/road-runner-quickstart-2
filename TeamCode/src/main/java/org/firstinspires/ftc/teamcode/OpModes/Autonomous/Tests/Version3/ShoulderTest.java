package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests.Version3;

import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.Actions.Action;

@Autonomous
public class ShoulderTest extends OpMode {
	private Action action;

	@Override
	public void init() {
		this.action = new Action(hardwareMap, telemetry);
	}

	@Override
	public void start() {
		Actions.runBlocking(
				new SequentialAction(
					action.shoulder(30),
					action.hold(1),
					action.shoulder(15)
				)
		);
	}

	@Override
	public void loop() {

	}
}
