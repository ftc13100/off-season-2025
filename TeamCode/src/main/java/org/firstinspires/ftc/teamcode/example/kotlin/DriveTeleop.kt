package org.firstinspires.ftc.teamcode.example.kotlin

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx

@TeleOp
class DriveTeleop : NextFTCOpMode() {
    // Change the motor names to suit your robot.
    val frontLeftName = "leftFront"
    val frontRightName = "rightFront"
    val backLeftName = "leftRear"
    val backRightName = "rightRear"

    lateinit var frontLeftMotor: MotorEx
    lateinit var frontRightMotor: MotorEx
    lateinit var backLeftMotor: MotorEx
    lateinit var backRightMotor: MotorEx

    lateinit var motors: Array<MotorEx>

    lateinit var driverControlled: MecanumDriverControlled


    override fun onInit() {
        frontLeftMotor = MotorEx(frontLeftName)

        frontRightMotor = MotorEx(frontRightName)
        backLeftMotor = MotorEx(backLeftName)
        backRightMotor = MotorEx(backRightName)

        // Change the motor directions to suit your robot.
        frontLeftMotor.direction = DcMotorSimple.Direction.REVERSE
        backLeftMotor.direction = DcMotorSimple.Direction.REVERSE
        frontRightMotor.direction = DcMotorSimple.Direction.FORWARD
        backRightMotor.direction = DcMotorSimple.Direction.FORWARD

        //braking instead of coasting
        frontLeftMotor.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        backLeftMotor.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        frontRightMotor.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        backRightMotor.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        motors = arrayOf(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor)
    }

    override fun onStartButtonPressed() {
        driverControlled = MecanumDriverControlled(motors, gamepadManager.gamepad1)
        driverControlled.scalar = 1.0
        driverControlled()

        gamepadManager.gamepad1.y.pressedCommand =
            { InstantCommand { driverControlled.scalar = 0.5 } }
        gamepadManager.gamepad1.y.releasedCommand =
            { InstantCommand { driverControlled.scalar = 1.0 } }

    }

    override fun onUpdate() {
    }
}