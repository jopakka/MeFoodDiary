package com.example.projectapp.ui.home.foodinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.projectapp.R;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.ui.AppInfoActivity;
import com.example.projectapp.ui.MainActivity;

import java.util.Objects;

/**
 * Class that contains FoodInfo Activity stuff
 */
public class FoodInfoActivity extends AppCompatActivity {
    private static final String TAG = "MyLog";

    /**
     * When creates activity
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
    }

    /**
     * Happens when activity starts
     */
    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        int value = Objects.requireNonNull(b).getInt(MainActivity.EXTRA, 0);
        Food food = FoodList.getInstance().getFoods().get(value);
        Log.i(TAG, "info " + value);

        ((TextView)findViewById(R.id.tvFoodName)).setText(food.getName());

        ((TextView)findViewById(R.id.tvEnergia)).setText(String.format(getResources()
                .getString(R.string.text_energy), food.getEnergia(),
                "" + Math.round(Integer.parseInt(food.getEnergia()) / 4.1868)));

        ((TextView)findViewById(R.id.tvHiilihydraatit)).setText(String.format(getResources()
                .getString(R.string.text_gram), food.getHiilihyraatti()));

        ((TextView)findViewById(R.id.tvKuitu)).setText(String.format(getResources()
                .getString(R.string.text_gram), food.getKuitu()));

        ((TextView)findViewById(R.id.tvProteiini)).setText(String.format(getResources()
                .getString(R.string.text_gram), food.getProteiini()));

        ((TextView)findViewById(R.id.tvRasva)).setText(String.format(getResources()
                .getString(R.string.text_gram), food.getRasva()));

        ((TextView)findViewById(R.id.tvSuola)).setText(String.format(getResources()
                .getString(R.string.text_mGram), food.getSuola()));
    }

    /**
     * Creates options menu
     * @param menu Menu
     * @return Return boolean of options bar status
     */
    //Create action bar buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_addMeal).setVisible(false);
        return true;
    }

    /**
     * Options menu items click events
     * @param item Options menu item
     * @return return boolen of selected item
     */
    //Options menu item click events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                Log.i(TAG, "action bar appInfo toimii");
                Intent intent = new Intent(this, AppInfoActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                Log.i(TAG, "action bar back toimii");
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Action bar support back arrow click event
     * @return Returns back pressed boolean
     */
    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "foodInfoActivity actionBar back toimii");
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    /**
     * Normal back press click event
     */
    @Override
    public void onBackPressed() {
        Log.i(TAG, "foodInfoActivity back toimii");
        super.onBackPressed();
    }
}
