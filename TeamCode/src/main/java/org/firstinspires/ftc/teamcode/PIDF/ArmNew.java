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
    DcMotor test;

    MultipleTelemetry Telemetry;
    private PIDController controller;

    // These are the vaules that worked on jul 10
    public static double p = 0, i = 0, f = 0, d = 0;
    public static double target;
   // private static double ticks_per_degree = 1.06805556;

    private static double ticks_per_degree = 22.755;

    public ArmNew(HardwareMap hw, MultipleTelemetry telemetry){
        shoulder = hw.get(DcMotor.class,"shoulder");
        test = hw.get(DcMotor.class,"test");
        this.Telemetry = telemetry;
        test.setDirection(DcMotorSimple.Direction.REVERSE);
        controller = new PIDController(p,i,d);
    }

    public void Goto(double pos){
        target = (int) (pos * ticks_per_degree);
        controller.setPID(p,i,d);
        int armpos = shoulder.getCurrentPosition();
        double pid = controller.calculate(armpos, target);
        double ff = Math.cos(target/ticks_per_degree)*f;

        double power = pid + ff;

        shoulder.setPower(power);

        Telemetry.addData("target",target);
        Telemetry.addData("Arm Pos:", armpos);
        Telemetry.addData("Arm Power:", power);
        Telemetry.update();
    }
}
