//package org.firstinspires.ftc.teamcode.PIDF;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.arcrobotics.ftclib.command.Command;
//import com.arcrobotics.ftclib.command.CommandBase;
//import com.arcrobotics.ftclib.command.SubsystemBase;
//import com.arcrobotics.ftclib.controller.PIDFController;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//@Config
//public class Arm extends SubsystemBase {
//    public static double p = 0;
//    public static double i = 0;
//    public static double d = 0;
//    public static double f = 0;
//    private double speed;
//    private double currentPosition;
//    private double target;
//
//    PIDFController pidfController = new PIDFController(p, i, d, f);
//    DcMotor arm;
//
//    public Arm(HardwareMap hardwareMap){
//        this.arm = hardwareMap.get(DcMotor.class, "shoulder");
//        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        arm.setDirection(DcMotor.Direction.REVERSE);
//        pidfController.setTolerance(10);
//        reset();
//    }
//
//    public void reset(){
//        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }
//
//    public Command goTo(double target){
//        return new ArmPos(target);
//    }
//
//    public ArmTelemetry telemetry(){
//        return new ArmTelemetry();
//    }
//
//    public class ArmTelemetry {
//        private final double speed;
//        private final double currentPosition;
//        private final double target;
//        private final double p;
//        private final double i;
//        private final double d;
//        private final double f;
//
//        public ArmTelemetry(){
//            speed = Arm.this.speed;
//            currentPosition = Arm.this.currentPosition;
//            target = Arm.this.target;
//            p = Arm.this.pidfController.getP();
//            i = Arm.this.pidfController.getI();
//            d = Arm.this.pidfController.getD();
//            f = Arm.this.pidfController.getF();
//        }
//
//        public double getSpeed(){
//            return speed;
//        }
//
//        public double getCurrentPosition(){
//            return currentPosition;
//        }
//
//        public double getTarget() {
//            return target;
//        }
//
//        public double getP() {
//            return p;
//        }
//
//        public double getI() {
//            return i;
//        }
//
//        public double getD() {
//            return d;
//        }
//
//        public double getF() {
//            return f;
//        }
//    }
//
//    public class ArmPos extends CommandBase{
//        public ArmPos(double armTarget){
//            target = armTarget;
//        }
//
//        @Override
//        public void execute(){
//            currentPosition = arm.getCurrentPosition();
//            speed = pidfController.calculate(currentPosition, target);
//            arm.setPower(speed);
//        }
//
//        @Override
//        public boolean isFinished(){
////            return pidfController.atSetPoint();
//            return false;
//        }
//    }
//}
