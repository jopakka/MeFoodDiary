package com.example.projectapp.ui.addMeal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.R;
import com.example.projectapp.filehandler.FileReader;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.ui.home.foodinfo.FoodInfoActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AddMealFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ArrayList<Food> copy;
    private ArrayList<Food> ingredients;
    private ListView haetut;
    private ListView valitut;
    private static final String TAG = "MyLog";
    private EditText et;
    private List ruokalista;
    private static final int MYFILE = R.raw.resultset;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_meal, container, false);
        setHasOptionsMenu(true);
        root.findViewById(R.id.addFood).setOnClickListener(this);
        root.findViewById(R.id.hakuButton).setOnClickListener(this);
        haetut = root.findViewById(R.id.haetut);
        et = root.findViewById(R.id.haku);
        valitut = root.findViewById(R.id.valitut);
        ingredients = new ArrayList<>();
        copy = new ArrayList<>();
        haetut.setOnItemClickListener(this);
        registerForContextMenu(valitut);
        return root;
    }

    //Create top bar buttons
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_search).setVisible(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hakuButton:
                Log.i(TAG, "search button toimii");
                haetut.setVisibility(View.VISIBLE);
                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    Log.d(TAG, "keyboard not visible");
                }
                getActivity().findViewById(R.id.haku).clearFocus();

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
                Meal ateria = new Meal(ingredients);
                Toast.makeText(getActivity().getApplicationContext(),"Ateria tallennetu!", Toast.LENGTH_LONG).show();

                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Log.i(TAG,  "Item ID: " + copy.get(i).getId());
        ingredients.add(FoodList.getInstance().getFoods().get(copy.get(i).getId()));
        showIngredients();

        haetut.setVisibility(View.GONE);

    }

    private void showIngredients(){
        valitut.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                ingredients));
    }

    private void searchFoods() {
        //Food you want to find
        EditText findText = getActivity().findViewById(R.id.haku);
        String haku = findText.getText().toString();
        String[] hakusanat = haku.split(" ");

        if(FoodList.getInstance().getFoods().size() == 0){
            FileReader reader = new FileReader();
            InputStream myFile = getActivity().getResources().openRawResource(MYFILE);
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
        haetut.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                copy));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.delete:
                Log.i(TAG,  "Poisto toimii");
                Log.i(TAG,  "Long click id " + info.id);
                ingredients.remove((int)info.id);
                showIngredients();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        if (v.getId()==R.id.valitut) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

}