package managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.game.Entity;
import com.game.Player;
import com.game.UpdatePlayer;

/**
 * Created by 7804364 on 1/15/2017.
 */
public class PlayerManager {
    public static Array<Player> player = new Array<Player>(); //holds the player
    public static Array<Player> updatePlayer = new Array<Player>(); //updates player
    public static Array<Player> destroyPlayer = new Array<Player>(); //destroys player

    public static void add (Player ply) {
        player.add(ply);
    }

    public static void setToUpdate (Player ply) {
        add(ply);
        updatePlayer.add(ply);
    }

    public static void setToDestroy (Player ply) {
        player.removeValue(ply, true);
        updatePlayer.removeValue(ply, true);
        destroyPlayer.add(ply);
    }

    public static void update () {
        destroyPlayer.clear();
        for (Player ply : player) {
            ply.update();
            UpdatePlayer.UpdatePlayer(ply);
        }
    }

    public static void draw (Batch batch) {
        for (Player ply : player) {
            ply.draw(batch);
        }
    }

    public static Player getPlayer () {
        return  player.first();
    }

    public static Vector2 playerPos () {
        return new Vector2(getPlayer().getX(), getPlayer().getY());
    }
}
