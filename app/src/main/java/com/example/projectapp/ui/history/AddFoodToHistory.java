package com.example.projectapp.ui.history;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectapp.R;
import com.example.projectapp.filehandler.FileReader;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodAtDate;
import com.example.projectapp.food_stuff.FoodHistory;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.food_stuff.MealsList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Elmeri Katainen
 */
public class AddFoodToHistory extends AppCompatActivity implements Spinner.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener{

    private static final String TAG = "MyLog";
    List<String> mealNames = new ArrayList<String>();
    private int day;
    private int month;
    private int year;
    private static final int MYFILE = R.raw.resultset;
    private List ruokalista;
    private List<Food> copy;
    private ListView haetut;
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HistoryFragment historyF = new HistoryFragment();
        setContentView(R.layout.activity_add_food_to_history);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        dateToInt(Objects.requireNonNull(getIntent().getExtras()).getString(HistoryFragment.EXTRA, "0_0_0"));
        Spinner mealList = findViewById(R.id.spinner);
        //TextView emptytext = (TextView)findViewById(android.R.id.empty);
        //mealList.setEmptyView(emptytext);
        mealList.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        mealNames.add(0, "");

        for (int i = 0; i < MealsList.getInstance().getMeals().size(); i++) {
            mealNames.add(MealsList.getInstance().getMeals().get(i).toString());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, mealNames);
        // Drop down layout style - list view with radio button
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        mealList.setAdapter(aa);
    }

    @Override
    protected void onStart() {
        findViewById(R.id.buttonSearch).setOnClickListener(this);
        haetut = findViewById(R.id.haetut);
        et = findViewById(R.id.editTextSearch);
        haetut.setOnItemClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onStart();
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
        if(mealNames.get(position) != "") {
            FoodHistory.getInstance().addFoodHistory(new FoodAtDate(day, month, year, MealsList.getInstance().getMeals().get(position - 1)));
            onSupportNavigateUp();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Log.i(TAG, "Item ID: " + copy.get(i).getId());
        Log.i(TAG, "FoodItem " + FoodList.getInstance().getFoods().get(copy.get(i).getId()));
        FoodHistory.getInstance().addFoodHistory(new FoodAtDate(day, month, year , FoodList.getInstance().getFoods().get(copy.get(i).getId())));
        onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSearch:
                Log.i(TAG, "search button toimii");
                haetut.setVisibility(View.VISIBLE);
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    Log.d(TAG, "keyboard not visible");
                }
                findViewById(R.id.editTextSearch).clearFocus();

                if (et.getText().toString().isEmpty()) {
                    if (copy.size() < FoodList.getInstance().getFoods().size()) {
                        searchFoods();
                    }
                } else {
                    searchFoods();
                }
                break;
                }
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

    private void searchFoods() {
        //Food you want to find
        EditText findText = findViewById(R.id.editTextSearch);
        String haku = findText.getText().toString();
        String[] hakusanat = haku.split(" ");

        if (FoodList.getInstance().getFoods().size() == 0) {
            FileReader reader = new FileReader();
            InputStream myFile = getResources().openRawResource(MYFILE);
            ruokalista = reader.readFile(myFile);
            FoodList.getInstance().addFoods(ruokalista);
        }

        //Find specific foods in list
        copy = new ArrayList();
        for (int i = 0; i < FoodList.getInstance().getFoods().size(); i++) {
            int arvo = 0;
            for (int j = 0; j < hakusanat.length; j++) {
                if (FoodList.getInstance().getFoods().get(i).getName().toLowerCase().contains(hakusanat[j].toLowerCase())) {
                    arvo++;
                }
            }
            if (arvo == hakusanat.length) {
                copy.add(FoodList.getInstance().getFoods().get(i));
            }
        }

        //Set listView of foods
        haetut.setAdapter(new ArrayAdapter<>(this,
                R.layout.food_list_layout,
                copy));
    }

}
