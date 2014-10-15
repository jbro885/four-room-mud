package mudexample.paulmandal.com.mudexample;

/**
 * Created by pmandal on 10/14/14.
 *
 * Represents any item in the game that can be picked up or dropped
 */
public class Item {

    /**
     * Item name
     */
    private String mName;

    public Item(String name) {
        mName = name;
    }

    public String getName() { return mName; }

    /**
     * Determine whether Player can pick up an item from a particular room
     * @param player The player picking up the item
     * @param room The room the action is taking place in
     * @return true If the item can be picked up
     */
    public boolean canGet(Player player, Room room) { return true; }

}
