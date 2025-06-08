package org.firstinspires.ftc.teamcode.PIDF;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

@Autonomous
public class PIDTuning extends OpMode {

    Gamepad gamepad;
    Arm arm;
//    Lift lift;

    @Override
    public void init() {
        arm = new Arm(hardwareMap);
//        lift = new Lift(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad.a) {
            CommandScheduler.getInstance().schedule(
                    new SequentialCommandGroup(
                            arm.goTo(80).withTimeout(3000)
                    )
            );
        }
        else if (gamepad.b) {
            CommandScheduler.getInstance().schedule(
                    new SequentialCommandGroup(
                            arm.goTo(0).withTimeout(3000)
                    )
            );
        }
//        if (gamepad.b) {
//            CommandScheduler.getInstance().schedule(
//                    new SequentialCommandGroup(
//                            lift.GoTo(100).withTimeout(3000)
//                    )
//            );
//        }
//        else {
//            CommandScheduler.getInstance().schedule(
//                    new SequentialCommandGroup(
//                            lift.GoTo(0).withTimeout(3000)
//                    )
//            );
//        }
    }
}
