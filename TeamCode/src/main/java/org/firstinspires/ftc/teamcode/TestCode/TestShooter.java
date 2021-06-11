package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Goal;

@Autonomous(name="Test Shooter", group = "basic")
public class TestShooter extends AutonomousControl {

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        setup(runtime, Goal.setupType.collectionsystem, Goal.setupType.shooter);
        telemetry.addLine("Start!");
        telemetry.update();

        while (opModeIsActive()) {
            rob.collection.setPower(.8);
            rob.collectLow.setPower(.8);
            rob.shooterRight.setPower(-0.8);
            rob.shooterLeft.setPower(-0.8);
            rob.feederRight.setPower(0.5);
            rob.feederLeft.setPower(0.5);

        }

    }
}