package org.firstinspires.ftc.teamcode.Actions;

public class Constants {

    // here is were all constanst needed for Autonomous

    public  int MAX_LIFT_HEIGHT = 3840;
    public int LIFT_PERCENT(int percentage){
        return (percentage/100) * MAX_LIFT_HEIGHT;
    }

    public static int ARM_UP = 1;
}
