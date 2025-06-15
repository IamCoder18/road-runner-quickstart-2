package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous
public class ChamberAuto extends OpMode {
    Pose2d beginPose;
    MecanumDrive drive;

    @Override
    public void init() {
        beginPose = new Pose2d(11.5, -61.5, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, beginPose);
    }

    @Override
    public void start() {
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .splineTo(new Vector2d(36,-36),Math.toRadians(90))
                        .splineTo(new Vector2d(36,0),Math.toRadians(90))
                        .strafeTo(new Vector2d(42,0))
                        .strafeTo(new Vector2d(42,-50)) // First sample into observation zone
                        .strafeTo(new Vector2d(42,0))
                        .strafeTo(new Vector2d(56,0))
                        .strafeTo(new Vector2d(56,-50)) // Second sample into observation zone
                        .strafeTo(new Vector2d(56,0))
                        .strafeTo(new Vector2d(61,0))
                        .strafeTo(new Vector2d(61,-50)) // Third sample into observation zone
                        .strafeToLinearHeading(new Vector2d(42,-30),Math.toRadians(270))
                        .build());
    }

    @Override
    public void loop() {}

}

