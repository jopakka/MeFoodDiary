package com.example.projectapp.ui.addMeal;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectapp.R;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.food_stuff.MealsList;

import java.util.List;

/**
 * Class that contains MealsFragment
 */
public class MealsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "MyLog";
    private ListView lvSavedMeals;
    private List<Meal> meals;
    public static final String EXTRA = "com.example.projectapp.ui.home.EXTRA";
    private ArrayAdapter adapter;
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
        adapter = new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                meals);

        lvSavedMeals.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                Log.i(TAG, "Poisto toimii");
                Log.i(TAG, "Long click id " + info.id);
                meals.remove((int) info.id);
                updateUi();
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
        Log.i(TAG, "meal list id: " + i);
        startActivity(nextActivity);
    }

    private void updateUi(){
        lvSavedMeals.setAdapter(adapter);
    }
}