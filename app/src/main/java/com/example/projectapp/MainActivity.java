package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List ruokalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Read food_nutritions.csv file
        FileReader reader = new FileReader();
        InputStream myFile = this.getResources().openRawResource(R.raw.food_nutritions);
        ruokalista = reader.readFile(myFile);
        FoodList foodList = new FoodList(ruokalista);


        //ListView of foods
        ListView lvFoods = findViewById(R.id.lvFoods);
        lvFoods.setAdapter(new ArrayAdapter<Food>(this,
                R.layout.food_list_layout,
                FoodList.getInstance().getFoods()));

    }
}
