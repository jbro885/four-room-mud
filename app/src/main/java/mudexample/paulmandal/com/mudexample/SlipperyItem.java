package mudexample.paulmandal.com.mudexample;

import java.util.Random;

/**
 * Created by pmandal on 10/15/14.
 */
public class SlipperyItem extends Item {

    /**
     * To track whether this is the first attempt at picking this item up
     */
    private boolean mFirstAttempt;

    public SlipperyItem(String name) {
        super(name);
        mFirstAttempt = true;
    }

    @Override
    public boolean canGet(Player player, Room room) {
        // The SlipperyItem can never be picked up on the first try
        if(mFirstAttempt) {
            mFirstAttempt = false;
            return false;
        }

        // Otherwise you have a 30% chance of getting the item
        Random r = new Random();
        int chance = r.nextInt(100);
        return chance > 68;
    }
}
