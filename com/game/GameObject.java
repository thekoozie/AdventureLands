package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by 7804364 on 1/10/2017.
 */
public interface GameObject {
    public void dispose();

    public void update ();

    public void render (Batch batch);
}
