package org.firstinspires.ftc.teamcode.PIDF;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm extends SubsystemBase {

    //PIDFController pidf;

    double kS;
    double Kcos;
    double kA;
    double kV;

    double velo;

    double target;

    ArmFeedforward feedforward = new ArmFeedforward(kS,Kcos,kA,kV);
    Motor arm;

    public Arm(HardwareMap hw,ArmFeedforward feed ){
        this.feedforward = feed;
         arm = new Motor(hw, "Sholder", Motor.GoBILDA.RPM_312);
         Reset();

    }
    public void Reset(){
        arm.resetEncoder();
    }

//    public class goTo(double Angle) e{
//        arm.set(feedforward.calculate(Math.toRadians(Angle),velo)); // TODO: find the velocity speed thats resonable
//
//    }

    public Command GoTo(double Degrees){
        return new ArmPos(Degrees);
    }

    public class ArmPos extends CommandBase{

        public ArmPos(double Degrees){
            target = Degrees;


        }
        @Override
        public void execute(){
            telemetry.addData("sholder Position",feedforward.calculate(Math.toRadians(target),velo));
            arm.set(feedforward.calculate(Math.toRadians(target),velo));;
        }
        @Override
        public boolean isFinished(){
            return feedforward.calculate(Math.toRadians(target),velo) <= 0;
        }

    }
}
