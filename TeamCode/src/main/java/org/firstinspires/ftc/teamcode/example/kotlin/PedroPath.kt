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
    //get ready to push the 1 spec p1
    private val pushPose1= Pose(47.93, 40.17, Math.toRadians(180.0))
    private val pushPose1control= Pose(27.25, 40.18, Math.toRadians(180.0))
    //get ready to push the 2 spec p2
    private val pushPose2= Pose(64.24, 28.44, Math.toRadians(180.0))
    private val pushPose2control= Pose(70.61, 40.97, Math.toRadians(180.0))




    private lateinit var depositFirstSpec: PathChain
    private lateinit var pushAndPick1: PathChain
    private lateinit var pushAndPick2: PathChain



    private fun buildPaths() {
        depositFirstSpec = follower.pathBuilder()
            .addPath(BezierLine(Point(startPose), Point(depositPose)))
            .setConstantHeadingInterpolation(startPose.heading)
            .build()

        pushAndPick1 = follower.pathBuilder()
            .addPath(BezierCurve(Point(depositPose), Point(pushPose1control), Point(pushPose1)))
            .setLinearHeadingInterpolation(depositPose.heading, pushPose1.heading)
            .build()
        pushAndPick2 = follower.pathBuilder()
            .addPath(BezierCurve(Point(pushPose1), Point(pushPose2control), Point(pushPose2)))
            .setConstantHeadingInterpolation(pushPose1.heading)
            .build()

    }





    val secondRoutine: Command
        get() = SequentialGroup(
                FollowPath(depositFirstSpec),
                FollowPath(pushAndPick2)
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