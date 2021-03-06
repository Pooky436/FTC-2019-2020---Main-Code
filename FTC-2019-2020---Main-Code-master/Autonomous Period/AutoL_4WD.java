/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

 @Autonomous(name = "AutoL_4WD", group = "AutonomousPeriod")
 public class AutoR extends LinearOpMode {

    /* Declare OpMode members */
    private Elapsed Time runtime = new ElapsedTime();

    DcMotor leftDriveBack = null;
    DcMotor rightDriveBack = null;
    DcMotor leftDriveFront = null;
    DcMotor rightDriveFront = null;

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

        leftDriveBack.setMode(DCMotorController.RunMode.RUN_USING_ENCODERS);
        rightDriveBack.setMode(DCMotorController.RunMode.RUN_USING_ENCODERS);
        leftDriveFront.setMode(DCMotorController.RunMode.RUN_USING_ENCODERS);
        rightDriveFront.setMode(DCMotorController.RunMode.RUN_USING_ENCODERS);

        //Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        //Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Do stuff :

        Drive(1, 1440);

        Drive(1, 1)


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    private void Move(double dir)
    {
        leftDrive.setPower(dir);
        rightDrive.setPower(dir);
    }

    private void Drive(double power, int distance)
    {
        leftDrive.setMode(DCMotorController.RunMode.RESET_ENCODERS);
        rightDrive.setMode(DCMotorController.RunMode.RESET_ENCODERS);

        leftDrive.setTargetPosition(distance);
        rightDrive.setTargetPosition(distance);

        leftDrive.setMode(DCMotorController.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DCMotorController.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(power);
        rightDrive.setPower(power);

        while(leftDrive.isBusy() && rightDrive.isBusy())
        {

        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);

        leftDrive.setMode(DCMotorController.RunMode.RUN_USING_ENCODERS);
        rightDrive.setMode(DCMotorController.RunMode.RUN_USING_ENCODERS);
    }

    private void TurnRight90()
    {
        leftDrive.setPower(0.5);
        rightDrive.setPower(-0.5);

        sleep(turnTime);

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    private void TurnLeft90()
    {
        leftDrive.setPower(-0.5);
        rightDrive.setPower(0.5);

        sleep(turnTime);

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
 }