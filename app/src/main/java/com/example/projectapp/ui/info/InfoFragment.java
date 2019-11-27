package com.example.projectapp.ui.info;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.R;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodList;

public class InfoFragment extends Fragment {
    private int value;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        value = getArguments().getInt("item");
        setHasOptionsMenu(true);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Food food = FoodList.getInstance().getFoods().get(value);
        Log.i("testilog", "info " + value);

        ((TextView)getView().findViewById(R.id.tvFoodName))
                .setText(food.getName());
        ((TextView)getView().findViewById(R.id.tvEnergia))
                .setText(food.getEnergia() + "kJ/ " + Math.round(Integer.parseInt(food.getEnergia()) / 4.1868) + "kcal");
        ((TextView)getView().findViewById(R.id.tvHiilihydraatit))
                .setText(food.getHiilihyraatti() + "g");
        ((TextView)getView().findViewById(R.id.tvKuitu))
                .setText(food.getKuitu() + "g");
        ((TextView)getView().findViewById(R.id.tvProteiini))
                .setText(food.getProteiini() + "g");
        ((TextView)getView().findViewById(R.id.tvRasva))
                .setText(food.getRasva() + "g");
        ((TextView)getView().findViewById(R.id.tvSuola))
                .setText(food.getSuola() + "mg");
    }

    //Create action bar buttons
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_search).setVisible(false);
    }
}
