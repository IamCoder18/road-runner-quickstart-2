package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SampleSideAuto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.6295805086)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(11.5, -61.5, Math.toRadians(90)))
                // remember, at 90 degrees, the arm protrudes 7 inches outside the robot's body
                            .strafeTo(new Vector2d(7,-39))
                            .waitSeconds(0.2) //score on obervation zone
                            .strafeTo(new Vector2d(0,-39))
                            //.strafeTo(new Vector2d(11.5,-50))

                           .splineToConstantHeading(new Vector2d(36,-36),Math.toRadians(90))
                           .splineToConstantHeading(new Vector2d(36,0),Math.toRadians(90))
                           .strafeTo(new Vector2d(42,0))
                           .strafeTo(new Vector2d(42,-50)) // first sample into obervation zone
                           .strafeTo(new Vector2d(42,0))
                           .strafeTo(new Vector2d(56,0))
                           .strafeTo(new Vector2d(56,-50)) // Second sample into obervation zone
                           .strafeTo(new Vector2d(56,0))
                           .strafeTo(new Vector2d(61,0))
                           .strafeTo(new Vector2d(61,-50)) // third sample into obervation zone

                           .strafeToLinearHeading(new Vector2d(48,-30),Math.toRadians(270))
                           .waitSeconds(1)
                           .strafeTo(new Vector2d(48,-56))
                           .strafeToLinearHeading(new Vector2d(7,-49),Math.toRadians(90))
                           .strafeTo(new Vector2d(7,-39)) //score
                           .strafeToLinearHeading(new Vector2d(48,-46),Math.toRadians(270))
                           .strafeTo(new Vector2d(48,-56))
//                           .strafeTo(new Vector2d(42,-55)) // collects from human player
//                        .splineTo(new Vector2d(48,-48),Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
