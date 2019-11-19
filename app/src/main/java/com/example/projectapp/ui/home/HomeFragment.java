package com.example.projectapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.FileReader;
import com.example.projectapp.Food;
import com.example.projectapp.FoodList;
import com.example.projectapp.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List ruokalista;
    private ListView lvFoods;
    private FoodList foods;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        lvFoods = root.findViewById(R.id.lvFoods);

        //Search button code
        Button findButton = root.findViewById(R.id.bFindFood);
        findButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myClick();
            }
        });
        return root;
    }

    private void myClick(){
        //Read food_nutritions.csv file
        FileReader reader = new FileReader();
        InputStream myFile = getActivity().getResources().openRawResource(R.raw.food_nutritions);
        ruokalista = reader.readFile(myFile);
        foods = new FoodList(ruokalista);


        //ListView of foods
        lvFoods.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                foods.getInstance().getFoods()));

    }
}