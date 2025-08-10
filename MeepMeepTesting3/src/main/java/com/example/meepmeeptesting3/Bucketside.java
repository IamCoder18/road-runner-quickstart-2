package com.example.meepmeeptesting3;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Bucketside {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.6295805086)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35.5, -61.5, Math.toRadians(90)))

                        .strafeTo(new Vector2d(-48,-46))
                        .waitSeconds(1)
                        .strafeToLinearHeading(new Vector2d(-51,-51),Math.toRadians(225))
                        .waitSeconds(2)
                        .strafeToLinearHeading(new Vector2d(-58,-46), Math.toRadians(90))
                        .waitSeconds(1)
                        .strafeToLinearHeading(new Vector2d(-51,-51),Math.toRadians(225))
                        .strafeToLinearHeading(new Vector2d(-58,-8),Math.toRadians(90))
                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}