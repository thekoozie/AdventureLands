package com.game;

/**
 * Created by 7804364 on 1/17/2017.
 */
public class UpdatePlayer {
    public static void UpdatePlayer (Player p) {
        if (p != null) {
            if (p.currentExp >= p.maxExp) {
                p.currentExp -= p.maxExp;
                p.maxExp = (int)(p.maxExp * 1.15f);
                p.level++;
                p.maxHealth += 5;
                p.damage += 2;
                p.armor += 1;
                p.currentHealth = p.maxHealth;
            }
        }
    }
}
