package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.Servo
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.ftc.OpModeData
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition


object Claw: Subsystem() {
    lateinit var servo: Servo

    val name = "intakeRoller"

    val open: Command
        get() = ServoToPosition(servo, // SERVO TO MOVE
            0.6, // POSITION TO MOVE TO
            this)  // IMPLEMENTED SUBSYSTEM

    val close: Command
        get() = ServoToPosition(servo, // SERVO TO MOVE
            0.2, // POSITION TO MOVE TO
            this) // IMPLEMENTED SUBSYSTEM

    override fun initialize() {
        servo = OpModeData.hardwareMap.get(Servo::class.java, name)
    }
}