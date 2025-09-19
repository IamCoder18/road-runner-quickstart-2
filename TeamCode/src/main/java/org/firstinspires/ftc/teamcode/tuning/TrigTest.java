package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import org.firstinspires.ftc.teamcode.TrigLocation;
import org.firstinspires.ftc.teamcode.messages.PoseMessage;


@Autonomous
public final class TrigTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        PoseMessage poseMessage = new PoseMessage(beginPose);
        TrigLocation trigLocation = new TrigLocation(drive, drive.localizer, hardwareMap);
        MultipleTelemetry Mtelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();



        while (opModeIsActive()) {
            drive.updatePoseEstimate();

            telemetry.addData("Calculated Angle", trigLocation.TurnToRed());
            telemetry.addData("X", trigLocation.currentPos.position.x);
            telemetry.addData("Y", trigLocation.currentPos.position.y);
        }

        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .strafeTo(new Vector2d(5,-6))
                        .strafeToLinearHeading(new Vector2d(trigLocation.currentPos.position.x,trigLocation.currentPos.position.y),trigLocation.TurnToRed())
                        .build());

    }

}
