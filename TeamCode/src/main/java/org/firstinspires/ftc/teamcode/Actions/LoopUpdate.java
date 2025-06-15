package org.firstinspires.ftc.teamcode.Actions;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LoopUpdate {

    // This class is made so everything can update even whe it action isnt being used
    public final DcMotor shoulder;
    LoopUpdate(HardwareMap hw, MultipleTelemetry multipleTelemetry){
        shoulder = hw.get(DcMotor.class,"shoulder");
    }

    public int getShoulder(){
        return shoulder.getCurrentPosition();
    }
}
