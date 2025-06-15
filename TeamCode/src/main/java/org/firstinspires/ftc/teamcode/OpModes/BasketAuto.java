package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous
public class BasketAuto extends OpMode {
    Pose2d beginPose;
    MecanumDrive drive;

    @Override
    public void init() {
        beginPose = new Pose2d(-35.5, -61.5, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, beginPose);
    }

    @Override
    public void start() {
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                    .splineTo(new Vector2d(-48,-38), Math.toRadians(90))
                    .waitSeconds(2)
                    .turnTo(180)
                    .splineTo(new Vector2d(-52,-52), Math.toRadians(225))
                    .waitSeconds(2)
                    .splineTo(new Vector2d(-57,-38), Math.toRadians(90))
                    .waitSeconds(2)
                    .turnTo(180)
                    .splineTo(new Vector2d(-52,-52), Math.toRadians(225))
                    .build());
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
        telemetry.update();
    }

}

