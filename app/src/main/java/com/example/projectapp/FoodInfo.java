package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projectapp.ui.home.HomeFragment;

public class FoodInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_infos);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(HomeFragment.EXTRA, 0);

        ((TextView)findViewById(R.id.tvFoodName))
                .setText(FoodList.getInstance().getFoods().get(i).toString());
    }
}
