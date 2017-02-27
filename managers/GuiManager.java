package managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.Player;

/**
 * Created by 7804364 on 1/19/2017.
 */
public class GuiManager {

    public Stage stage;
    private Table table;

    private BitmapFont bfont;
    private BitmapFont b2font;

    private Image expBarBg;
    private Image expBarFg;
    private Label expBarLabel;
    private Image avatar;
    private Image hpBarBg;
    private Image hpBarFg;
    private Label playerLevel;
    private Image inventory;
    private Label playerHealth;

    public void create (Stage stg) {
        stage = stg;
        //Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        bfont = new BitmapFont();
        b2font = new BitmapFont(Gdx.files.internal("leveltext.fnt"));

        expBarBg = new Image(new Texture("ui/expbarbg.png"));
        expBarBg.setPosition(320,0);
        stage.addActor(expBarBg);
        expBarFg = new Image(new Texture("ui/expbarfg.png"));
        expBarFg.setPosition(320,0);
        expBarFg.setOrigin(0,0);
        stage.addActor(expBarFg);

        expBarLabel = new Label(""+PlayerManager.getPlayer().currentExp+"/"+PlayerManager.getPlayer().maxExp, new Label.LabelStyle(bfont, Color.WHITE));
        expBarLabel.setPosition(640, 14);
        stage.addActor(expBarLabel);

        avatar = new Image(new Texture("ui/avatar.png"));
        avatar.setPosition(0, 620);
        stage.addActor(avatar);

        Label leveltext = new Label("Level:", new Label.LabelStyle(bfont, Color.WHITE));
        leveltext.setPosition(32,690);
        stage.addActor(leveltext);

        playerLevel = new Label(""+PlayerManager.getPlayer().level, new Label.LabelStyle(b2font, Color.WHITE));
        playerLevel.setPosition(35, 640);
        stage.addActor(playerLevel);

        hpBarBg = new Image(new Texture("ui/playerhpbg.png"));
        hpBarBg.setPosition(100, 680);
        stage.addActor(hpBarBg);
        hpBarFg = new Image(new Texture("ui/playerhpfg.png"));
        hpBarFg.setPosition(100, 680);
        stage.addActor(hpBarFg);

        playerHealth = new Label(""+PlayerManager.getPlayer().currentHealth+"/"+PlayerManager.getPlayer().maxHealth, new Label.LabelStyle(bfont, Color.WHITE));
        playerHealth.setPosition(206, 684);
        stage.addActor(playerHealth);

        inventory = new Image(new Texture("ui/inventory.png"));
        inventory.setPosition(stg.getWidth()-46, 0);
        stage.addActor(inventory);
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void render () {
        expBarFg.setScale(PlayerManager.getPlayer().currentExp / (float) PlayerManager.getPlayer().maxExp, 1f);
        hpBarFg.setScale(PlayerManager.getPlayer().currentHealth / (float) PlayerManager.getPlayer().maxHealth, 1f);
        expBarLabel.setText(""+PlayerManager.getPlayer().currentExp+"/"+PlayerManager.getPlayer().maxExp);
        playerLevel.setText(""+ PlayerManager.getPlayer().level);
        playerHealth.setText(""+PlayerManager.getPlayer().currentHealth+"/"+PlayerManager.getPlayer().maxHealth);
        //stage.act(Gdx.graphics.getDeltaTime());
        //stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}