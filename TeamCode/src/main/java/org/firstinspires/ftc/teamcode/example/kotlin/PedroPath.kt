package org.firstinspires.ftc.teamcode.example.kotlin
import com.pedropathing.pathgen.Point
import com.pedropathing.follower.Follower
import com.pedropathing.localization.Pose
import com.pedropathing.pathgen.BezierCurve
import com.pedropathing.pathgen.BezierLine
import com.pedropathing.pathgen.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay
import com.rowanmcalpin.nextftc.pedro.FollowPath
import com.rowanmcalpin.nextftc.pedro.PedroOpMode
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants
import org.firstinspires.ftc.teamcode.subsystems.Arm
import org.firstinspires.ftc.teamcode.subsystems.Claw
import org.firstinspires.ftc.teamcode.subsystems.Slides
import org.firstinspires.ftc.teamcode.subsystems.Wrist

@Autonomous(name = "NextFTC Autonomous Program 2 Kotlin")
class PedroPath: PedroOpMode(Claw, Slides, Wrist, Arm) {
    //starting position
    private val startPose = Pose(8.0, 64.0, Math.toRadians(180.0))
    //specimens deposit
    private val depositPose = Pose(39.0, 64.0, Math.toRadians(180.0))
    //get ready to push the 1 spec
    private val pushPose1 = Pose(68.62, 15.71, Math.toRadians(180.0))
    private val pushPose1control1 = Pose(23.07, 39.98, Math.toRadians(180.0))
    private val pushPose1control2 = Pose(55.49, 36.80, Math.toRadians(180.0))
    //push to obv zone
    private val pickUp1and2 = Pose(21.28, 16.309, Math.toRadians(180.0))









    private lateinit var depositFirstSpec: PathChain
    private lateinit var goToPushPose1: PathChain
    private lateinit var push1: PathChain
    private lateinit var depositSecondSpec: PathChain
    private lateinit var depositThirdSpec: PathChain
    private lateinit var gotoThirdSpec: PathChain









    private fun buildPaths() {
        depositFirstSpec = follower.pathBuilder()
            .addPath(BezierLine(Point(startPose), Point(depositPose)))
            .setConstantHeadingInterpolation(startPose.heading)
            .build()

        goToPushPose1 = follower.pathBuilder()
            .addPath(BezierCurve(Point(depositPose), Point(pushPose1control1), Point(pushPose1control2), Point(pushPose1)))
            .setConstantHeadingInterpolation(depositPose.heading)
            .build()
        push1 = follower.pathBuilder()
            .addPath(BezierLine(Point(pushPose1), Point(pickUp1and2)))
            .setConstantHeadingInterpolation(pushPose1.heading)
            .build()
        depositSecondSpec = follower.pathBuilder()
            .addPath(BezierLine(Point(pickUp1and2), Point(depositPose)))
            .setConstantHeadingInterpolation(pickUp1and2.heading)
            .build()
        gotoThirdSpec= follower.pathBuilder()
            .addPath(BezierLine(Point(depositPose), Point(pickUp1and2)))
            .setConstantHeadingInterpolation(depositPose.heading)
            .build()
        depositThirdSpec = follower.pathBuilder()
            .addPath(BezierLine(Point(pickUp1and2), Point(depositPose)))
            .setConstantHeadingInterpolation(pickUp1and2.heading)
            .build()

    }





    val secondRoutine: Command
        get() = SequentialGroup(
                FollowPath(depositFirstSpec),
                FollowPath(goToPushPose1),
                FollowPath(push1),
                FollowPath(depositSecondSpec),
                FollowPath(gotoThirdSpec),
                FollowPath(depositThirdSpec)
        )

    override fun onInit() {
        follower = Follower(hardwareMap, FConstants::class.java, LConstants::class.java)
        follower.setStartingPose(startPose)
        buildPaths()


    }

    override fun onStartButtonPressed() {
        secondRoutine()
    }
}