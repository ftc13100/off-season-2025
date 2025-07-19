package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower


object Arm: Subsystem() {
    val armMotor by lazy { MotorEx("leftSlideAxel") }
    val armMotor2 by lazy { MotorEx("rightSlideAxel") }
    val arms by lazy { MotorGroup(armMotor, armMotor2) }

    val controller = PIDFController(5.0, 0.0, 0.1, StaticFeedforward(0.0))


    val toLow: Command
        get() = SetPower(arms, 0.6, this)
    val toHigh: Command
        get() = SetPower(arms, -0.6, this)
    val toStop: Command
        get() = SetPower(arms, 0.0, this)






    override fun initialize() {
        armMotor2.direction = DcMotorSimple.Direction.REVERSE
    }


}