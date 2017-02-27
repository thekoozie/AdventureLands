package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import items.Item;
import items.ItemHandler;
import managers.EntityManager;
import managers.IndicatorTextManager;
import managers.PlayerManager;
import managers.TextManager;

import java.awt.*;
import java.util.Random;

/**
 * Created by 7804364 on 1/15/2017.
 */
public class Entity extends Group {
    public float entitySpeed;
    public String entityName;
    public int entityRarity;
    public int entityLevel;
    public int entityCurrentHealth;
    public int entityExp;
    public int entityRange;
    public float entityAttackSpeed;
    public int entityMaxHealth;
    public int entityArmor;
    public int entityDamage;

    public float entityDistance;
    public Vector2 entityPos;
    public Vector2 playerPos;
    float x;
    float y;
    public boolean withinDistance;

    private float time;
    private HealthBar healthBar;

    private Stage stageWorld;

    private Image playerImage;


    public Entity (Texture texture, float posX, float posY, float spd, String name, int level, int currhealth, int ex, int entRange, float attackspeed, int entarmor, int entDamage) {
        this.stageWorld = Constants.WORLD_STAGE;
        this.playerImage = new Image(texture);
        this.playerImage.setPosition(posX, posY);

        this.addActor(playerImage);
        this.stageWorld.addActor(this);

        this.entitySpeed = spd;
        this.entityName = name;
        this.entityLevel = level;
        this.entityRarity = Constants.getEntityRarity();
        this.entityCurrentHealth = currhealth * this.entityRarity;
        this.entityMaxHealth = currhealth* this.entityRarity;
        this.entityExp = ex * this.entityRarity*4;
        this.entityRange = entRange;
        this.entityAttackSpeed = attackspeed;
        this.entityArmor = entarmor * this.entityRarity;
        this.entityDamage = entDamage * this.entityRarity;
        this.x = posX;
        this.y = posY;

        System.out.println("Rarity: " + this.entityRarity);

        healthBar = new HealthBar(this, new Texture("ui/enemyhealthbg.png"),  new Texture("ui/enemyhealthfg.png"));
    }

    public void dispose() {

    }

    public void update() {
        entityDeath();
        moveToPlayer();
        UpdateEntity.updateDistance(this);
        UpdateEntity.UpdateGui(this);
        damagePlayer();
        healthBar.update();
    }

    public void render (Batch batch) {
        this.setPosition(getX(), getY());
        healthBar.render(batch);
    }

    public void moveToPlayer () {
        if (this.entityDistance <= entityRange) {
            if (this.entityDistance < 33) {
                withinDistance = false;
            } else {
                withinDistance = true;
            }
        } else {
            withinDistance = false;
        }

        if (this.withinDistance) {
            float targetX = PlayerManager.getPlayer().getX();
            float targetY = PlayerManager.getPlayer().getY();
            float spriteX = this.getX();
            float spriteY = this.getY();
            float x2 = this.getX();
            float y2 = this.getY();
            float angle;
            angle = (float) Math
                    .atan2(targetY - spriteY, targetX - spriteX);
            x2 += (float) Math.cos(angle) * this.entitySpeed
                    * Gdx.graphics.getDeltaTime();
            y2 += (float) Math.sin(angle) * this.entitySpeed
                    * Gdx.graphics.getDeltaTime();
            this.setPosition(x2, y2);
        }
    }

    public void entityTakeDamage (int damage) {
        if (this.isAlive()) {
            this.entityCurrentHealth -= (damage - this.entityArmor);
            new TextManager.DamageText(this.getX() + this.getWidth()/2, this.getY() + this.getHeight()/2, "-"+(damage - this.entityArmor));
        }
    }

    public void entityDeath () {
        if (!this.isAlive()) {
            Random ran = new Random();
            int x = ran.nextInt(32) + 1;
            PlayerManager.getPlayer().addExp(this.entityExp);
            new TextManager.ExpText(this.getX() + x, this.getY() + x, "+"+this.entityExp+" Exp");
            int itemRarity = Constants.getItemRarity();
            if (itemRarity != 0) {
                ItemHandler.add(new Item(this.getX(), this.getY(), "item.png", "Dagger", stageWorld, this.entityLevel,
                        Constants.getRandomItemDamage(this.entityLevel),
                        Constants.getRandomItemHealth(this.entityLevel),
                        Constants.getRandomItemArmor(this.entityLevel),
                        "Weapon",
                        Constants.getRandomMonsterAttackSpeed(),
                        itemRarity));
            }
            EntityManager.setToDestroy(this);
        }
    }

    public boolean isAlive () {
        if (this.entityCurrentHealth > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void damagePlayer () {
        if (this.entityDistance <= 50) {
            if (PlayerManager.getPlayer().isAlive()) {
                time ++;
                if (time >= (this.entityAttackSpeed * Gdx.graphics.getFramesPerSecond())) {
                    time = 0;
                    PlayerManager.getPlayer().playerTakeDamage(entityDamage);
                }
            }
        }
    }

    private class HealthBar extends Sprite {
        private Sprite enemyHealthBarBg;
        private Sprite enemyHealthBarFg;
        private Entity owner;
        private final short buffer = 56;

        public HealthBar (Entity owner, Texture healthBarBg, Texture healthBarFg) {
            this.owner = owner;

            enemyHealthBarBg = new Sprite(healthBarBg);
            enemyHealthBarFg = new Sprite(healthBarFg);

            enemyHealthBarBg.setX(owner.getX());
            enemyHealthBarBg.setY(owner.getY() + owner.getHeight() + buffer);
            enemyHealthBarFg.setX(owner.getX());
            enemyHealthBarFg.setY(owner.getY() + owner.getHeight() + buffer);
            enemyHealthBarFg.setOrigin(0, 0);
        }

        public void update () {
            enemyHealthBarBg.setX(owner.getX());
            enemyHealthBarBg.setY(owner.getY() + owner.getHeight() + buffer);
            enemyHealthBarFg.setX(owner.getX());
            enemyHealthBarFg.setY(owner.getY() + owner.getHeight() + buffer);

            enemyHealthBarFg.setScale(owner.entityCurrentHealth / (float) owner.entityMaxHealth, 1f);
        }

        public void render (Batch batch) {
            enemyHealthBarBg.draw(batch);
            enemyHealthBarFg.draw(batch);
            checkMouseHover();
        }
    }

    public void checkMouseHover () {
        float minx = this.x;
        float miny = this.y;
        float maxx = minx + this.getWidth();
        float maxy = miny + this.getHeight();

        int mousex = MouseInfo.getPointerInfo().getLocation().x;
        int mousey = MouseInfo.getPointerInfo().getLocation().y;

        if ((mousex >= minx && mousex <= maxx) && (mousey >= miny && mousey <= maxy)) {
            System.out.println("Clicked on the sprite.");
        }
    }
}
