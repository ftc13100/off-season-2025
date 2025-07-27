package org.firstinspires.ftc.teamcode.example.kotlin
import com.pedropathing.pathgen.Point
import com.pedropathing.follower.Follower
import com.pedropathing.localization.Pose
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
class AutonomousProgram: PedroOpMode(Claw, Slides, Wrist, Arm) {
    private val startPose = Pose(9.0, 60.0, Math.toRadians(0.0))
    private val finishPose = Pose(37.0, 50.0, Math.toRadians(180.0))

    private lateinit var move: PathChain

    fun buildPaths() {
        move = follower.pathBuilder()
            .addPath(BezierLine(Point(startPose), Point(finishPose)))
            .setLinearHeadingInterpolation(startPose.heading, finishPose.heading)
            .build()
    }

    val secondRoutine: Command
        get() = SequentialGroup(
            ParallelGroup(
                FollowPath(move),
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