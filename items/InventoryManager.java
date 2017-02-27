package items;

import com.badlogic.gdx.utils.Array;

/**
 * Created by 7804364 on 1/31/2017.
 */
public class InventoryManager {
    public static Array<Item> items = new Array<Item>(); //holds the item
    public static Array<Item> updateItems = new Array<Item>(); //updates item
    public static Array<Item> destroyItems = new Array<Item>(); //destroys item

    public static void add (Item item) {
        items.add(item);
    }

    public static void setToUpdate (Item item) {
        add(item);
        updateItems.add(item);
    }

    public static void setToDestroy (Item item) {
        items.removeValue(item, true);
        updateItems.removeValue(item, true);
        destroyItems.add(item);
    }

    public static void update () {
        destroyItems.clear();
        for (Item item : items) {
            item.update();
        }
    }

    public static void draw () {
        for (Item item : items) {
            item.render();
        }
    }
}
