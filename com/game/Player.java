package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import managers.EntityManager;
import managers.PlayerManager;
import managers.TextManager;

/**
 * Created by 7804364 on 1/14/2017.
 */
public class Player extends Sprite {
    public float speed;

    public int level;
    public int currentExp;
    public int maxExp;
    public int currentHealth;
    public float attackSpeed;

    public int maxHealth;
    public int armor;
    public int damage;
    public String name;

    private float time;
    private float healTimer;

    public Rectangle playerRect = new Rectangle();

    public Player(Texture texture, float x, float y, float speed, int level, int currentExp, int maxExp, int currentHealth, int maxHealth, int armor, int damage, String name, float atkspd) {
        this.setTexture(texture);
        this.setPosition(x,y);
        this.speed = speed;
        this.level = level;
        this.currentExp = currentExp;
        this.maxExp = maxExp;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.armor = armor;
        this.damage = damage;
        this.name = name;
        this.attackSpeed = atkspd;

        playerRect = new Rectangle(x,y,
                texture.getWidth(),
                texture.getHeight());
    }
    public void update() {
        GlyphLayout layout = new GlyphLayout(TextManager.bfont, name);
        float lengthName = layout.width;
        TextManager.drawName(name, (getX() + 16) - (lengthName/2), getY() + 44);

        damageEntity();

        if (getX() >= 0) {
            translateX((float) (GameInput.KeyForce.x * speed * Time.time));
        } else {
            setX(0);
        }
        if (getX() >= 1888) {
            setX(1888);
        }
        if (getY() >= 0) {
            translateY((float) (GameInput.KeyForce.y * speed * Time.time));
        } else {
            setY(0);
        }
        if (getY() >= 1056) {
            setY(1056);
        }
        healPlayer();
    }
    @Override
    public void draw (Batch batch) {
        batch.draw(getTexture(), getX(), getY());
    }

    public Player getPlayer () {
        return this;
    }

    public boolean isAlive () {
        if (this.currentHealth > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void healPlayer () {
        if (this.isAlive() && this.currentHealth < this.maxHealth) {
            healTimer++;
            if (healTimer >= (Gdx.graphics.getFramesPerSecond() * 1)) {
                healTimer = 0;
                int healAmount = (int) (Math.ceil(this.maxHealth * 0.01));
                this.currentHealth += healAmount;
                new TextManager.HealText(PlayerManager.getPlayer().getX() + (PlayerManager.getPlayer().getWidth() / 2),
                        PlayerManager.getPlayer().getY() + PlayerManager.getPlayer().getHeight() / 2, "+" + (healAmount) + " HP");
            }
        }

        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
    }

    public void addExp (int e) {
        this.currentExp += e;
    }

    public void damageEntity () {
        if (EntityManager.getClosestEntityDistance(PlayerManager.playerPos()) <= 50) {
            if (EntityManager.getClosestEntity().isAlive()) {
                time ++;
                if (time >= (PlayerManager.getPlayer().attackSpeed * Gdx.graphics.getFramesPerSecond())) {
                    time = 0;
                    EntityManager.getClosestEntity().entityTakeDamage(PlayerManager.getPlayer().damage);
                }
            }
        }
    }

    public void playerTakeDamage (int damage) {
        if (this.isAlive()) {
            this.currentHealth -= (damage - this.armor);
            new TextManager.DamageText(PlayerManager.getPlayer().getX() + PlayerManager.getPlayer().getWidth()/2, PlayerManager.getPlayer().getY() + PlayerManager.getPlayer().getHeight()/2, "-"+(damage - this.armor));
        }
    }
}
