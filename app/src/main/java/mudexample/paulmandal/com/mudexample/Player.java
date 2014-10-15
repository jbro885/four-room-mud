package mudexample.paulmandal.com.mudexample;

import java.util.ArrayList;

/**
 * Created by pmandal on 10/14/14.
 *
 * Represents the player playing the game
 */
public class Player {

    /**
     * Items the player is currently holding
     */
    private ArrayList<Item> mInventory;

    public Player() {
        // Init member variables
        mInventory = new ArrayList<Item>();
    }

    /**
     * Add item to the Player's inventory
     * @param item The item being added to the inventory
     */
    public boolean addInventory(Item item) {
        return mInventory.add(item);
    }

    /**
     * Remove item from the Player's inventory
     * @param item The item being removed from the inventory
     */
    public boolean removeInventory(Item item) {
        return mInventory.remove(item);
    }
}