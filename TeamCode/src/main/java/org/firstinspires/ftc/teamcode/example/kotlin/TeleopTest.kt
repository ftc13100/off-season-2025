
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled
import org.firstinspires.ftc.teamcode.subsystems.Claw
import org.firstinspires.ftc.teamcode.subsystems.Arm
import org.firstinspires.ftc.teamcode.subsystems.Slides

@TeleOp(name = "NextFTC Main TeleOp")
class TeleOpProgram: NextFTCOpMode(Claw, Arm) {

    // Change the motor names to suit your robot.
    val frontLeftName = "leftFront"
    val frontRightName = "rightFront"
    val backLeftName = "leftRear"
    val backRightName = "rightRear"

    val frontLeftMotor by lazy { MotorEx(frontLeftName) }
    val frontRightMotor  by lazy { MotorEx(frontRightName) }
    val backLeftMotor by lazy { MotorEx(backLeftName) }
    val backRightMotor by lazy { MotorEx(backRightName) }

    lateinit var motors: Array<MotorEx>

    lateinit var driverControlled: MecanumDriverControlled


    override fun onInit() {
        // Change the motor directions to suit your robot.
        frontLeftMotor.direction = DcMotorSimple.Direction.FORWARD
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

        gamepadManager.gamepad1.y.pressedCommand = { InstantCommand { driverControlled.scalar = 0.5 } }
        gamepadManager.gamepad1.y.releasedCommand = { InstantCommand { driverControlled.scalar = 1.0 } }
        gamepadManager.gamepad2.a.pressedCommand = {Claw.open}
        gamepadManager.gamepad2.b.pressedCommand = {Claw.close}

        gamepadManager.gamepad2.leftBumper.pressedCommand = {Arm.toHigh}
        gamepadManager.gamepad2.rightBumper.pressedCommand = {Arm.toLow}
        gamepadManager.gamepad2.rightBumper.releasedCommand = {Arm.toStop}
        gamepadManager.gamepad2.leftBumper.releasedCommand = {Arm.toStop}

        gamepadManager.gamepad2.dpadLeft.pressedCommand = {Slides.toSlideHigh}
        gamepadManager.gamepad2.dpadRight.pressedCommand = {Slides.toSlideLow}
        gamepadManager.gamepad2.dpadLeft.releasedCommand = {Slides.toSlideStop}
        gamepadManager.gamepad2.dpadRight.releasedCommand = {Slides.toSlideStop}




    }

    override fun onUpdate() {
        this.telemetry.addData("Position", Arm.motor.currentPosition)
        this.telemetry.update()
    }
}