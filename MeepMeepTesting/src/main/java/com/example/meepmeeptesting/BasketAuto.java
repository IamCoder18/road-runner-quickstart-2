package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BasketAuto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.6)
                .build();

        Pose2d startPose = new Pose2d(-35.5, -61.5, Math.toRadians(90));

        myBot.runAction(myBot.getDrive().actionBuilder(startPose)
                .splineTo(new Vector2d(-50,-40), Math.toRadians(90))
                .waitSeconds(2)
                .splineTo(new Vector2d(-50,-45), Math.toRadians(225))
                .waitSeconds(2)
                .splineTo(new Vector2d(-55,-40), Math.toRadians(90))
                .waitSeconds(2)
                .splineTo(new Vector2d(-60,-40), Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}