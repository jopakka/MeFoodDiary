package com.example.projectapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projectapp.R;
import com.example.projectapp.ui.addMeal.CreateMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

/**
 * Class that contains MainActivity stuffs and action bar items
 */
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA = "com.example.projectapp.ui.home.EXTRA";
    private static final String TAG = "MyLog";
    private boolean searchVisible;

    /**
     * Happens when activity is created
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_history, R.id.navigation_addMeal).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    /**
     * Creates options menu to action bar
     * @param menu Menu
     * @return Return boolean if action bar is created
     */
    //Luo options valikon yläpalkkiin
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
        if (searchVisible) {
            menu.findItem(R.id.action_search).setIcon(R.drawable.ic_clear_white_24dp);
        }
        return true;
    }

    /**
     * Options menu onClick events
     * @param item Menu item
     * @return return if item is clicked
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

            case R.id.action_search:
                Log.i(TAG, "action bar search toimii");
                EditText et = findViewById(R.id.ptFind);
                String text = et.getText().toString();
                if (searchVisible) {
                    //Piilottaa näyttönäppäimistön, mikäli se on päällä
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        Objects.requireNonNull(imm).hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                    } catch (Exception e) {
                        Log.d(TAG, "keyboard not visible");
                    }
                    findViewById(R.id.ptFind).clearFocus();
                    if (text.isEmpty()) {
                        item.setIcon(R.drawable.ic_search_black_24dp);
                        et.setVisibility(View.GONE);
                        findViewById(R.id.bSearch).setVisibility(View.GONE);
                        searchVisible = false;
                    } else {
                        et.setText("");
                    }
                } else {
                    item.setIcon(R.drawable.ic_clear_white_24dp);
                    et.setVisibility(View.VISIBLE);
                    findViewById(R.id.bSearch).setVisibility(View.VISIBLE);
                    et.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).showSoftInput(findViewById(R.id.ptFind), 0);
                    searchVisible = true;
                }
                return true;

            case android.R.id.home:
                Log.i(TAG, "action bar back toimii");
                getSupportFragmentManager().popBackStack();
                return true;

            case R.id.action_addMeal:
                Log.i(TAG, "Lisää ateria-välilehti toimii");
                Intent intent2 = new Intent(this, CreateMealActivity.class);
                startActivity(intent2);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Returns boolean depends if home fragments search bar is visible
     * @return Return search bars visible value
     */
    public boolean getSearchVisible() {
        return searchVisible;
    }
}
