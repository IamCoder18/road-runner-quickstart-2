package org.firstinspires.ftc.teamcode.PIDF;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Config
@Autonomous
public class PIDTuning extends OpMode {
    ArmNew arm;
    public static double target = 0;
    private final static double ticksPerDegree = 1; // TODO: find out why 22.755 no longer operates

    MultipleTelemetry multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    @Override
    public void init() {
        arm = new ArmNew(hardwareMap,multipleTelemetry);
    }

    @Override
    public void loop() {
        double degrees = target * ticksPerDegree;

        arm.Goto(degrees);
    }
}
