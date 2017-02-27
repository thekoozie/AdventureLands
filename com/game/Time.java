package com.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by 7804364 on 1/13/2017.
 */
public class Time {
    public static double time = 1d;

    public static int defaultFps = 60;

    public static void update() {
        int actualFps = Gdx.graphics.getFramesPerSecond();
        actualFps = (actualFps == 0) ? 3000 : actualFps;
        time = (double)defaultFps / actualFps;
    }
}
