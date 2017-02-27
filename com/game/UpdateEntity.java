package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import managers.PlayerManager;
import managers.TextManager;

/**
 * Created by 7804364 on 1/17/2017.
 */
public class UpdateEntity {
    static BitmapFont bbfont = new BitmapFont();

    public static void UpdateGui (Entity e) {
        bbfont.setColor(Constants.getItemRarityColor(e.entityRarity));
        if (e.isAlive()) {
            String entName = (Constants.getItemRarityName(e.entityRarity) + " " + e.entityName);
            GlyphLayout layout = new GlyphLayout(bbfont, entName);
            float lengthName = layout.width;
            TextManager.drawColor(entName, (e.getX() + 16) - (lengthName / 2), e.getY() + 44, bbfont);

            String level = ("Level: " + e.entityLevel);
            GlyphLayout layout2 = new GlyphLayout(TextManager.bfont, level);
            float lengthName2 = layout2.width;
            TextManager.drawName(level, (e.getX() + 16) - (lengthName2 / 2), e.getY() + 56);
        }

    }

    public static void updateDistance (Entity e) {
        if (e.isAlive()) {
            e.entityPos = new Vector2(e.getX(), e.getY());
            e.playerPos = new Vector2(PlayerManager.getPlayer().getX(), PlayerManager.getPlayer().getY());
            e.entityDistance = PlayerManager.playerPos().dst(e.entityPos);
        }
    }
}
