package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Autonomous
public class RoadRunnerMoveTest extends OpMode {
    Pose2d beginPose;
    MecanumDrive drive;
    @Override
    public void init() {
        beginPose = new Pose2d(0, 0, Math.toRadians(0));
        drive = new MecanumDrive(hardwareMap, beginPose);
    }

    @Override
    public void loop() {
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .strafeTo(new Vector2d(24, 0))
                        .strafeTo(new Vector2d(0, 0))
                        .strafeTo(new Vector2d(0, 24))
                        .strafeTo(new Vector2d(0, 0))
                        .strafeTo(new Vector2d(-24, 0))
                        .strafeTo(new Vector2d(-24, -24))
                        .strafeTo(new Vector2d(24, -24))
                        .strafeTo(new Vector2d(24, 24))
                        .strafeTo(new Vector2d(-24, 24))
                        .strafeTo(new Vector2d(-24, 0))
                        .strafeTo(new Vector2d(0,0))
                        .build());
    }
}
