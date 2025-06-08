package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MovementTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.6)
                .build();

        Pose2d initialStartPose = new Pose2d(0, 0, Math.toRadians(0));

        myBot.runAction(myBot.getDrive().actionBuilder(initialStartPose)
                .strafeTo(new Vector2d(24, 0))
                .strafeTo(new Vector2d(0, 0))
                .strafeTo(new Vector2d(0, 24))
                .strafeTo(new Vector2d(0, 0))
                .strafeTo(new Vector2d(-24, 0))
                .strafeTo(new Vector2d(-24, -24))
                .strafeTo(new Vector2d(24, -24))
                .strafeTo(new Vector2d(24, 24))
                .strafeTo(new Vector2d(-24, 24))
                .strafeTo(new Vector2d(-24, 0))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}