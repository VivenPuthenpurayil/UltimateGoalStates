package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Goal;

@Autonomous(name="Test Wobble", group = "basic")
public class TestWobble extends AutonomousControl {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        setup(runtime, Goal.setupType.wobblegoal);

        if (opModeIsActive()){
//            rob.claw.setPosition(0);
//            sleep(1000);
//            rob.pinch.setPosition(0.8);
//            sleep(1000);
//            rob.pinch.setPosition(0);
//            sleep(1000);
//            rob.claw.setPosition(0.6);
//            sleep(1000);

            dropgoal();

            sleep(5000);

            pickupgoal();

        }
//hi

    }
}