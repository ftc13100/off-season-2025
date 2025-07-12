package org.firstinspires.ftc.teamcode.subsystems

import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition
import kotlin.math.PI
import kotlin.math.cos


object Lift: Subsystem() {
    lateinit var motor: MotorEx
    lateinit var motor2: MotorEx


    val controller = PIDFController(5.0, 0.0, 0.1, StaticFeedforward(0.0))

    val name = "leftSlideAxel"
    val name2 = "rightSlideAxel"

    val toLow: Command
        get() = RunToPosition(motor, // MOTOR TO MOVE
            0.0, // TARGET POSITION, IN TICKS
            controller, // CONTROLLER TO IMPLEMENT
            this) // IMPLEMENTED SUBSYSTEM
    val toLow2: Command
        get() = RunToPosition(motor2, // MOTOR TO MOVE
            0.0, // TARGET POSITION, IN TICKS
            controller, // CONTROLLER TO IMPLEMENT
            this) // IMPLEMENTED SUBSYSTEM




    val toHigh: Command
        get() = RunToPosition(motor, // MOTOR TO MOVE
            1320.0, // TARGET POSITION, IN TICKS
            controller, // CONTROLLER TO IMPLEMENT
            this) // IMPLEMENTED SUBSYSTEM
    val toHigh2: Command
        get() = RunToPosition(motor2, // MOTOR TO MOVE
            -1320.0, // TARGET POSITION, IN TICKS
            controller, // CONTROLLER TO IMPLEMENT
            this) // IMPLEMENTED SUBSYSTEM

    override fun initialize() {
        motor = MotorEx(name)
        motor2 = MotorEx(name2)
    }


}