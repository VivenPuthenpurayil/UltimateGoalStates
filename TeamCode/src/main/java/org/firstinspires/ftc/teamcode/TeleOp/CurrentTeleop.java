
package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Control.Goal;
import org.firstinspires.ftc.teamcode.Control.TeleOpControl;
@TeleOp(name="vivaniskindasped", group = "Main")
public class CurrentTeleop extends TeleOpControl {
    public static final double rotationSpeed = 0.4;
    public static boolean flywheelon = false;
    Orientation angles;
    double init;
    @Override
    public void runOpMode() throws InterruptedException {
        boolean yToggle = false;
        boolean xToggle = false;
        boolean move_to_pos = false;
        double angle = 0;
        setup(runtime, Goal.setupType.teleop);
        rob.lifter.setPosition(.90);
        sleep(500);
        waitForStart();
        while (opModeIsActive()){
            double IMUOrientB = -1;
            double distanceRight = rob.Right.getDistance(DistanceUnit.CM);
            telemetry.addData("back", "%.2f cm", rob.Back.getDistance(DistanceUnit.CM));
            telemetry.addData("angle", "%.2f",IMUOrientB);
            telemetry.addData("orient", rob.getDirection());
            IMUOrientB = rob.getDirection();
            // telemetry.addData("angle", "%.2f",(Math.atan((distanceFront-distanceBack)/6.6142)*180)/(3.1415));
            telemetry.update();
            standardGamepadData();
            if(gamepad2.x){
                xToggle = !xToggle;
            }
            if (gamepad1.b){
                yToggle = !yToggle;
            }
            if (!yToggle) {
                // BR - Third hole
                if (g(0)) {
                    rob.driveTrainMovement(fb, Goal.movements.forward);
                } else if (g(2)) {
                    rob.driveTrainMovement(fb, Goal.movements.backward);
                } else if (g(3)) {
                    rob.driveTrainMovement(rl, Goal.movements.right);
                } else if (g(1)) {
                    rob.driveTrainMovement(rl, Goal.movements.left);
                }
                else if (g(4)) {
                    rob.driveTrainMovement(diagonalSpeed, Goal.movements.br);
                }else if (g(5)) {
                    rob.driveTrainMovement(diagonalSpeed, Goal.movements.bl);
                }else if (g(6)) {
                    rob.driveTrainMovement(diagonalSpeed, Goal.movements.tl);
                }else if (g(7)) {
                    rob.driveTrainMovement(diagonalSpeed, Goal.movements.tr);
                }
                else if (g(8)) {
                    rob.driveTrainMovement(1, Goal.movements.ccw);
                } else if (g(9)) {
                    rob.driveTrainMovement(1, Goal.movements.cw);
                } else {
                    rob.stopDrivetrain();
                }
            }
            else {
                if (g(0)) {
                    rob.driveTrainMovement(0.3, Goal.movements.forward);
                } else if (g(2)) {
                    rob.driveTrainMovement(0.3, Goal.movements.backward);
                } else if (g(3)) {
                    rob.driveTrainMovement(0.3, Goal.movements.right);
                } else if (g(1)) {
                    rob.driveTrainMovement(0.3, Goal.movements.left);
                }
                else if (g(4)) {
                    rob.driveTrainMovement(0.3, Goal.movements.br);
                }else if (g(5)) {
                    rob.driveTrainMovement(0.3, Goal.movements.bl);
                }else if (g(6)) {
                    rob.driveTrainMovement(0.3, Goal.movements.tl);
                }else if (g(7)) {
                    rob.driveTrainMovement(0.3, Goal.movements.tr);
                }
                else if (g(8)) {
                    rob.driveTrainMovement(.3, Goal.movements.ccw);
                } else if (g(9)) {
                    rob.driveTrainMovement(.3, Goal.movements.cw);
                } else {
                    rob.stopDrivetrain();
                }
            }
            // gamepad functions - lift, whack, and fly(x), wobblegoal up(y), wobblegoal down(a),
            // wobblegoal pincher in(rb), wobblegoal pincher out(rb),
            if(gamepad2.dpad_up){
                rob.claw.setPosition(1);
                // TEST - puts thingy up
            }
            else if(gamepad2.dpad_down){
                rob.claw.setPosition(0);
                // TEST - puts thingy down
            }

            if(gamepad2.dpad_right){
                // TEST - adjust this
                rob.pinch.setPosition(0);
            }
            else if(gamepad2.dpad_left){
                // TEST - adjust this
                rob.pinch.setPosition(1);
            }
            if (gamepad1.y) {
                // flywheelon = true; h
                move_to_pos = true;
            }
            if (gamepad2.x) {
                flywheelon = true;
            }
            if (flywheelon) {
                rob.fly.setPower(-.7);
            }
            if(gamepad2.y){
                rob.pinch.setPosition(1);
                sleep(500);
                rob.encoderMovement(0.75, 8, 20, 0, Goal.movements.backward);
                rob.driveTrainEncoderMovement(0.75, 10, 10, 0, Goal.movements.backward);
            }
            if(gamepad2.a) {
                shoot();
                /*
                rob.motorFL.setPower(0);
                rob.motorBL.setPower(0);
                rob.motorFR.setPower(0);
                rob.motorBR.setPower(0);
                sleep(250);
                rob.lifter.setPosition(.85);
                sleep(500);
                for (int i = 0; i <= 2; i++) {
                    if(gamepad2.b){
                        emergencystopDriver2();
                        break;
                    }
//                    rob.fly.setPower(-0.625);
//                    sleep(200);
                    if(gamepad2.b){
                        emergencystopDriver2();
                        break;
                    }
                    rob.whack.setPosition(0.45);
                    sleep(750);
                    if(gamepad2.b){
                        emergencystopDriver2();
                        break;
                    }
                    rob.whack.setPosition(0);
                    sleep(1000);
                    if(gamepad2.b){
                        emergencystopDriver2();
                        break;
                    }
                }


//                for (int i = 0; i <= 1; i++) {
//
//                    if(gamepad2.b){
//                        emergencystopDriver2();
//                        break;
//                    }
//
//                    rob.fly.setPower(-0.6);
//                    sleep(500);
//
//                    if(gamepad2.b){
//                        emergencystopDriver2();
//                        break;
//                    }
//
//                    rob.whack.setPosition(0.45);
//                    sleep(500);
//
//                    if(gamepad2.b){
//                        emergencystopDriver2();
//                        break;
//                    }
//
//                    rob.whack.setPosition(0);
//                    sleep(500);
//
//                    if(gamepad2.b){
//                        emergencystopDriver2();
//                        break;
//                    }
//                }
                rob.fly.setPower(0);
                flywheelon = false;
                rob.lifter.setPosition(.98);
                sleep(200);
                */
            }
            if(gamepad2.b){
                emergencystopDriver2();
            }
            if (gamepad1.left_trigger>.2){
                rob.collection.setPower(-1);
            }
            else if (gamepad1.right_trigger > .2){
                rob.collection.setPower(1);
            }
            else if(gamepad1.x){
                init = rob.getDirection();
            }
            else {
                rob.collection.setPower(0);
            }
            if (move_to_pos) {
                if (distanceRight > 1000) {
                    continue;
                }
                angles = rob.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                if (angles.firstAngle < 0) {
                    rob.teleturn((float) (Math.abs(angles.firstAngle)), Goal.turnside.cw, 0.9, Goal.axis.center);
                }
                else if (angles.firstAngle > 0) {
                    rob.teleturn((float) (Math.abs(angles.firstAngle)), Goal.turnside.ccw, 0.9, Goal.axis.center);
                }
                sleep(200);
////                rob.driveTrainEncoderMovement(1,(131 - rob.Back.getDistance((DistanceUnit.CM)))/2.54,20,0,Goal.movements.forward);
////                rob.stopDrivetrain();
////                rob.driveTrainEncoderMovement(1,((rob.Right.getDistance((DistanceUnit.CM))-45)/2.54),20,0,Goal.movements.right);
////                rob.stopDrivetrain();
                moveJimmy(153/2.54, true, Goal.movements.forward, rob, rob.Back);
                moveJimmy(153/2.54, false, Goal.movements.backward, rob, rob.Back);
                moveJimmy(45/2.54, true, Goal.movements.left, rob, rob.Right);
                moveJimmy(45/2.54, false, Goal.movements.right, rob, rob.Right);
                move_to_pos = false;
            }
        }
    }
    public void emergencystopDriver2() throws InterruptedException {
        flywheelon = false;
        rob.fly.setPower(0);
        sleep(200);
        //  rob.claw.setPower(0);
        sleep(200);
        rob.lifter.setPosition(0.98);
        sleep(200);
        rob.whack.setPosition(0.05);
    }
    public void emergencystopDriver1() throws InterruptedException {
        rob.motorFL.setPower(0);
        sleep(200);
        rob.motorBL.setPower(0);
        sleep(200);
        rob.motorFR.setPower(0);
        sleep(200);
        rob.motorBR.setPower(0);
        sleep(200);
        rob.collection.setPower(0);
        sleep(200);
    }
    public void shoot() {
        // shoot
        rob.shooterRight.setPower(-0.8);
        rob.shooterLeft.setPower(-0.8);
        rob.feederRight.setPower(0.5);
        rob.feederLeft.setPower(0.5);
        sleep(1000);

        for (int i = 0; i <= 2; i++) {
            rob.feederRight.setPower(0.5);
            rob.feederLeft.setPower(0.5);
            sleep(250);
            rob.feederRight.setPower(0);
            rob.feederLeft.setPower(0);
            sleep(250);
        }

        rob.shooterRight.setPower(0);
        rob.shooterLeft.setPower(0);
        rob.feederRight.setPower(0);
        rob.feederLeft.setPower(0);
        sleep(100);
    }
    // if true, do movements opposite sensor. If false, do movement same as sensor
    public void moveJimmy(double distance, boolean less, Goal.movements dir, Goal rob, ModernRoboticsI2cRangeSensor sensor) throws InterruptedException {
        double dist = sensor.getDistance(DistanceUnit.INCH);
        if (less && dist < distance) {
            do{
                dist = drive(distance, dir, rob, sensor);
            } while(dist > 1000 || dist < distance || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
            rob.stopDrivetrain();
        }
        else if (!less && dist > distance) {
            do{
                dist = drive(distance, dir, rob, sensor);
            } while(dist > 1000 || dist > distance || Double.compare(dist, Double.NaN) == 0 && opModeIsActive());
            rob.stopDrivetrain();
        }
        sleep(200);
    }
    public double drive(double distance, Goal.movements dir, Goal rob, ModernRoboticsI2cRangeSensor sensor) throws InterruptedException {
        double dist = sensor.getDistance(DistanceUnit.INCH);
        double speed = findSpeed(distance, dist);
        rob.driveTrainMovement(speed, dir);
        return sensor.getDistance(DistanceUnit.INCH);
    }
    public double findSpeed(double distance, double dist) {
        return Math.min(Math.max(0.25, (Math.abs(distance - dist)/30)), 1);
    }
}

