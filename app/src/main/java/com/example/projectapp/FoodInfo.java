package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projectapp.ui.home.HomeFragment;

public class FoodInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(HomeFragment.EXTRA, 0);


        ((TextView)findViewById(R.id.tvFoodName))
                .setText(FoodList.getInstance().getFoods().get(i).getName());
        ((TextView)findViewById(R.id.tvEnergia))
                .setText(FoodList.getInstance().getFoods().get(i).getEnergia() + "kJ");
        ((TextView)findViewById(R.id.tvHiilihydraatit))
                .setText(FoodList.getInstance().getFoods().get(i).getHiilihyraatti() + "g");
        ((TextView)findViewById(R.id.tvKuitu))
                .setText(FoodList.getInstance().getFoods().get(i).getKuitu() + "g");
        ((TextView)findViewById(R.id.tvProteiini))
                .setText(FoodList.getInstance().getFoods().get(i).getProteiini() + "g");
        ((TextView)findViewById(R.id.tvRasva))
                .setText(FoodList.getInstance().getFoods().get(i).getRasva() + "g");
        ((TextView)findViewById(R.id.tvSuola))
                .setText(FoodList.getInstance().getFoods().get(i).getSuola() + "mg");
    }
}
