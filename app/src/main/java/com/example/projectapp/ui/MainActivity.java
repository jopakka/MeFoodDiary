package com.example.projectapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.projectapp.filehandler.FileReader;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodAtDate;
import com.example.projectapp.food_stuff.FoodHistory;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.food_stuff.MealsList;
import com.example.projectapp.ui.addMeal.CreateMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that contains MainActivity stuffs and action bar items
 * @author Joonas Niemi
 */
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA = "com.example.projectapp.ui.home.EXTRA";
    private static final String TAG = "MyLog";
    private static final String MEALPREF = "MealPref";
    private static final String DAYPREF = "DayMealPref";
    private boolean searchVisible;
    private static final int MYFILE = R.raw.resultset;
    private Gson gson = new Gson();

    /**
     * Happens when activity is created
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getGson();

        /**
         * Create bottom navigation bar
         */
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.navigation_home, R.id.navigation_history, R.id.navigation_addMeal).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        /**
         * Creates FoodList if it is empty
         */
        if(FoodList.getInstance().getFoods().size() == 0){
            FileReader reader = new FileReader();
            InputStream myFile = Objects.requireNonNull(getResources().openRawResource(MYFILE));
            List<Food> ruokalista = reader.readFile(myFile);
            FoodList.getInstance().addFoods(ruokalista);
        }
    }

    @Override
    protected void onPause() {
        saveGson();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveGson();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveGson();
        super.onDestroy();
    }

    /**
     * Makes singleton from sharedPreferences
     */
    private void getGson(){
        SharedPreferences pref = getSharedPreferences(MEALPREF, Activity.MODE_PRIVATE);
        /**
         * Makes mealsList from gson
         */
        String mealsJson = gson.toJson(new ArrayList<>());
        String mealList = pref.getString("MealsList", mealsJson);
        TypeToken<List<Meal>> token = new TypeToken<List<Meal>>() {};
        List<Meal> list = gson.fromJson(mealList, token.getType());
        MealsList.getInstance().replaceList(list);

        /**
         * Makes foodHistoryList from gson
         */
        String dayJson = gson.toJson(new ArrayList<>());
        String dayList = pref.getString("DayMealsList", dayJson);
        TypeToken<List<FoodAtDate>> dayToken = new TypeToken<List<FoodAtDate>>(){};
        List<FoodAtDate> foodDayList = gson.fromJson(dayList, dayToken.getType());
        FoodHistory.getInstance().replaceList(foodDayList);
    }

    /**
     * Saves singletons to sharedPreferences
     */
    private void saveGson(){
        String mealsJson = gson.toJson(MealsList.getInstance().getMeals());
        String dayJson = gson.toJson(FoodHistory.getInstance().getFoodHistory());

        SharedPreferences pref = getSharedPreferences(MEALPREF, Activity.MODE_PRIVATE);

        SharedPreferences.Editor prefEdit = pref.edit();
        prefEdit.putString("MealsList", mealsJson);
        prefEdit.putString("DayMealsList", dayJson);
        prefEdit.apply();
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
