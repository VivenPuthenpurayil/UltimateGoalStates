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

        setup(runtime, Goal.setupType.autonomous);
        telemetry.addLine("Start!");
        telemetry.update();

        if (opModeIsActive()){
            rob.fly.setPower(-.62);
            sleep(3000);
            rob.lifter.setPosition(.86);
            sleep(250);
            for (int i = 0; i <= 2; i++) {
                rob.fly.setPower(-0.64);
                sleep(300);
                rob.whack.setPosition(0.42);
                sleep(500);
                rob.whack.setPosition(0);
                sleep(750);
            }
            rob.fly.setPower(0);
            rob.lifter.setPosition(.98);
            sleep(200);
        }


    }
}
