package com.example.projectapp.ui.addMeal;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.R;
import com.example.projectapp.ShowMealActivity;
import com.example.projectapp.filehandler.FileReader;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.food_stuff.MealsList;
import com.example.projectapp.ui.home.foodinfo.FoodInfoActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Class that contains MealsFragment
 */
public class MealsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "MyLog";
    private ListView lvSavedMeals;
    private List<Meal> meals;
    public static final String EXTRA = "com.example.projectapp.ui.addMeal.EXTRA";
    private ArrayAdapter adaper;
    /**
     * Creates fragment
     * @param inflater Inflates view with addmeal fragment
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_meal, container, false);
        setHasOptionsMenu(true);
        meals = MealsList.getInstance().getMeals();
        return root;
    }

    //Create top bar buttons

    /**
     * Creates options metu to action bar
     * @param menu Menu
     * @param inflater MenuInflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_search).setVisible(false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvSavedMeals = getView().findViewById(R.id.lvSavedMeals);
        registerForContextMenu(lvSavedMeals);
        adaper = new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                meals);

        lvSavedMeals.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        lvSavedMeals.setAdapter(adaper);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                Log.i(TAG, "Poisto toimii");
                Log.i(TAG, "Long click id " + info.id);
                meals.remove((int) info.id);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lvSavedMeals) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        Intent nextActivity = new Intent(getActivity(), ShowMealActivity.class);
        nextActivity.putExtra(EXTRA, i);
        startActivity(nextActivity);
    }
}