package org.firstinspires.ftc.teamcode.Actions;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LoopUpdate {

    // This class is made so everything can update even whe it action isnt being used (❁´◡`❁)
    public final DcMotor shoulder;

    TelemetryPacket tp;
    LoopUpdate(HardwareMap hw, TelemetryPacket Telemetrypacket){
        shoulder = hw.get(DcMotor.class,"shoulder");
        this.tp = Telemetrypacket;
    }

    public int getShoulder(){
        return shoulder.getCurrentPosition();
    }
//    public void shoulderTelementry(){
//        packet.put("Target", target);
//        packet.put("Current Position", currentPosition);
//        packet.put("Motor Power", power + feedforward);
//        FtcDashboard.getInstance().sendTelemetryPacket(packet);
//
//    }
}
