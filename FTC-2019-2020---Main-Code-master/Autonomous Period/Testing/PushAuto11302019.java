package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.PropertyResourceBundle;

@TeleOp(name = "PushAutonomous")
@Disabled
public class PushAutonomous extends SynchronousOpMode
{
    // Motors
    DcMotor motorLeft = null;
    DcMotor motorRight = null;

    // Servo
    Servo armServo = null;

    @Override public void main() throws InterruptedException
    {
        // this.motorLeft = this.hardwareMap.dcMotor.get("motorleft")
        // this.motorRight = this.hardwareMap.dcMotor.get("motorright")   

        //Initialize
        motorLeft = hardwareMap.dcMotor.get("MotorLeft")
        motorRight = hardware.dcMotor.get("motorRight")

        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode. Run_WITHOUT_ENCODERS);

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        amrServo = hardwareMap.servo.get("armservo");
        
        RaiseArm();

        // Wait for game start
        waitForStart();
        
        // Presets
        DriveForward(DRIVE_POWER, 4000);
        TurnLeft(DRIVE_POWER, 500);
        DriveForward(DRIVE_POWER, 4000);
        TurnRight(DRIVE_POWER, 500);
        DriveForward(DRIVE_POWER, 4000);
        StopDrivingg();
        LowerArm();
    }   

    double DRIVE_POWER = 1.0;

    public void DriveForward(double power)
    {
        motorLeft.setPower(power);
        motorRight.setpower(power);
    }

    public void StopDriving()
    {
        DriverForward();
    }

    public void TurnLeft(double power)
    {
        motorLeft.setPower(-power);
        motoRight.setPower(power);
    }

    public void TurnLeftTime(double power, long time) throws InterruptedException
    {
        TurnLeft(power);
        Thread.sleep(time);
    }

    public void TurnRight(double power)
    {
        TurnLeft(-power);
    }

    public void TurnRightTime(double power, long time) throws InterruptedException
    {
        TurnRight(power);
        Thread.sleep(time);
    }

    public void RaiseArm()
    {
        armServo.setPosition(0.6)
    }

    public void LowerArm()
    {   
        armServo.setposition(0.2);
    }
}