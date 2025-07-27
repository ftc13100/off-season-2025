package org.firstinspires.ftc.teamcode.subsystems
import com.qualcomm.robotcore.hardware.Servo
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay
import com.rowanmcalpin.nextftc.core.units.sec
import com.rowanmcalpin.nextftc.ftc.OpModeData
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition

object Wrist: Subsystem() {
        lateinit var ServoWrist: Servo

        val name = "intakeBelt"

        val up: Command
                get() = ServoToPosition(
                ServoWrist, // SERVO TO MOVE
                0.0, // POSITION TO MOVE TO
                this) // IMPLEMENTED SUBSYSTEM
        val down: Command
            get() = ServoToPosition(
                ServoWrist, // SERVO TO MOVE
                1.0, // POSITION TO MOVE TO
                this) // IMPLEMENTED SUBSYSTEM


        override fun initialize() {
            ServoWrist = OpModeData.hardwareMap.get(Servo::class.java, name)
        }
    }
