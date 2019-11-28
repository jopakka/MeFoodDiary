package com.example.projectapp.ui;

import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
        return true;
    }

    //Options menu item click events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                Log.i("testilog", "action bar appInfo toimii");
                Intent intent = new Intent(this, AppInfoActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_search:
                Log.i("testilog", "action bar search toimii");
                EditText et = findViewById(R.id.ptFind);
                String text = et.getText().toString();
                if (searchVisible) {
                    if (text.isEmpty()) {
                        item.setIcon(R.drawable.ic_search_black_24dp);
                        findViewById(R.id.ptFind).setVisibility(View.GONE);
                        findViewById(R.id.bSearch).setVisibility(View.GONE);
                        searchVisible = false;
                    } else {
                        et.setText("");
                        try {
                            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        } catch (Exception e){
                            System.out.println("nice boi");
                        }
                    }
                } else {
                    item.setIcon(R.drawable.ic_clear_white_24dp);
                    findViewById(R.id.ptFind).setVisibility(View.VISIBLE);
                    findViewById(R.id.bSearch).setVisibility(View.VISIBLE);
                    searchVisible = true;
                }
                return true;

            case android.R.id.home:
                Log.i("testilog", "action bar back toimii");
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean getSearchVisible(){
        return searchVisible;
    }
}
