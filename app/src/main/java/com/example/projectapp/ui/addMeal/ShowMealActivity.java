package com.example.projectapp.ui.addMeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectapp.R;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.food_stuff.MealsList;
import com.example.projectapp.ui.MainActivity;

import java.util.Objects;

/**
 *
 * @author Joonas Niemi
 */
public class ShowMealActivity extends AppCompatActivity {
    private static final String TAG = "MyLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meal);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA, 0);

        Log.i(TAG, "Meal ID: " + i +" " + MealsList.getInstance().getMeals().get(i).getMeal());

        ((TextView)findViewById(R.id.tvFoodName))
                .setText(MealsList.getInstance().getMeals().get(i).toString());

        ListView lv = findViewById(R.id.lvMealFoods);
        lv.setAdapter(new ArrayAdapter<>(this,
                R.layout.food_list_layout,
                MealsList.getInstance().getMeals().get(i).getMeal()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
}
