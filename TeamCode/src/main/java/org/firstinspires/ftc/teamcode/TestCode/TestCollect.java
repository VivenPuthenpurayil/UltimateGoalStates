package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Goal;

@Autonomous(name="Test Collect", group = "basic")
public class TestCollect extends AutonomousControl {

    @Override
    public void runOpMode() throws InterruptedException {

        setup(runtime, Goal.setupType.collectionsystem);
        telemetry.addLine("Start!");
        telemetry.update();

        while (opModeIsActive()) {
            rob.collection.setPower(.8);
            sleep(100);
            rob.collectLow.setPower(.8);
            sleep(100);
        }

    }
}