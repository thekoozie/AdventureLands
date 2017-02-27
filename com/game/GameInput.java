package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import managers.EntityManager;
import managers.PlayerManager;
import managers.TextManager;

import java.util.Random;

/**
 * Created by 7804364 on 1/10/2017.
 */
public class GameInput {
    public static Vector2 KeyForce = new Vector2();

    public static void update() {
        KeyForce.x = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            KeyForce.x -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            KeyForce.x += 1;
        }

        KeyForce.y = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            KeyForce.y -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            KeyForce.y += 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            Random random = new Random();
            int randX = random.nextInt(Gdx.graphics.getWidth())+1;
            int randY = random.nextInt(Gdx.graphics.getHeight())+1;
            String monster = Constants.getRandomMonster();
            EntityManager.add(new Entity(new Texture("monsters/"+monster+".png"), randX, randY, 100, monster, Constants.getRandomMonsterLevel(PlayerManager.getPlayer().level),
                    Constants.getRandomMonsterHealth(PlayerManager.getPlayer().level),
                    Constants.getRandomMonsterExp(PlayerManager.getPlayer().level),
                    150, Constants.getRandomMonsterAttackSpeed(),
                    Constants.getRandomMonsterArmor(PlayerManager.getPlayer().level),
                    Constants.getRandomMonsterDamage(PlayerManager.getPlayer().level)));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            PlayerManager.getPlayer().currentExp += 1000000;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.T)) {
            double[] chances = {40, 15, 10, 4, 1, 0.01, 0.002};
            Random r = new Random();
            boolean dropped = false;
            String rarity = "Common";
            for (int i = 0; i < chances.length; i++) {
                if (chances[i] > r.nextDouble() * 100) {
                    if (chances[i] == 40) {
                    } else if (chances[i] == 15) {
                        rarity = "Uncommon";
                    } else if (chances[i] == 10) {
                        rarity = "Rare";
                    } else if (chances[i] == 4) {
                        rarity = "Unique";
                    } else if (chances[i] == 1) {
                        rarity = "Epic";
                    }  else if (chances[i] == 0.01) {
                        rarity = "Legendary";
                    }  else if (chances[i] == 0.002) {
                        rarity = "Mystic";
                    }
                    dropped = true;
                    break;
                }
            }
        }
    }
}
