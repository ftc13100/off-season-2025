package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.ArmFeedforward
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower
import kotlin.math.PI


object Arm: Subsystem() {                                                                                                //This>
    val controllerArm = PIDFController(0.4, 0.0, 0.0, ArmFeedforward(0.05) {ticks -> ticks * 2786.2 / (2 * PI)})
     //kotlin doesn't offer KS or KV or if it does its very complicated
    //kCos value was implemented but the cpr/ppr would need to be updated if the motor used for the arm was not in fact 30 rpm
    //    get() = RunToPosition(arms, 1000.0, controllerArm, this)
    val armMotor by lazy { MotorEx("leftSlideAxel") }
    val armMotor2 by lazy { MotorEx("rightSlideAxel") }
    val arms by lazy { MotorGroup(armMotor, armMotor2) }



    val toLow: Command
       get() = SetPower(arms, 0.6, this)
    val toHigh: Command
        get() = SetPower(arms, -0.6, this)
    val toStop: Command
        get() = SetPower(arms, 0.0, this)






    override fun initialize() {
        armMotor2.direction = DcMotorSimple.Direction.REVERSE
        armMotor2.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        armMotor.motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }


}