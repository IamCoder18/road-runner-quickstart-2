package org.firstinspires.ftc.teamcode.PIDF;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift extends SubsystemBase {

    //PIDFController pidf;

    double kS;
    double Kcos;
    double kA;
    double kV;

    double velo;

    double target;

    ElevatorFeedforward feedforward = new ElevatorFeedforward(kS, Kcos, kA, kV);
    Motor liftL;
    Motor liftR;


    public Lift(HardwareMap hw,ElevatorFeedforward feed ){
        this.feedforward = feed;
        liftL = new Motor(hw, "liftLeft", Motor.GoBILDA.RPM_312);
        liftR = new Motor(hw, "liftRight", Motor.GoBILDA.RPM_312);

        liftR.setInverted(true);
        Reset();

    }
    public void Reset(){
        liftL.resetEncoder();
        liftR.resetEncoder();
    }



    public Command GoTo(double height){
        return new LiftPos(height);
    }

    public class LiftPos extends CommandBase{

        public LiftPos(double height){
            target = height;

        }
        @Override
        public void execute(){
            telemetry.addData("sholder Position",feedforward.calculate(velo,target));
            liftL.set(feedforward.calculate(velo,target));
            liftR.set(feedforward.calculate(velo,target));// TODO: find the velocity speed thats resonable

        }
        @Override
        public boolean isFinished(){
            return feedforward.calculate(velo,target) <= 0;
        }

    }
}
