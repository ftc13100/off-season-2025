package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower


object Slides: Subsystem() {
    val slide by lazy { MotorEx("leftSlideString").reverse() }
    val slide2 by lazy { MotorEx("rightSlideString") }
    val slides by lazy { MotorGroup(slide, slide2) }


    val toSlideHigh: Command
        get() = SetPower(slides, 0.8, this)
    val toSlideLow: Command
        get() = SetPower(slides, -0.8, this)
    val toSlideStop: Command
        get() = SetPower(slides, 0.0, this)

    override fun initialize() {
        slide.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        slide2.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }
}