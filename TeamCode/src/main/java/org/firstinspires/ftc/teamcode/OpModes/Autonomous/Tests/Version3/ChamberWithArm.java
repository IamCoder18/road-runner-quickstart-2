package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests.Version3;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.Actions.Action;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous
public class ChamberWithArm extends OpMode {
    private Action action;
    Pose2d beginPose;
    MecanumDrive drive;


    @Override
    public void init() {
        this.action = new Action(hardwareMap, telemetry);
        beginPose = new Pose2d(11.5, -61.5, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, beginPose);
    }

    @Override
    public void start() {
        Actions.runBlocking(GetCaseOne());
    }

    SequentialAction GetCaseOne(){
        return new SequentialAction(
                action.claw(0),
                action.hold(0.5),
                action.shoulder(90),
                action.wrist(0),
                action.hold(0.5),
                drive.actionBuilder(beginPose)
                        .strafeTo(new Vector2d(7,-39))
                        .waitSeconds(0.2) //score on obervation zone
                        .strafeTo(new Vector2d(0,-39))
                        .build(),
                action.claw(1)
        );
    }

    @Override
    public void loop() {

    }

}



