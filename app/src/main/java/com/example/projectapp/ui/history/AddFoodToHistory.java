package com.example.projectapp.ui.history;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectapp.R;
import com.example.projectapp.food_stuff.FoodHistory;
import com.example.projectapp.food_stuff.MealsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Elmeri Katainen
 */
public class AddFoodToHistory extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private static final String TAG = "MyLog";
    List<String> mealNames = new ArrayList<String>();
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HistoryFragment historyF = new HistoryFragment();
        setContentView(R.layout.activity_add_food_to_history);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        dateToInt(Objects.requireNonNull(getIntent().getExtras()).getString(HistoryFragment.EXTRA, "0_0_0"));
        Spinner mealList = findViewById(R.id.spinner);
        mealList.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        mealNames.add(0, "");
        for (int i = 1; i < MealsList.getInstance().getMeals().size(); i++) {
            mealNames.add(MealsList.getInstance().getMeals().get(i).toString());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, mealNames);
        // Drop down layout style - list view with radio button
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        mealList.setAdapter(aa);
    }

    /**@Override
    protected void onStart() {

        Spinner mealList = findViewById(R.id.spinner);
        mealList.setOnItemSelectedListener(this);

        for (int i = 0; i < MealsList.getInstance().getMeals().size(); i++) {
            mealNames[i] = (MealsList.getInstance().getMeals().get(i).getMeal().toString());
        }
        Log.i(TAG, "" + mealNames);
        //Creating the ArrayAdapter instance having the meal list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mealNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        mealList.setAdapter(aa);
        super.onStart();
    }*/

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
        //FoodHistory.getInstance().addFoodHistory(day, month, year, );
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "takaisin ateriafragmenttiin");
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_info).setVisible(false);
        return true;
    }

    public void dateToInt(String string) {
        String[] strArray = string.split("_");
        for(int i = 0; i < strArray.length; i++){
            if(i == 0)
                day = Integer.parseInt(strArray[i]);
            if(i == 1)
                month = Integer.parseInt(strArray[i]);
            if(i == 2)
                year = Integer.parseInt(strArray[i]);
        }
    }

    public void findHistory() {

    }

    /**@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addMeal:
                Log.i(TAG, "action bar appInfo toimii");
                Intent intent = new Intent(this, AppInfoActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


}
