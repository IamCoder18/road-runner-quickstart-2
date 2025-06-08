package org.firstinspires.ftc.teamcode.PIDF;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm extends SubsystemBase {
    public static double p = 0;
    public static double i = 0;
    public static double d = 0;
    public static double f = 0;

    PIDFController pidfController = new PIDFController(p, i, d, f);
    Motor arm;
    Telemetry telemetry;

    public Arm(HardwareMap hw, Telemetry telemetry){
        this.telemetry = telemetry;
        this.telemetry.addLine("Hello from Arm!");
        arm = new Motor(hw, "shoulder", Motor.GoBILDA.RPM_312);
        reset();
    }

    public void reset(){
        arm.resetEncoder();
    }

    public Command goTo(double target){
        return new ArmPos(target);
    }

    public class ArmPos extends CommandBase{
        private final double target;
        public double speed;

        public ArmPos(double target){
            this.target = target;
        }

        @Override
        public void execute(){
            double shoulderPosition = arm.getCurrentPosition();
            speed = pidfController.calculate(shoulderPosition, target);

            telemetry.addData("Shoulder Speed", speed);
            telemetry.addData("Shoulder Position", shoulderPosition);
            telemetry.addData("Shoulder Target", target);
            arm.set(speed);
        }

        @Override
        public boolean isFinished(){
            return speed <= 0.001;
        }
    }
}
