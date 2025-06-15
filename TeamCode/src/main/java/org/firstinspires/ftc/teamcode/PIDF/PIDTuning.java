package org.firstinspires.ftc.teamcode.PIDF;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@Autonomous
public class PIDTuning extends OpMode {
//    Arm arm;
    ArmNew arm;
    public static int target = 0;
//    Lift lift;

    MultipleTelemetry telemetry;

    @Override
    public void init() {
        arm = new ArmNew(hardwareMap,telemetry);
        //telemetry.addLine("Hello from PIDTuning");
//        lift = new Lift(hardwareMap);
    }

    @Override
    public void loop() {
//        if (gamepad1.a) {
//            CommandScheduler.getInstance().schedule(
//                    new SequentialCommandGroup(
//                            arm.goTo(target).withTimeout(3000)
//                    )
//            );

            arm.Goto(target);
//        }
//        else if (gamepad1.b) {
////            CommandScheduler.getInstance().schedule(
////                    new SequentialCommandGroup(
////                            arm.goTo(0).withTimeout(3000)
////                    )
////            );
//            arm.Goto(0);
//        }

//        Arm.ArmTelemetry armTelemetry = arm.telemetry();
//        telemetry.addData("Arm Speed", armTelemetry.getSpeed());
//        telemetry.addData("Arm Current Position", armTelemetry.getCurrentPosition());
//        telemetry.addData("Arm Target", armTelemetry.getTarget());
//        telemetry.addData("Arm P", armTelemetry.getP());
//        telemetry.addData("Arm I", armTelemetry.getI());
//        telemetry.addData("Arm D", armTelemetry.getD());
//        telemetry.addData("Arm F", armTelemetry.getF());
//        telemetry.update();
    }
}
