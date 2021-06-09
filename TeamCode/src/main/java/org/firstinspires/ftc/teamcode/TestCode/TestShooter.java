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

        setup(runtime, Goal.setupType.shooter);
        telemetry.addLine("Start!");
        telemetry.update();

        while (opModeIsActive()){
            rob.feederRight.setPower(1);
            rob.feederLeft.setPower(-1);
            rob.shooterRight.setPower(-0.7);
            rob.shooterLeft.setPower(-0.7);
        }


    }
}
