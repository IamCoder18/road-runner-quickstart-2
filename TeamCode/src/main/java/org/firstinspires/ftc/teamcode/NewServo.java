package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class NewServo {

    Servo servo;

    private double targetpos;

    public double speed = 5;

    public double min = 0;
    public double max = 1;

    public enum NewServoDirection { FORWARD, REVERSE}

    public NewServo(HardwareMap hw, String deviceName){

        servo = hw.get(Servo.class,deviceName);
    }

    /** the class Direction sets the direction that the servo moves  */
    public void Direction(NewServoDirection direction){

        if (direction == NewServoDirection.FORWARD){
            servo.setDirection(Servo.Direction.FORWARD);
        }
        if (direction == NewServoDirection.REVERSE){
            servo.setDirection(Servo.Direction.REVERSE);
        }
    }
    /** the class setRange set the maximum and minimum the servo can travel. That way the servo will
     * never break any robot components that are inside the  1 and 0  */
    public void setRange(double Min, double Max){
        max = Max;
        min = Min;
    }



    public double getIncrementSpeed(){return speed;}

    public void setIncrementSpeed(double newSpeed){
        speed = newSpeed;
    }

    public void moveToPosition(double target){
        while (servo.getPosition() != target){
            if (servo.getPosition() > target){
                targetpos -= speed;
            }
            if (servo.getPosition() < target){
                targetpos += speed;
            }
            if (servo.getPosition() >= target + 10 && servo.getPosition() <= target - 10 ){
                targetpos = target;
            }
            servo.setPosition(MathUtils.clamp(targetpos,min,max));
        }

    }

    public double getPos(){return servo.getPosition();}
}
