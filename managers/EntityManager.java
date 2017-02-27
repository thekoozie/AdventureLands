package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.game.Entity;
import com.game.GameObject;
import com.game.Player;
import com.game.UpdateEntity;

/**
 * Created by 7804364 on 1/15/2017.
 */
public class EntityManager {
    public static Array<Entity> entities = new Array<Entity>(); //holds the entities
    public static Array<Entity> updateEntities = new Array<Entity>(); //updates entities
    public static Array<Entity> destroyEntities = new Array<Entity>(); //destroys entities

    public static void add (Entity entity) {
        entities.add(entity);
    }

    public static void setToUpdate (Entity entity) {
        add(entity);
        updateEntities.add(entity);
    }

    public static void setToDestroy (Entity entity) {
        entities.removeValue(entity, true);
        updateEntities.removeValue(entity, true);
        destroyEntities.add(entity);
    }

    public static void update () {
        destroyEntities.clear();
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public static void draw (Batch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

   static Vector2 tmp = new Vector2();
    public static float getClosestEntityDistance (Vector2 posPlayer) {
        float distance = Float.MAX_VALUE;
        Entity closest;
        for (Entity entity : entities) {
            tmp.set((entity.getX()), entity.getY());
            float dst = posPlayer.dst(entity.getX(), entity.getY());
            if (dst < distance) {
                distance = dst;
                closest = entity;
            }
        }
        return distance;
    }
    public static Entity getClosestEntity(){
        Player player = PlayerManager.getPlayer();
        Entity closest = null;
        double distance = -1;
        if(player != null && EntityManager.entities != null){
            Vector2 v1 = new Vector2(PlayerManager.getPlayer().getX(),PlayerManager.getPlayer().getY());
            for(Entity e : EntityManager.entities){
                if(distance == -1){
                    distance = v1.dst(new Vector2(e.getX(), e.getY()));
                    closest = e;
                }else{
                    double temp = v1.dst(new Vector2(e.getX(), e.getY()));
                    if(temp < distance){
                        distance = temp;
                        closest = e;
                    }
                }
            }
        }
        return closest;
    }
}
