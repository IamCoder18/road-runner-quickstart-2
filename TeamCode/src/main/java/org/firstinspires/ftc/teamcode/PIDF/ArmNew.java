package org.firstinspires.ftc.teamcode.PIDF;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@Config
public class ArmNew {

    // This is based on Kookybotz setup for PIDs
    //  video --> https://www.youtube.com/watch?v=E6H6Nqe6qJo

    DcMotor shoulder;

    MultipleTelemetry Telemetry;
    private PIDController controller;

    // These are the vaules that worked on jun 21
    public static double p = 0.0009, i = 0, f = 0.0001, d = 0.0005;
    public static double target;
    private static double ticks_per_degree = 700/180;

    public ArmNew(HardwareMap hw, MultipleTelemetry telemetry){
        shoulder = hw.get(DcMotor.class,"shoulder");
        this.Telemetry = telemetry;
        shoulder.setDirection(DcMotorSimple.Direction.REVERSE);
        controller = new PIDController(p,i,d);
    }

    public void Goto(double pos){
        target = pos;
        controller.setPID(p,i,d);
        int armpos = shoulder.getCurrentPosition();
        double pid = controller.calculate(armpos,target);
        double ff = Math.cos(target/ticks_per_degree)*f;

        double power = pid + ff;

        shoulder.setPower(power);

        Telemetry.addData("target",target);
        Telemetry.addData("Arm Pos:", armpos);
        Telemetry.addData("Arm Power:", power);
        Telemetry.update();
    }
}
