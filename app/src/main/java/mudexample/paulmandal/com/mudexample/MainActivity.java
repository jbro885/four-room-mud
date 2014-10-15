package mudexample.paulmandal.com.mudexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    /**
     * TextView for Game Output
     */
    private TextView mGameOutput;

    /**
     * ScrollView to scroll game text
     */
    private ScrollView mScrollView;

    /**
     * ImageViews for our Exit buttons
     */
    private ImageView mNorthButton;
    private ImageView mSouthButton;
    private ImageView mEastButton;
    private ImageView mWestButton;

    /**
     * ArrayList of every Room in the game
     */
    private ArrayList<Room> mRooms;

    /**
     * ArrayList of every Item in the game
     */
    private ArrayList<Item> mItems;

    /**
     * The game's player
     */
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up click listeners for everything
        findViewById(R.id.action_look).setOnClickListener(this);
        findViewById(R.id.action_inventory).setOnClickListener(this);
        findViewById(R.id.action_get).setOnClickListener(this);
        findViewById(R.id.action_drop).setOnClickListener(this);
        findViewById(R.id.action_north).setOnClickListener(this);
        findViewById(R.id.action_south).setOnClickListener(this);
        findViewById(R.id.action_east).setOnClickListener(this);
        findViewById(R.id.action_west).setOnClickListener(this);
        findViewById(R.id.action_font_increase).setOnClickListener(this);
        findViewById(R.id.action_font_decrease).setOnClickListener(this);

        // Get reference to game output TextView and ScrollView
        mGameOutput = (TextView)findViewById(R.id.game_output);
        mScrollView = (ScrollView)findViewById(R.id.game_output_scrollview);

        // Get references to the north/south/east/west buttons
        mNorthButton = (ImageView)findViewById(R.id.action_north);
        mSouthButton = (ImageView)findViewById(R.id.action_south);
        mEastButton = (ImageView)findViewById(R.id.action_east);
        mWestButton = (ImageView)findViewById(R.id.action_west);

        // Init game state
        mRooms = new ArrayList<Room>();
        mItems = new ArrayList<Item>();
        mPlayer = new Player();

        // Create the first room and add it to mRooms
        Room room0 = new Room(getString(R.string.room0_title), getString(R.string.room0_desc));
        mRooms.add(room0);

        // Create the 2nd room and add it to mRooms
        Room room1 = new Room(getString(R.string.room1_title), getString(R.string.room1_desc));
        mRooms.add(room1);

        // Create an exit from room0->room1
        room0.setExit(Room.EXIT_EAST, room1);

        // Create an item and add it to mItems and the first room
        Item i = new Item(getString(R.string.item0_name));
        mItems.add(i);
        room0.addContents(i);

        // Set the Player location to the first room
        mPlayer.setLocation(room0);

        // Update UI (exit buttons)
        updateUI();

        // Force look()
        doLook();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.action_look:
                doLook();
                break;
            case R.id.action_inventory:
                doInventory();
                break;
            case R.id.action_get:
                break;
            case R.id.action_drop:
                break;
            case R.id.action_north:
                doMove(Room.EXIT_NORTH);
                break;
            case R.id.action_south:
                doMove(Room.EXIT_SOUTH);
                break;
            case R.id.action_east:
                doMove(Room.EXIT_EAST);
                break;
            case R.id.action_west:
                doMove(Room.EXIT_WEST);
                break;
            case R.id.action_font_increase:
                break;
            case R.id.action_font_decrease:
                break;
        }
    }

    /**
     * Describes the player's inventory
     */
    public void doInventory() {
        ArrayList<Item> inventory = mPlayer.getInventory();
        String inventoryStr = "";
        if(inventory.size() > 0) {
            inventoryStr = getString(R.string.inventory) + " ";
            boolean firstItem = true;
            for(Item i : inventory) {
                inventoryStr += (firstItem ? "" : ", ") + i.getName();
                firstItem = false;
            }
            inventoryStr += ".";
        } else {
            inventoryStr = getString(R.string.no_inventory);
        }

        inventoryStr += "\n";
        addOutput(inventoryStr);
    }

    /**
     * Describes the player's location, room contents, and exits
     */
    public void doLook() {
        Room currentLocation = mPlayer.getLocation();

        // Build Exits: String
        boolean exitsExist = false;
        Room[] exits = currentLocation.getExits();
        String exitStr = "";

        // Check that any exits exist, otherwise skip this
        for(int i = 0 ; i < exits.length ; i++) {
            if(exits[i] != null) {
                exitsExist = true;
            }
        }

        // If there are any exits build the Exits: string, otherwise display the no_exits string
        if(exitsExist) {
            exitStr = getString(R.string.exits) + " ";
            boolean firstExit = true;
            // Must be the same order as Room.EXIT_*
            String exitNames[] = {getString(R.string.north), getString(R.string.south), getString(R.string.east), getString(R.string.west)};

            for (int i = 0; i < exits.length; i++) {
                if (exits[i] != null) {
                    exitStr += (firstExit ? "" : ", ") + exitNames[i];
                    firstExit = false;
                }
            }
            exitStr += ".";
        } else {
            exitStr = getString(R.string.no_exits);
        }

        // Build the contents string, if there are no items display the no_contents
        ArrayList<Item> contents = currentLocation.getContents();
        String contentsStr = "";
        if(contents.size() > 0) {
            contentsStr = getString(R.string.contents) + " ";
            boolean firstItem = true;

            for(Item i : contents) {
                contentsStr += (firstItem ? "" : ", ") + i.getName();
                firstItem = false;
            }
            contentsStr += ".";
        } else {
            contentsStr = getString(R.string.no_contents);
        }

        // Build full output
        String output = currentLocation.getTitle() + "\n";
        output += currentLocation.getDescription() + "\n";
        output += exitStr + "\n";
        output += contentsStr + "\n\n";

        // Update Game Output
        addOutput(output);
    }

    /**
     * Add output to the Game Output TextView
     * @param output Output to add to the TextView
     */
    private void addOutput(String output) {
        String currentOutput = mGameOutput.getText().toString();
        mGameOutput.setText(currentOutput += output);
        // Wait 100ms to scroll the ScrollView since mGameOutput's size won't update until the UI refreshes
        mScrollView.postDelayed(scrollRunnable, 100);
    }

    private Runnable scrollRunnable = new Runnable() {
        @Override
        public void run() {
          // Scroll output window to the bottom
          mScrollView.fullScroll(View.FOCUS_DOWN);
        }
    };

    /**
     * Update UI based on current game state, currently just displays/hides exit buttons
     */
    private void updateUI() {
        // Same order as Room.EXIT_*
        ImageView[] exitButtons = {mNorthButton, mSouthButton, mEastButton, mWestButton};

        // Loop through all exits disabling/enabling exitButtons if they are not null
        Room[] exits = mPlayer.getLocation().getExits();
        for(int i = 0 ; i < exits.length ; i++) {
            if(exits[i] != null) {
                exitButtons[i].setVisibility(View.VISIBLE);
            } else {
                exitButtons[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * Move the player in the supplied direction, direction should be one of the Room.EXIT_* ints
     * @param direction Room.EXIT_*
     */
    private void doMove(int direction) {
        // Update the player location
        Room destination = mPlayer.getLocation().getExits()[direction];
        mPlayer.setLocation(destination);
        // Display room description and contents upon entry
        doLook();
        // Update the UI
        updateUI();
    }

}
