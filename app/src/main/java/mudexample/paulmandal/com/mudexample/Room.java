package mudexample.paulmandal.com.mudexample;

import java.util.ArrayList;

/**
 * Created by pmandal on 10/14/14.
 */
public class Room {

    /**
     * Static ints to specify the mExits array positions
     */
    public static int EXIT_NORTH = 0;
    public static int EXIT_SOUTH = 1;
    public static int EXIT_EAST = 2;
    public static int EXIT_WEST = 3;

    /**
     * Title for this room
     */
    private String mTitle;

    /**
     * Description of this room
     */
    private String mDescription;

    /**
     * Exits this room has, array of length 4, possible exits are: north, south, east, west
     * If an entry is null that exit does not exist, otherwise the entry is a reference to the destination room object
     */
    private Room[] mExits;

    /**
     * Contents of this room
     */
    private ArrayList<Item> mContents;

    public Room(String title, String description) {
        mTitle = title;
        mDescription = description;
        // Init exits and contents
        mExits = new Room[4];
        mContents = new ArrayList<Item>();
    }

    /**
     * Add item to the room's contents
     * @param item Item being added to the room's contents
     * @return
     */
    public boolean addContents(Item item) {
        return mContents.add(item);
    }

    /**
     * Remove item from the room's contents
     * @param item Item being removed from the room's contents
     */
    public boolean removeContents(Item item) {
        return mContents.remove(item);
    }

    /**
     * Set destination for an exit for this room, use Room.EXIT_* for whichExit
     * @param whichExit Use Room.EXIT_*
     * @param destination Reference to the destination this exit points to
     */
    public void setExit(int whichExit, Room destination) {
        mExits[whichExit] = destination;
    }
}
