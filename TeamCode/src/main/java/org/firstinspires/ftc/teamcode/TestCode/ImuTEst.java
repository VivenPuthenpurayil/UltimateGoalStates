package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Goal;

@Autonomous(name="IMU test", group = "basic")

public class ImuTEst extends AutonomousControl {

    Orientation angles;

    @Override

    public void runOpMode() throws InterruptedException {

        setup(runtime, Goal.setupType.autonomous);
        telemetry.addLine("Start!");

        telemetry.update();

//        if (opModeIsActive()) {
//            angles = rob.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//            if (angles.firstAngle < 0) {
//                rob.teleturn((float) (Math.abs(angles.firstAngle)), Goal.turnside.cw, 0.9, Goal.axis.center);
//            }
//            else if (angles.firstAngle > 0) {
//                rob.teleturn((float) (Math.abs(angles.firstAngle)), Goal.turnside.ccw, 0.9, Goal.axis.center);
//
//            }


        if (rob.Right.getDistance(DistanceUnit.INCH)> 36){
            telemetry.addData("angle", (float) (Math.atan(rob.Front.getDistance(DistanceUnit.INCH)/(rob.Right.getDistance(DistanceUnit.INCH)- 36))*180/Math.PI));
            telemetry.update();
            sleep(1000);
          //  rob.Actualteleturn((float) (90- (Math.atan(rob.Front.getDistance(DistanceUnit.INCH)/(rob.Right.getDistance(DistanceUnit.INCH)- 36))*180/Math.PI)),  Goal.turnside.cw, 0.9, Goal.axis.center);
            telemetry.addData("angle", (float) (Math.atan(rob.Front.getDistance(DistanceUnit.INCH)/(rob.Right.getDistance(DistanceUnit.INCH)- 36))*180/Math.PI));
        }

        else {
            telemetry.addData("angle",  (float)(Math.atan(rob.Front.getDistance(DistanceUnit.INCH)/(36-rob.Right.getDistance(DistanceUnit.INCH)))*180/Math.PI));
            telemetry.update();
            sleep(1000);
            //rob.Actualteleturn((float)(90 - (Math.atan(rob.Front.getDistance(DistanceUnit.INCH)/(36-rob.Right.getDistance(DistanceUnit.INCH)))*180/Math.PI)),  Goal.turnside.ccw, 0.9, Goal.axis.center);

        }

            //  rob.driveTrainIMUSwingTurnMovement(0.4, Goal.movements.backward, 3000, 90, 0.02, Goal.turnside.cw);


        }
    }


