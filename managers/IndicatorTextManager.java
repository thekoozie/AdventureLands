package managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.game.Entity;

/**
 * Created by 7804364 on 1/20/2017.
 */
public class IndicatorTextManager {
    public static Array<TextManager.DamageText> indicatorTexts = new Array<TextManager.DamageText>(); //holds the text
    public static Array<TextManager.DamageText> updateIndicatorTexts = new Array<TextManager.DamageText>(); //updates text
    public static Array<TextManager.DamageText> destroyIndicatorTexts = new Array<TextManager.DamageText>(); //destroys text

    public static void add (TextManager.DamageText text) {
        indicatorTexts.add(text);
    }

    public static void setToUpdate (TextManager.DamageText text) {
        add(text);
        updateIndicatorTexts.add(text);
    }

    public static void setToDestroy (TextManager.DamageText text) {
        indicatorTexts.removeValue(text, true);
        updateIndicatorTexts.removeValue(text, true);
        destroyIndicatorTexts.add(text);
    }

    public static void update () {
        destroyIndicatorTexts.clear();
        for (TextManager.DamageText text : indicatorTexts) {
            text.update();
        }
    }

    public static void draw (Batch batch) {
        for (TextManager.DamageText text : indicatorTexts) {
            text.render(batch);
        }
    }
}
