package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests.Version2;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.Actions.Claw;
import org.firstinspires.ftc.teamcode.Actions.ServoArm;
import org.firstinspires.ftc.teamcode.Actions.Wrist;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.sql.Time;

@Autonomous
public class RoadrunnerClawTest extends OpMode {
	MultipleTelemetry multipleTelemetry;

	Pose2d beginPose;
	MecanumDrive drive;

	@Override
	public void init() {
		multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

		telemetry.addData("Status", "Initialized");
		telemetry.update();
		beginPose = new Pose2d(0, 0, Math.toRadians(0));
		drive = new MecanumDrive(hardwareMap, beginPose);
	}

	@Override
	public void start(){
		Actions.runBlocking(
				new SequentialAction(
						drive.actionBuilder(beginPose)
								.strafeTo(new Vector2d(24, 0))
								.build(),
					new ServoArm(hardwareMap,0.5),
					
					new Claw(hardwareMap,1),
					//new SleepAction(3),
					new Wrist(hardwareMap, 1),
					//new SleepAction(3),
					new Claw(hardwareMap,0),
					//new SleepAction(3),
					new Wrist(hardwareMap, 0)
				)
		);
	}

	@Override
	public void loop() {

	}
}
