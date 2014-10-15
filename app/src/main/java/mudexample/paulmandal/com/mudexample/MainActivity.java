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
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.action_look:
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

}
