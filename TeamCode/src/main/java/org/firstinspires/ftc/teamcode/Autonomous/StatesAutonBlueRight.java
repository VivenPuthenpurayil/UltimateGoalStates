package org.firstinspires.ftc.teamcode.Autonomous;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/*
 * Copyright (c) 2020 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Control.AutonomousControl;
import org.firstinspires.ftc.teamcode.Control.Goal;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous(name="States Auton Blue Left", group = "basic")

/*

    OG w/ 1 wobble goal
    UNTESTED

 */


public class StatesAutonBlueRight extends AutonomousControl {
    SkystoneDeterminationPipeline pipeline;

    @Override
    public void runOpMode() throws InterruptedException
    {
        setup(runtime, Goal.setupType.openCV, Goal.setupType.autonomous);
        telemetry.addLine("Start!");
        telemetry.update();

        pipeline = new SkystoneDeterminationPipeline();
        rob.webcam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.h

        rob.webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        rob.webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                rob.webcam.startStreaming(320,240);
            }
        });

        double currTime = runtime.milliseconds();
//hi
        waitForStart();

        if (opModeIsActive())
        {
            double distanceBack = rob.Back.getDistance(DistanceUnit.CM);
            double distanceFront = rob.Front.getDistance(DistanceUnit.CM);
            double distanceLeft = rob.Left.getDistance(DistanceUnit.CM);
            double distanceRight = rob.Right.getDistance(DistanceUnit.CM);

            double speed = 0.7;
            double speed2 = 0.9;

            do{
                rob.driveTrainMovement(0.7, Goal.movements.forward);
                distanceBack = rob.Back.getDistance(DistanceUnit.INCH);
                telemetry.addData("cm Back", "%.2f cm", distanceBack);
                telemetry.update();

            }
            while(distanceBack >1000 || distanceBack < 33 || Double.compare(distanceBack, Double.NaN) == 0 && opModeIsActive());

            rob.stopDrivetrain();

            sleep(100);

            while(opModeIsActive() && runtime.milliseconds() < 3000) {
                telemetry.addData("Analysis", pipeline.getAnalysis());
                telemetry.addData("Position", pipeline.position);
                telemetry.addData("Value", pipeline.value);
                telemetry.update();

                // Don't burn CPU cycles busy-looping in this sample
                sleep(50);
            }

            // move to shooting pos
            do{
                rob.driveTrainMovement(0.7, Goal.movements.forward);
                distanceBack = rob.Back.getDistance(DistanceUnit.INCH);
                telemetry.addData("cm Back", "%.2f cm", distanceBack);
                telemetry.update();

            }
            while(distanceBack >1000 || distanceBack < 150 || Double.compare(distanceBack, Double.NaN) == 0 && opModeIsActive());

            rob.stopDrivetrain();

            sleep(100);

            do{
                rob.driveTrainMovement(speed, Goal.movements.left);
                distanceLeft = rob.Left.getDistance(DistanceUnit.INCH);
//                telemetry.addData("cm Back", "%.2f cm", distanceBack);
                telemetry.addData("cm Left", "%.2f cm", distanceLeft);
                telemetry.update();

            }
            while(distanceLeft >1000 || distanceLeft > 67 || Double.compare(distanceLeft, Double.NaN) == 0 && opModeIsActive());

            rob.stopDrivetrain();
            sleep(100);

            shoot();

//            pipeline.value = 4;

            if (pipeline.value == 4){

                do{
                    rob.driveTrainMovement(speed, Goal.movements.left);
                    distanceLeft = rob.Left.getDistance(DistanceUnit.INCH);
//                  telemetry.addData("cm Back", "%.2f cm", distanceBack);
                    telemetry.update();

                }
                while(distanceLeft >1000 || distanceLeft > 12 || Double.compare(distanceLeft, Double.NaN) == 0 && opModeIsActive());

                rob.stopDrivetrain();
                sleep(100);

                do{
                    rob.driveTrainMovement(speed, Goal.movements.forward);

                    distanceFront = rob.Front.getDistance(DistanceUnit.INCH);
                    telemetry.addData("cm Back", "%.2f cm", distanceBack);
                    telemetry.update();

                }
                while(distanceFront >1000 || distanceFront > 12 || Double.compare(distanceBack, Double.NaN) == 0 && opModeIsActive());

                rob.stopDrivetrain();

                // rotate 180 degrees
                rob.absturn((float) (180), Goal.turnside.cw, 0.9, Goal.axis.center);

                dropgoal();

                rob.driveTrainEncoderMovement(speed, 15, 10, 0, Goal.movements.forward);

                rob.stopDrivetrain();
                sleep(100);

                do {
                    rob.driveTrainMovement(speed, Goal.movements.forward);

                    distanceFront = rob.Front.getDistance(DistanceUnit.INCH);
//                    telemetry.addData("cm Back", "%.2f cm", dist);
                    telemetry.update();
                }
                while(distanceFront >1000 || distanceFront > 80 || Double.compare(distanceFront, Double.NaN) == 0 && opModeIsActive());

                rob.stopDrivetrain();
                sleep(100);

                // rotate 180 degrees
                rob.absturn((float) (180), Goal.turnside.cw, 0.9, Goal.axis.center);

            }

            else if(pipeline.value == 1){
                //dropping off the first goal
                do{
                    rob.driveTrainMovement(speed2, Goal.movements.forward);

                    distanceBack = rob.Back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("cm Back", "%.2f cm", distanceBack);
                    telemetry.update();

                }
                while(distanceBack >1000 || distanceBack < 82 || Double.compare(distanceBack, Double.NaN) == 0 && opModeIsActive());

                rob.stopDrivetrain();
                sleep(200);

                dropgoal();

                rob.driveTrainEncoderMovement(speed2, 5, 5, 0, Goal.movements.forward);
                sleep(200);

                do{
                    rob.driveTrainMovement(speed2, Goal.movements.left);

                    distanceLeft = rob.Left.getDistance(DistanceUnit.INCH);
                    telemetry.addData("cm front", "%.2f cm", distanceRight);
                    telemetry.update();

                }
                while(distanceLeft >1000 || distanceLeft > 10 || Double.compare(distanceLeft, Double.NaN) == 0 && opModeIsActive());

                rob.stopDrivetrain();
                sleep(200);

                do{
                    rob.driveTrainMovement(speed2, Goal.movements.backward);

                    distanceBack = rob.Back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("cm Back", "%.2f cm", distanceFront);
                    telemetry.update();

                }
                while(distanceFront >1000 || distanceBack > 80 || Double.compare(distanceFront, Double.NaN) == 0 && opModeIsActive());
            }

            else{
                //dropping off the first goal

                do{
                    rob.driveTrainMovement(speed, Goal.movements.left);
                    distanceLeft = rob.Left.getDistance(DistanceUnit.INCH);
//                  telemetry.addData("cm Back", "%.2f cm", distanceBack);
                    telemetry.update();

                }
                while(distanceLeft >1000 || distanceLeft > 12 || Double.compare(distanceLeft, Double.NaN) == 0 && opModeIsActive());

                rob.stopDrivetrain();
                sleep(100);

                do{
                    rob.driveTrainMovement(speed, Goal.movements.forward);

                    distanceBack = rob.Back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("cm Back", "%.2f cm", distanceBack);
                    telemetry.update();

                }
                while(distanceBack >1000 || distanceBack < 90 || Double.compare(distanceBack, Double.NaN) == 0 && opModeIsActive());

                rob.stopDrivetrain();
                sleep(100);

                dropgoal();

                do{
                    rob.driveTrainMovement(speed, Goal.movements.backward);

                    distanceBack = rob.Back.getDistance(DistanceUnit.INCH);
                    telemetry.addData("cm Back", "%.2f cm", distanceBack);
                    telemetry.update();

                }
                while(distanceBack >1000 || distanceBack > 80 || Double.compare(distanceBack, Double.NaN) == 0 && opModeIsActive());

            }

            rob.stopDrivetrain();
            sleep(100);

            // move to white line
            rob.driveTrainEncoderMovement(1, 15, 10, 0, Goal.movements.forward);
        }
    }

    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        /*
         * An enum to define the skystone position
         */

        public enum RingPosition
        {
            FOUR,
            ONE,
            NONE
        }

        public int value = 0;
        /*
         * Some color constants
         */

        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */

        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(100,98);

        static final int REGION_WIDTH = 100;
        static final int REGION_HEIGHT = 100;

        final int FOUR_RING_THRESHOLD = 147;
        final int ONE_RING_THRESHOLD = 135;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */

        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;

        // Volatile since accessed by OpMode thread w/o synchronization
        public volatile RingPosition position = RingPosition.FOUR;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */

        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = RingPosition.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = RingPosition.FOUR;
                value = 4;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = RingPosition.ONE;
                value = 1;
            }else{
                position = RingPosition.NONE;
                value = 0;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }
    }
}
