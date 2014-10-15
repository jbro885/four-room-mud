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

}
