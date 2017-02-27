package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by 7804364 on 1/14/2017.
 */
public class SoundManager implements Disposable{
    private static Sound music;
    private static Sound sfx;
    private static float masterVolume = 1.0f;

    public static void playMusic (String filePath, float volume) {
        music = Gdx.audio.newSound(Gdx.files.internal(filePath));
        music.loop(volume * masterVolume);
    }

    public void dispose() {
        music.dispose();
        sfx.dispose();
    }
}
