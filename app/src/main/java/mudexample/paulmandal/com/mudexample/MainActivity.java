package mudexample.paulmandal.com.mudexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    /**
     * TextView for Game Output
     */
    private TextView mGameOutput;

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

        // Get reference to game output TextView
        mGameOutput = (TextView)findViewById(R.id.game_output);

        // Init game state
        mRooms = new ArrayList<Room>();
        mItems = new ArrayList<Item>();
        mPlayer = new Player();

        // Create the first room and add it to mRooms
        Room r = new Room(getString(R.string.room0_title), getString(R.string.room0_desc));
        mRooms.add(r);

        // Create an item and add it to mItems and the first room
        Item i = new Item(getString(R.string.item0_name));
        mItems.add(i);
        r.addContents(i);

        // Set the Player location to the first room
        mPlayer.setLocation(r);

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
                break;
            case R.id.action_get:
                break;
            case R.id.action_drop:
                break;
            case R.id.action_north:
                break;
            case R.id.action_south:
                break;
            case R.id.action_east:
                break;
            case R.id.action_west:
                break;
            case R.id.action_font_increase:
                break;
            case R.id.action_font_decrease:
                break;
        }
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
            exitStr = getString(R.string.exits);
            boolean firstExit = true;
            // Must be the same order as Room.EXIT_*
            String exitNames[] = {getString(R.string.north), getString(R.string.south), getString(R.string.east), getString(R.string.west)};

            for (int i = 0; i < exits.length; i++) {
                if (exits[i] != null) {
                    exitStr += (firstExit ? "" : ", ") + " " + exitNames[i];
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
            contentsStr = getString(R.string.contents);
            boolean firstItem = true;

            for(Item i : contents) {
                contentsStr += (firstItem ? "" : ", ") + " " + i.getName();
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
    }

}
