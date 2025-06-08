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
    private double speed;
    private double currentPosition;
    private double target;

    PIDFController pidfController = new PIDFController(p, i, d, f);
    Motor arm;

    public Arm(HardwareMap hw){
        arm = new Motor(hw, "shoulder", Motor.GoBILDA.RPM_312);
        reset();
    }

    public void reset(){
        arm.resetEncoder();
    }

    public Command goTo(double target){
        return new ArmPos(target);
    }

    public ArmTelemetry telemetry(){
        return new ArmTelemetry();
    }

    public class ArmTelemetry {
        public double speed;
        public double currentPosition;
        public double target;

        public ArmTelemetry(){
            speed = Arm.this.speed;
            currentPosition = Arm.this.currentPosition;
            target = Arm.this.target;
        }

        public double getSpeed(){
            return speed;
        }
        public double getCurrentPosition(){
            return currentPosition;
        }
        public double getTarget() {
            return target;
        }
    }

    public class ArmPos extends CommandBase{
        public ArmPos(double armTarget){
            target = armTarget;
        }

        @Override
        public void execute(){
            currentPosition = arm.getCurrentPosition();
            speed = pidfController.calculate(currentPosition, target);
            arm.set(speed);
        }

        @Override
        public boolean isFinished(){
            return speed <= 0.001;
        }
    }
}
