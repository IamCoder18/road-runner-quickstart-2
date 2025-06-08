package org.firstinspires.ftc.teamcode.PIDF;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Config
@Autonomous
public class PIDTuning extends OpMode {
    Arm arm;
    public static double target = 2000;
//    Lift lift;

    @Override
    public void init() {
        arm = new Arm(hardwareMap, telemetry);
//        lift = new Lift(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            CommandScheduler.getInstance().schedule(
                    new SequentialCommandGroup(
                            arm.goTo(target).withTimeout(3000)
                    )
            );
        }
        else if (gamepad1.b) {
            CommandScheduler.getInstance().schedule(
                    new SequentialCommandGroup(
                            arm.goTo(target).withTimeout(3000)
                    )
            );
        }
    }
}
