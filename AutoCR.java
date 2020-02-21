package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.PropertyResourceBundle;

/*
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

 @Autonomous(name = "AutoColourRed", group = "AutonomousPeriod")
 public class AutoCR extends LinearOpMode {

    /* Declare OpMode members */
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor leftDriveBack = null;
    DcMotor rightDriveBack = null;
    DcMotor leftDriveFront = null;
    DcMotor rightDriveFront = null;
    
    
    
    private Servo tray1 = null;
    private Servo tray2 = null;
    private Servo hand = null;

    private double handPosition = 0.7;
    private double trayPosition1 = 0.42;
    private double trayPosition2 = 0.6;

    //PEOPLE READ UP HERE
    private int turnTime = 500; // edit '500' value

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables
         * The init() method of the hardware class does all the work here
         */
        leftDriveBack = hardwareMap.get(DcMotor.class, "Left Motor Back");
        rightDriveBack = hardwareMap.get(DcMotor.class, "Right Motor Back");
        leftDriveFront = hardwareMap.get(DcMotor.class, "Left Motor Front");
        rightDriveFront = hardwareMap.get(DcMotor.class, "Right Motor Front");
        
                hand = hardwareMap.servo.get("Hand");

        tray1 = hardwareMap.servo.get("Tray1");
        tray2 = hardwareMap.servo.get("Tray2");

        leftDriveBack.setDirection(DcMotor.Direction.REVERSE);
        rightDriveBack.setDirection(DcMotor.Direction.FORWARD);
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        
        hand.setPosition(handPosition);

        tray1.setPosition(trayPosition1);
        tray2.setPosition(trayPosition2);

        //Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        //Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Do stuff 288 = 1 rotation -> 31.9 cm per rotation --> 2 rotations = 1 mat square:


        //input in cm
        
        
        Drive(1, 60);
        TurnLeft90(1, 35);
        
        while(opModeIsActive() && !isStopRequested()){
            if(color.red < 5 && color.green < 5 && color.blue < 5){
                TurnRight90(1, 10);
                handposition = 0.7;
            }else{
                Drive(1, 90);
            }
        }
        telemetry.addData("red: ", color.red());
        telemetry.addData("green: ", color.green());
        telemetry.addData("blue: ", color.blue());
        telemetry.update();
        
        
        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    private void Drive(double power, double distance)
    {
        distance *= 1440 / 31.9;
        
        //distance = ;
        
        leftDriveBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightDriveBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        leftDriveFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightDriveFront.setMode(DcMotor.RunMode.RESET_ENCODERS);


        leftDriveBack.setTargetPosition((int)distance);
        rightDriveBack.setTargetPosition((int)distance);
        leftDriveFront.setTargetPosition((int)distance);
        rightDriveFront.setTargetPosition((int)distance);
        
        leftDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDriveBack.setPower(power);
        rightDriveBack.setPower(power);
        leftDriveFront.setPower(power);
        rightDriveFront.setPower(power);

        while(leftDriveBack.isBusy() && leftDriveFront.isBusy() && rightDriveBack.isBusy() && rightDriveFront.isBusy())
        {

        }

        leftDriveBack.setPower(0);
        rightDriveBack.setPower(0);
        leftDriveFront.setPower(0);
        rightDriveFront.setPower(0);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    }

    private void TurnRight90(double power, int distance)
    {
        distance *= 1440 / 31.9;

        
        leftDriveBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightDriveBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        leftDriveFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightDriveFront.setMode(DcMotor.RunMode.RESET_ENCODERS);


        leftDriveBack.setTargetPosition((int)distance);
        rightDriveBack.setTargetPosition(-(int)distance);
        leftDriveFront.setTargetPosition((int)distance);
        rightDriveFront.setTargetPosition(-(int)distance);
        
        leftDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDriveBack.setPower(power);
        rightDriveBack.setPower(-power);
        leftDriveFront.setPower(power);
        rightDriveFront.setPower(-power);

        while(leftDriveBack.isBusy() && leftDriveFront.isBusy() && rightDriveBack.isBusy() && rightDriveFront.isBusy())
        {

        }

        leftDriveBack.setPower(0);
        rightDriveBack.setPower(0);
        leftDriveFront.setPower(0);
        rightDriveFront.setPower(0);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    }

    private void TurnLeft90(double power, int distance)
    {
                distance *= 1440 / 31.9;

        
        leftDriveBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightDriveBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        leftDriveFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightDriveFront.setMode(DcMotor.RunMode.RESET_ENCODERS);


        leftDriveBack.setTargetPosition(-(int)distance);
        rightDriveBack.setTargetPosition((int)distance);
        leftDriveFront.setTargetPosition(-(int)distance);
        rightDriveFront.setTargetPosition((int)distance);
        
        leftDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDriveBack.setPower(-power);
        rightDriveBack.setPower(power);
        leftDriveFront.setPower(-power);
        rightDriveFront.setPower(power);

        while(leftDriveBack.isBusy() && leftDriveFront.isBusy() && rightDriveBack.isBusy() && rightDriveFront.isBusy())
        {

        }
        
        //bob the builder lets build it

        leftDriveBack.setPower(0);
        rightDriveBack.setPower(0);
        leftDriveFront.setPower(0);
        rightDriveFront.setPower(0);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    }
 }
