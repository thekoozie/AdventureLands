package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.game.Constants;
import com.game.GameObject;

/**
 * Created by 7804364 on 1/10/2017.
 */
public class TextManager {
    public static BitmapFont bfont = new BitmapFont();
    public static SpriteBatch spriteBatchHandle;
    public static OrthographicCamera orthroCam;

    public static void setSpriteBatch (SpriteBatch batch, OrthographicCamera cam) {
        orthroCam = cam;
        spriteBatchHandle = batch;
    }

    public static void draw(CharSequence str, float x, float y) {
        Vector3 position = new Vector3(x,y, 0);
        orthroCam.unproject(position);
        bfont.draw(spriteBatchHandle, str, position.x, position.y);
    }

    public static void drawColor(CharSequence str, float x, float y, BitmapFont font) {
        Vector3 position = new Vector3(x,y, 0);
        font.draw(spriteBatchHandle, str, position.x, position.y);
    }

    public static void drawName (CharSequence str, float x, float y) {
        Vector3 position = new Vector3(x,y, 0);
        bfont.draw(spriteBatchHandle, str, position.x, position.y);
    }

    public static class DamageText {
        private BitmapFont bfont;
        private CharSequence text;
        private float x;
        private float y;
        private float transparency = 0;

        public DamageText (float x, float y, CharSequence str) {
            bfont = new BitmapFont(Gdx.files.internal("damage.fnt"));
            text = str;
            GlyphLayout layout = new GlyphLayout(bfont, str);
            this.x = (x + layout.width/2);
            this.y = y;

            IndicatorTextManager.setToUpdate(this);
        }
        public void dispose() {

        }

        public void update () {
            y += 1;
            transparency += .01;
            bfont.setColor(1, 0, 0, 1 - transparency);
            if (transparency <= 0) {
                IndicatorTextManager.setToDestroy(this);
            }
        }

        public void render (Batch batch) {
            bfont.draw(batch, text, x , y);
        }
    }

    public static class ExpText extends DamageText {
        private BitmapFont bfont;
        private CharSequence text;
        private float x;
        private float y;
        private float transparency = 0;

        public ExpText(float x, float y, CharSequence str) {
            super(x, y, str);
            bfont = new BitmapFont(Gdx.files.internal("damage.fnt"));
            text = str;
            GlyphLayout layout = new GlyphLayout(bfont, str);
            this.x = (x + layout.width/2);
            this.y = y;

            IndicatorTextManager.setToUpdate(this);
        }

        public void update () {
            y += 1;
            transparency += .01;
            bfont.setColor(0, 1, 0, 1 - transparency);
            if (transparency <= 0) {
                IndicatorTextManager.setToDestroy(this);
            }
        }

        public void render (Batch batch) {
            bfont.draw(batch, text, x , y);
        }
    }

    public static class HealText extends DamageText {
        private BitmapFont bfont;
        private CharSequence text;
        private float x;
        private float y;
        private float transparency = 0;

        public HealText(float x, float y, CharSequence str) {
            super(x, y, str);
            bfont = new BitmapFont(Gdx.files.internal("damage.fnt"));
            text = str;
            GlyphLayout layout = new GlyphLayout(bfont, str);
            this.x = (x + layout.width/2);
            this.y = y;

            IndicatorTextManager.setToUpdate(this);
        }

        public void update () {
            y += 1;
            transparency += .01;
            bfont.setColor(0, 1, 0, 1 - transparency);
            if (transparency <= 0) {
                IndicatorTextManager.setToDestroy(this);
            }
        }

        public void render (Batch batch) {
            bfont.draw(batch, text, x , y);
        }
    }
}
