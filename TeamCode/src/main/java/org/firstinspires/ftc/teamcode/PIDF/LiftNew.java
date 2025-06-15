package org.firstinspires.ftc.teamcode.PIDF;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


@Config
public class LiftNew {

    // This is based on Kookybotz setup for PIDs and also ArmNew.java
    //  video --> https://www.youtube.com/watch?v=E6H6Nqe6qJo

    DcMotor liftLeft;
    DcMotor liftRight;

    MultipleTelemetry Telemetry;
    private PIDController controller;

    // These are the vaules that worked on jun 14 fort the lift
    public static double p = 0.025, i = 0, d = 0, f = 0.0006;
    public static  int target;
    private static double ticks_per_degree = 700/180;

    public LiftNew(HardwareMap hw, MultipleTelemetry telemetry){
        liftLeft = hw.get(DcMotor.class,"liftMotorLeft");
        liftRight = hw.get(DcMotor.class,"liftMotorRight");
        this.Telemetry = telemetry;
        liftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        controller = new PIDController(p,i,d);
    }

    public void Goto(int pos){
        target = pos;
        controller.setPID(p,i,d);
        int liftpos = (liftLeft.getCurrentPosition() + liftRight.getCurrentPosition()) / 2;
        double pid = controller.calculate(liftpos,target);
        double ff = Math.cos(target/ticks_per_degree)*f;

        double power = pid + ff;

        liftLeft.setPower(power);
        liftRight.setPower(power);

        Telemetry.addData("lift target",target);
        Telemetry.addData("Lift Pos:", liftpos);
        Telemetry.addData("Lift Power:", power);
        Telemetry.update();
    }
}
