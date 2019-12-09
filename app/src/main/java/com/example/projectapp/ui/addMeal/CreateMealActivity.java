package com.example.projectapp.ui.addMeal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectapp.R;
import com.example.projectapp.filehandler.FileReader;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.food_stuff.MealsList;
import com.example.projectapp.ui.MainActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateMealActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private List<Food> copy;
    private List<Food> ingredients;
    private ListView haetut;
    private ListView valitut;
    private static final String TAG = "MyLog";
    private EditText et;
    private List ruokalista;
    private static final int MYFILE = R.raw.resultset;
    private String ateriaNimi = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        findViewById(R.id.addFood).setOnClickListener(this);
        findViewById(R.id.hakuButton).setOnClickListener(this);
        haetut = findViewById(R.id.haetut);
        et = findViewById(R.id.haku);
        valitut = findViewById(R.id.valitut);
        ingredients = new ArrayList<>();
        copy = new ArrayList<>();
        haetut.setOnItemClickListener(this);
        registerForContextMenu(valitut);
    }

    /**
     * Contains onClick methods
     *
     * @param view Current view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hakuButton:
                Log.i(TAG, "search button toimii");
                haetut.setVisibility(View.VISIBLE);
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    Log.d(TAG, "keyboard not visible");
                }
                findViewById(R.id.haku).clearFocus();

                if (et.getText().toString().isEmpty()) {
                    if (copy.size() < FoodList.getInstance().getFoods().size()) {
                        searchFoods();
                    }
                } else {
                    searchFoods();
                }
                break;

            case R.id.addFood:
                Log.i(TAG, "lisäysnappi toimii");
                /**
                 * https://stackoverflow.com/questions/10903754/input-text-dialog-android
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Nimeä ateria");
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("Tallenna", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ateriaNimi = input.getText().toString();
                        Meal ateria = new Meal(ingredients, ateriaNimi);
                        ateriaNimi = "";
                        Toast.makeText(getApplicationContext(), "Ateria tallennetu!", Toast.LENGTH_LONG).show();
                        ingredients.clear();
                        showIngredients();
                        MealsList.getInstance().addMeal(ateria);
                        onSupportNavigateUp();
                    }
                });
                builder.setNegativeButton("Peruuta", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                break;


        }
    }

    /**
     * Contains onItemClick
     *
     * @param parent
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Log.i(TAG, "Item ID: " + copy.get(i).getId());
        Log.i(TAG, "FoodItem " + FoodList.getInstance().getFoods().get(copy.get(i).getId()));
        ingredients.add(FoodList.getInstance().getFoods().get(copy.get(i).getId()));
        showIngredients();

        haetut.setVisibility(View.GONE);

    }

    private void showIngredients() {
        valitut.setAdapter(new ArrayAdapter<>(this,
                R.layout.food_list_layout,
                ingredients));
    }

    private void searchFoods() {
        //Food you want to find
        EditText findText = findViewById(R.id.haku);
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                Log.i(TAG, "Poisto toimii");
                Log.i(TAG, "Long click id " + info.id);
                ingredients.remove((int) info.id);
                showIngredients();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.valitut) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
