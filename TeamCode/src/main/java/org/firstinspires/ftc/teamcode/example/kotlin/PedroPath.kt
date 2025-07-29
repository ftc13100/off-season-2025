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
    private val pushPose1= Pose(62.254, 17.9, Math.toRadians(180.0))
    private val pushPose1control= Pose(34.01, 39.98, Math.toRadians(180.0))


    private lateinit var depositFirstSpec: PathChain
    private lateinit var pushAndPick2: PathChain


    private fun buildPaths() {
        depositFirstSpec = follower.pathBuilder()
            .addPath(BezierLine(Point(startPose), Point(depositPose)))
            .setLinearHeadingInterpolation(startPose.heading, depositPose.heading)
            .build()

        pushAndPick2 = follower.pathBuilder()
            .addPath(BezierCurve(Point(depositPose), Point(pushPose1control), Point(pushPose1)))
            .setLinearHeadingInterpolation(depositPose.heading, pushPose1.heading)
            .build()
    }





    val secondRoutine: Command
        get() = SequentialGroup(
            ParallelGroup(
                FollowPath(depositFirstSpec),
                Arm.toHigh
            ),
            ParallelGroup(
                Claw.open,
            ),
            Delay(1.0),
            Arm.toLow
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