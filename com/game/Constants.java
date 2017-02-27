package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by 7804364 on 1/20/2017.
 */
public class Constants {
    public static Stage WORLD_STAGE = null;

    public static void setWorldStage (Stage stage) {
        WORLD_STAGE = stage;
    }

    public final static String Monsters[] = {"goblin", "bear", "beetle", "orc", "skeleton"};

    public static String getRandomMonster () {
        Random ran = new Random();
        int arrayLength = ran.nextInt(Monsters.length);
        return Monsters[arrayLength];
    }

    public static int getRandomMonsterLevel (int level) {
        int randomLevel = (level) + (int)(Math.random() * (level + 1));

        return randomLevel;
    }

    public static int getRandomMonsterHealth (int level) {
        int randomLevel = (level*2) + (int)(Math.random() * (level*3));

        return randomLevel;
    }

    public static int getRandomMonsterExp (int level) {
        int randomLevel = (level*2) + (int)(Math.random() * (level*4));

        return randomLevel;
    }

    public static int getRandomMonsterAttackSpeed () {
        int randomLevel = (1) + (int)(Math.random() * (4));

        return randomLevel;
    }

    public static int getRandomMonsterArmor (int level) {
        int randomLevel = (0) + (int)(Math.random() * (level*2));

        return randomLevel;
    }

    public static int getRandomMonsterDamage (int level) {
        int randomLevel = (1) + (int)(Math.random() * (level*4));

        return randomLevel;
    }

    //Begin Items
    public static int getRandomItemDamage (int level) {
        Random r = new Random();
        int Low = level+level;
        int High = level*2+level;
        int Result = r.nextInt(High-Low) + Low;

        return Result;
    }

    public static int getRandomItemHealth (int level) {
        Random r = new Random();
        int Low = level - level;
        int High = (level+2)*4;
        int Result = r.nextInt(High-Low) + Low;

        return Result;
    }

    public static int getRandomItemArmor (int level) {
        Random r = new Random();
        int Low = level - level;
        int High = (level)*2;
        int Result = r.nextInt(High-Low) + Low;

        return Result;
    }

    public static double getRandomItemAttackSpeed () {
        double r = Math.random()*4.00;

        return r;
    }

    public static int getItemRarity () {
        double[] chances = {40, 15, 10, 4, 1, 0.01, 0.002};
        Random r = new Random();
        boolean dropped = false;
        String rarity = "Common";
        int rarityint = 1;
        for (int i = 0; i < chances.length; i++) {
            if (chances[i] > r.nextDouble() * 100) {
                if (chances[i] == 40) {
                } else if (chances[i] == 15) {
                    rarity = "Uncommon";
                    rarityint = 2;
                } else if (chances[i] == 10) {
                    rarity = "Rare";
                    rarityint = 3;
                } else if (chances[i] == 4) {
                    rarity = "Unique";
                    rarityint = 4;
                } else if (chances[i] == 1) {
                    rarity = "Epic";
                    rarityint = 5;
                }  else if (chances[i] == 0.01) {
                    rarity = "Legendary";
                    rarityint = 6;
                }  else if (chances[i] == 0.002) {
                    rarity = "Mystic";
                    rarityint = 7;
                }
                dropped = true;
                break;
            }
        }
        if (dropped == false) {
            rarityint = 0;
        }
        return rarityint;
    }

    public static int getEntityRarity () {
        double[] chances = {40, 15, 10, 4, 1, 0.01, 0.002};
        Random r = new Random();
        boolean dropped = false;
        String rarity = "Common";
        int rarityint = 1;
        for (int i = 0; i < chances.length; i++) {
            if (chances[i] > r.nextDouble() * 100) {
                if (chances[i] == 40) {
                } else if (chances[i] == 15) {
                    rarity = "Uncommon";
                    rarityint = 2;
                } else if (chances[i] == 10) {
                    rarity = "Rare";
                    rarityint = 3;
                } else if (chances[i] == 4) {
                    rarity = "Unique";
                    rarityint = 4;
                } else if (chances[i] == 1) {
                    rarity = "Epic";
                    rarityint = 5;
                }  else if (chances[i] == 0.01) {
                    rarity = "Legendary";
                    rarityint = 6;
                }  else if (chances[i] == 0.002) {
                    rarity = "Mystic";
                    rarityint = 7;
                }
                dropped = true;
                break;
            }
        }
        if (dropped == false) {
            rarityint = 1;
        }
        return rarityint;
    }

    public static Color getItemRarityColor (int itemRarity) {
        Color color;
        if (itemRarity == 1) {
            color = Color.WHITE;
        } else if (itemRarity == 2) {
            color = Color.GREEN;
        } else if (itemRarity == 3) {
            color = Color.CYAN;
        } else if (itemRarity == 4) {
            color = Color.BLUE;
        } else if (itemRarity == 5) {
            color = Color.MAGENTA;
        } else if (itemRarity == 6) {
            color = Color.ORANGE;
        } else if (itemRarity == 7) {
            color = Color.RED;
        } else {
            color = Color.WHITE;
        }

        return color;
    }

    public static String getItemRarityName (int itemRarity) {
        String color;
        if (itemRarity == 1) {
            color = "Common";
        } else if (itemRarity == 2) {
            color = "Uncommon";
        } else if (itemRarity == 3) {
            color = "Rare";
        } else if (itemRarity == 4) {
            color = "Unique";
        } else if (itemRarity == 5) {
            color = "Epic";
        } else if (itemRarity == 6) {
            color = "Legendary";
        } else if (itemRarity == 7) {
            color = "Mystic";
        } else {
            color = "Common";
        }

        return color;
    }
}
