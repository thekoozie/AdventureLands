package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.Constants;
import managers.GuiManager;

/**
 * Created by 7804364 on 1/26/2017.
 */
public class Item extends Group {
    private Stage stage;

    private Texture texture;
    private Image image;
    private float itemX;
    private float itemY;
    private BitmapFont bfont;

    private String itemName;
    private int itemLevel;
    private int itemDamage;
    private int itemHealth;
    private int itemArmor;
    private String itemType;
    private float itemAttackSpeed;
    private int itemRarity;
    private Color itemRarityColor;

    Image itembg;
    Label itemNameLabel;
    Label itemLevelLabel;
    Label itemDamageLabel;
    Label itemHealthLabel;
    Label itemArmorLabel;
    Label itemAttackSpeedLabel;

    Table table;
    Label.LabelStyle labelStyle;

    public Item (float x, float y, String texture_path, String itemName, Stage stg, int itemLevel, int itemDamage, int itemHealth, int itemArmor, String itemType, float itemAttackSpeed, int itemRarity) {
        bfont = new BitmapFont();
        this.itemX = x;
        this.itemY = y;
        this.texture = new Texture(texture_path);
        this.stage = stg;
        this.image = new Image(this.texture);
        this.table = new Table();

        this.itemRarity = itemRarity;
        this.itemName = itemName;
        this.itemLevel = itemLevel;
        this.itemDamage = (itemDamage * this.itemRarity);
        this.itemHealth = (itemHealth * this.itemRarity);
        this.itemArmor = (itemArmor * this.itemRarity);
        this.itemType = itemType;
        this.itemAttackSpeed = itemAttackSpeed;
        this.itemRarityColor = Constants.getItemRarityColor(this.itemRarity);

        this.setBounds(x, y, this.texture.getWidth(), this.texture.getHeight());
        this.setPosition(x, y);
        this.addActor(image);
        this.stage.addActor(this);

        labelStyle = new Label.LabelStyle(bfont, Color.WHITE);

        itembg = new Image(new Texture("itembg.png"));
        itemNameLabel = new Label(Constants.getItemRarityName(this.itemRarity)+" " + itemName, new Label.LabelStyle(bfont, this.itemRarityColor));
        itemLevelLabel = new Label("Lvl: " + itemLevel, new Label.LabelStyle(bfont, Color.WHITE));
        itemDamageLabel = new Label("Damage: +" + this.itemDamage, new Label.LabelStyle(bfont, Color.WHITE));
        itemHealthLabel = new Label("Health: +" + this.itemHealth, new Label.LabelStyle(bfont, Color.WHITE));
        itemArmorLabel = new Label("Armor: +" + this.itemArmor, new Label.LabelStyle(bfont, Color.WHITE));
        itemAttackSpeedLabel = new Label("AttackSpd: " + this.itemAttackSpeed, new Label.LabelStyle(bfont, Color.WHITE));
    }

    public void update () {
        this.image.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                table.bottom().left();
                table.add(itemNameLabel);
                table.row();
                table.add(itemLevelLabel);
                table.row();
                table.add(itemDamageLabel);
                table.row();
                table.add(itemHealthLabel);
                table.row();
                table.add(itemArmorLabel);
                table.row();
                table.add(itemAttackSpeedLabel);
                table.row();
                table.setPosition(itemX + 32, itemY + 70);
                //table.debug();
                stage.addActor(table);
                //drawItemStats();
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                //removeItemStats();
                table.clear();
            }
        });
    }

    public void render () {
        this.setPosition(getX(), getY());
    }
}
