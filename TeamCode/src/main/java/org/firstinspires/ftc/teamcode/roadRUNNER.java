package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class roadRUNNER extends OpMode {
    Pose2d beginPose;
    MecanumDrive drive;
    Localizer localizer;

    TrigLocation trig;

    double Dx, Dy,Dh;

    double X = gamepad1.left_stick_y;
    double Y = gamepad1.left_stick_x;
    double H = gamepad1.right_stick_x;
    double aimTrigger = gamepad1.right_trigger;

 // red park is 37, -33

    @Override
    public void init() {
        beginPose = new Pose2d(0, 0, Math.toRadians(180));
        trig = new TrigLocation(drive,localizer,hardwareMap);
        Dx = beginPose.position.x;
        Dy = beginPose.position.y;
        Dh = Double.valueOf(String.valueOf(beginPose.heading));
        drive = new MecanumDrive(hardwareMap, beginPose);

    }
    @Override
    public void loop() {
        Pose2d pose = drive.localizer.getPose();
        telemetry.addData("Status", "Running");
        telemetry.update();
        drive.updatePoseEstimate();

        if (X != 0){
            Dx += X * 12.2;
        }else{
            Dx = drive.localizer.getPose().position.x;
        }

        if (Y != 0){
            Dy += Y * 12.2;
        }else{
            Dy = drive.localizer.getPose().position.y;
        }

        if (aimTrigger != 0){
            if (H != 0) {
                Dh += trig.normalizeAngle(X + Math.toRadians(30));

            }else{
                Dh = Double.valueOf(String.valueOf(drive.localizer.getPose().heading));
            }
        }else{
            Dh = trig.TurnToRed();
        }


        Actions.runBlocking(
          drive.actionBuilder(beginPose)
                  .strafeToLinearHeading(new Vector2d(Dx,Dy),Dh)
                  .build());

    }
}

