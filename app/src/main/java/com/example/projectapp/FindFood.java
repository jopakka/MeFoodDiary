package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;

public class FindFood extends AppCompatActivity {
    private List ruokalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_food);

        //Read food_nutritions.csv file
        FileReader reader = new FileReader();
        InputStream myFile = this.getResources().openRawResource(R.raw.food_nutritions);
        ruokalista = reader.readFile(myFile);
        FoodList foodList = new FoodList(ruokalista);


        //ListView of foods
        ListView lvFoods = findViewById(R.id.lvFoods);
        lvFoods.setAdapter(new ArrayAdapter<Food>(this,
                R.layout.food_list_layout,
                foodList.getInstance().getFoods()));
    }
}
