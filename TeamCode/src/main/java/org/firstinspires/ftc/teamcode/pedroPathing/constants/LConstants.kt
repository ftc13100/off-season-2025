package org.firstinspires.ftc.teamcode.pedroPathing.constants

import com.pedropathing.localization.GoBildaPinpointDriver
import com.pedropathing.localization.constants.PinpointConstants
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit

object LConstants {
    init {
        PinpointConstants.forwardY = 1.25
        PinpointConstants.strafeX = -7.5
        PinpointConstants.distanceUnit = DistanceUnit.INCH
        PinpointConstants.hardwareMapName = "pinpoint"
        PinpointConstants.useYawScalar = false
        PinpointConstants.yawScalar = 1.0
        PinpointConstants.useCustomEncoderResolution = false
        PinpointConstants.encoderResolution =
            GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD
        PinpointConstants.customEncoderResolution = 13.26291192
        PinpointConstants.forwardEncoderDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED
        PinpointConstants.strafeEncoderDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD
    }
}