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

/**
 * Class that contains AddMealFragment
 */
public class AddMealFragment extends Fragment {
    private static final String TAG = "MyLog";
    private static final int MYFILE = R.raw.resultset;

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

}