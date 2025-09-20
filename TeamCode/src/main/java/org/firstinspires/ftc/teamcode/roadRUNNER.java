package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.MecanumDrive.PARAMS;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class roadRUNNER extends OpMode {
    Pose2d beginPose;
    MecanumDrive drive;
    Localizer localizer;

    TrigLocation trig;

    double Dx, Dy,Dh;

    double X;
    double Y;
    double H;
    double aimTrigger;
    double parkTrigger;

    public String allianceGoal = "RED GOAL";

 // red park is 37, -33

    @Override
    public void init() {
        drive = new MecanumDrive(hardwareMap, beginPose);
        localizer = new TwoDeadWheelLocalizer(hardwareMap,drive.lazyImu.get(), PARAMS.inPerTick, beginPose);
        beginPose = new Pose2d(0, 0, Math.toRadians(180));
        trig = new TrigLocation(drive,localizer,hardwareMap);
        Dx = beginPose.position.x;
        Dy = beginPose.position.y;
        Dh = Double.valueOf(String.valueOf(beginPose.heading));


         X = gamepad1.left_stick_y;
         Y = gamepad1.left_stick_x;
         H = gamepad1.right_stick_x;
         aimTrigger = gamepad1.right_trigger;
         parkTrigger = gamepad1.right_trigger;

    }
    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
        telemetry.addData("X,Y,H", localizer.getPose());
        telemetry.addLine(allianceGoal);
        telemetry.update();
        localizer.update();


        if (parkTrigger <= 0.5) {
            if (X != 0) {
                Dx += X * 12.2;
            } else {
                Dx = localizer.getPose().position.x;
            }

            if (Y != 0) {
                Dy += Y * 12.2;
            } else {
                Dy = localizer.getPose().position.y;
            }

            if (aimTrigger != 0) {
                if (H != 0) {
                    Dh += trig.normalizeAngle(X * Math.toRadians(30));

                } else {
                    Dh = Double.valueOf(String.valueOf(localizer.getPose().heading));
                }
            } else {
                if (allianceGoal == "RED GOAL") {
                    Dh = trig.TurnToRed();
                }else if (allianceGoal == "BLUEGOAL"){
                    Dh = trig.TurnToBlue();
                }
            }
        }else{
            Dy = 37;
            Dx = -33;
            Dh = Math.toRadians(90);
        }


        Actions.runBlocking(
          drive.actionBuilder(beginPose)
                  .strafeToLinearHeading(new Vector2d(Dx,Dy),Dh)
                  .build());

    }
}

