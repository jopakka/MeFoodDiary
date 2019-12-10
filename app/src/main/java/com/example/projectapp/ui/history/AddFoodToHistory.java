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
import com.example.projectapp.food_stuff.MealsList;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elmeri Katainen
 */
public class AddFoodToHistory extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private static final String TAG = "MyLog";
    List<String> mealNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HistoryFragment historyF = new HistoryFragment();
        setContentView(R.layout.activity_add_food_to_history);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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
