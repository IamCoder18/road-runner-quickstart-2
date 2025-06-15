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
	MultipleTelemetry multipleTelemetry;
	SequentialAction currentAction;

	Boolean runOnce = true;

	@Override
	public void init() {
		multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
		telemetry.addData("Status", "Initialized");
		telemetry.update();
	}

	@Override
	public void start(){

	}

	@Override
	public void loop() {

		//case 1: Full Loop, No Sleep
//		setAction(1);
//		Actions.runBlocking(currentAction);

		/*
		//case 2: Full Loop with Sleep
		setAction(2);
		Actions.runBlocking(currentAction);
		 */


		//case 3: Loop once without Sleep
		if(runOnce)
		{
			setAction(1);
			Actions.runBlocking(currentAction);
			runOnce = false;
		}


		/*
		//case 4: Loop once with Sleep
		if(runOnce)
		{
			setAction(2);
			Actions.runBlocking(currentAction);
			runOnce = false;
		}
		 */
	}

	void setAction(int id)
	{
		if(id == 1)
		{
			currentAction =  new SequentialAction(
					new Arm(hardwareMap, 300),
//					new Arm(hardwareMap, 500),
					new Arm(hardwareMap, 800)
			);
		}
		else if(id == 2)
		{
			currentAction =  new SequentialAction(
					new Arm(hardwareMap, 300),
					new Arm(hardwareMap, 500),
					new SleepAction(3),
					new Arm(hardwareMap, 800)
			);
		}
	}
}
