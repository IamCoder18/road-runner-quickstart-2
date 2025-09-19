package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.NewServo;

import java.sql.Time;

public class ServoArm implements Action {
    private final NewServo Arm1;
    private final NewServo Arm2;
    private final double target;

    private ServoController controller;
    private java.sql.Time time = new java.sql.Time(0);

    public ServoArm(HardwareMap hw, double target) {
        this.Arm1 = new NewServo(hw,"Arm1");
        this.Arm2 = new NewServo(hw,"Arm2");

//        this.Arm1.scaleRange(0, 1);
//        this.Arm2.scaleRange(0,1);
        //
        this.target = target;
    }

    /**
     * Moves the claw to the target position depending on the targets given in the constructor.
     * Unfortunately, you can't get the servo positions in the FTC SDK, so you need to add a
     * short wait after every move to ensure the servo have time to move to its target position.
     */
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
         this.Arm1.moveToPosition(target);
        this.Arm2.moveToPosition(target);
        telemetryPacket.put("Claw Target", target);


        return  Arm1.getPos() == target;
    }
}