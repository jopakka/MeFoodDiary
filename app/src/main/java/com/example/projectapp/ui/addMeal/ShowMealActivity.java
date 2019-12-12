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

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Class for ShowMealActivity
 * @author Joonas Niemi
 */
public class ShowMealActivity extends AppCompatActivity {
    private static final String TAG = "MyLog";
    private int mealId;

    /**
     * Happens when activity is created first time
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meal);

        Bundle b = getIntent().getExtras();
        mealId = b.getInt(MainActivity.EXTRA, 0);

        Log.i(TAG, "Meal ID: " + mealId +" " + MealsList.getInstance().getMeals().get(mealId).getMeal());

        ((TextView)findViewById(R.id.tvFoodName))
                .setText(MealsList.getInstance().getMeals().get(mealId).toString());

        ListView lv = findViewById(R.id.lvMealFoods);
        lv.setAdapter(new ArrayAdapter<>(this,
                R.layout.food_list_layout,
                MealsList.getInstance().getMeals().get(mealId).getMeal()));

        DecimalFormat df = new DecimalFormat("#.#");
        ((TextView)findViewById(R.id.tvEnergia))
                .setText(String.format(getResources().getString(R.string.text_energy), Integer.toString(energyCounter()),
                        "" + Math.round(energyCounter() / 4.1868)));
        ((TextView)findViewById(R.id.tvHiilihydraatit)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("hiilihydraatti"))));
        ((TextView)findViewById(R.id.tvKuitu)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("kuitu"))));

        ((TextView)findViewById(R.id.tvProteiini)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("proteiini"))));

        ((TextView)findViewById(R.id.tvRasva)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("rasva"))));

        ((TextView)findViewById(R.id.tvSuola)).setText(String.format(getResources()
                .getString(R.string.text_mGram), df.format(counter("suola"))));
    }

    /**
     * Counts specific meals foods energy values together and returns average
     * @return int
     */
    private int energyCounter(){
        Meal meal = MealsList.getInstance().getMeals().get(mealId);
        int summa = 0;
        for(int i = 0; i < MealsList.getInstance().getMeals().get(mealId).getMeal().size(); i++){
            int luku = Integer.parseInt(meal.getMeal().get(i).getEnergia().replaceAll("[^\\d.]", ""));
            summa += luku;
        }
        return summa / meal.getMeal().size();
    }

    /**
     * Counts specific meals foods different values together and returns average
     * @param value What will be counted
     * @return Double
     */
    private double counter(String value){
        Meal meal = MealsList.getInstance().getMeals().get(mealId);
        double summa = 0;
        switch (value){
            case "hiilihydraatti":
                for(int i = 0; i < MealsList.getInstance().getMeals().get(mealId).getMeal().size(); i++){
                    double luku = Double.parseDouble(meal.getMeal().get(i).getHiilihyraatti().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / meal.getMeal().size();

            case "kuitu":
                for(int i = 0; i < MealsList.getInstance().getMeals().get(mealId).getMeal().size(); i++){
                    double luku = Double.parseDouble(meal.getMeal().get(i).getKuitu().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / meal.getMeal().size();

            case "proteiini":
                for(int i = 0; i < MealsList.getInstance().getMeals().get(mealId).getMeal().size(); i++){
                    double luku = Double.parseDouble(meal.getMeal().get(i).getProteiini().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / meal.getMeal().size();

            case "rasva":
                for(int i = 0; i < MealsList.getInstance().getMeals().get(mealId).getMeal().size(); i++){
                    double luku = Double.parseDouble(meal.getMeal().get(i).getRasva().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / meal.getMeal().size();

            case "suola":
                for(int i = 0; i < MealsList.getInstance().getMeals().get(mealId).getMeal().size(); i++){
                    double luku = Double.parseDouble(meal.getMeal().get(i).getSuola().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / meal.getMeal().size();
        }
        return 0;
    }

    /**
     * Happens every time when app is resumed
     */
    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Action bar back button event
     * @return super.onSupportNavigateUp
     */
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    /**
     * Android back button events
     */
    @Override
    public void onBackPressed() {
        Log.i(TAG, "takaisin ateriafragmenttiin");
        super.onBackPressed();

    }
}
