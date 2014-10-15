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

    /**
     * Current location of the Player
     */
    private Room mLocation;

    public Player() {
        // Init member variables
        mInventory = new ArrayList<Item>();
        mLocation = null;
    }

    /**
     * Updates the Player's location
     * @param location Room the player is being moved to
     */
    public void setLocation(Room location) {
        mLocation = location;
    }

    /**
     * Gets the current location for this player
     * @return Room object the player is currently inside
     */
    public Room getLocation() { return mLocation; }

    /**
     * Gets the player's inventory
     * @return ArrayList<Item> every item the player is carrying
     */
    public ArrayList<Item> getInventory() { return mInventory; }

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