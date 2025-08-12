package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower
import org.firstinspires.ftc.teamcode.example.kotlin.PedroPath


object Slides: Subsystem() {
    val controllerSlides = PIDFController(0.4, 0.0, 0.0, StaticFeedforward(0.1))
        //get() = RunToPosition(slides, 1000.0, controllerSlides , this)
        //how it would be done
    lateinit var slide: MotorEx
    lateinit var slide2: MotorEx
    lateinit var slides:  MotorGroup

    val toSlideHigh: Command
        get() = SetPower(slides, 0.8, this)

    val toSlideLow: Command
        get() = SetPower(slides, -0.8, this)
    val toSlideStop: Command
        get() = SetPower(slides, 0.0, this)

    override fun initialize() {
        slide = MotorEx("leftSlideString").reverse()
        slide2 = MotorEx("rightSlideString")
        slides = MotorGroup(slide, slide2)
        slide.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        slide2.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }
}