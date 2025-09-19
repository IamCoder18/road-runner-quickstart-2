package org.firstinspires.ftc.teamcode.PIDF;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Config
@Autonomous
public class PIDTuning extends OpMode {
    LiftNew lift;
    public static double target = 0;
    private final static double ticksPerDegree = 1; // TODO: find the tick/inch
    MultipleTelemetry multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    @Override
    public void init() {
        lift = new LiftNew(hardwareMap, multipleTelemetry);


    }

    @Override
    public void loop() {
        double degrees = target * ticksPerDegree;

        lift.Goto((int)degrees);
    }
}
