package com.example.projectapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.projectapp.R;
import com.example.projectapp.ui.addMeal.AddMealFragment;
import com.example.projectapp.ui.history.HistoryFragment;
import com.example.projectapp.ui.home.HomeFragment;
import com.example.projectapp.ui.home.foodinfo.InfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA = "com.example.projectapp.ui.home.EXTRA";
    private static final String TAG = "MyLog";
    private boolean searchVisible;

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

    //Luo options valikon yläpalkkiin
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
        if (searchVisible) {
            menu.findItem(R.id.action_search).setIcon(R.drawable.ic_clear_white_24dp);
        }
        return true;
    }

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
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        Log.d(TAG, "keyboard not visible");
                    }
                    findViewById(R.id.ptFind).clearFocus();
                    if (text.isEmpty()) {
                        item.setIcon(R.drawable.ic_search_black_24dp);
                        findViewById(R.id.ptFind).setVisibility(View.GONE);
                        findViewById(R.id.bSearch).setVisibility(View.GONE);
                        searchVisible = false;
                    } else {
                        et.setText("");
                    }
                } else {
                    item.setIcon(R.drawable.ic_clear_white_24dp);
                    findViewById(R.id.ptFind).setVisibility(View.VISIBLE);
                    findViewById(R.id.bSearch).setVisibility(View.VISIBLE);
                    findViewById(R.id.ptFind).requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(findViewById(R.id.ptFind), 0);
                    searchVisible = true;
                }
                return true;

            case android.R.id.home:
                Log.i(TAG, "action bar back toimii");
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean getSearchVisible() {
        return searchVisible;
    }
}
