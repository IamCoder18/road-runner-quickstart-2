package org.firstinspires.ftc.teamcode.PIDF;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Arm extends SubsystemBase {
    double p = 0;
    double i = 0;
    double d = 0;
    double f = 0;

    PIDFController pidfController = new PIDFController(p, i, d, f);
    Motor arm;

    public Arm(HardwareMap hw){
         arm = new Motor(hw, "Shoulder", Motor.GoBILDA.RPM_312);
         reset();
    }

    public void reset(){
        arm.resetEncoder();
    }

    public Command goTo(double Degrees){
        return new ArmPos(Degrees);
    }

    public class ArmPos extends CommandBase{
        private final double target;
        public double speed;

        public ArmPos(double degrees){
            target = degrees;
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
