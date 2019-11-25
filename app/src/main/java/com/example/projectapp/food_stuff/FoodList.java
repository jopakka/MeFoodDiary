package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

public class FoodList {
    private static final FoodList ourInstance = new FoodList();
    private List<Food> foods;

    private FoodList() {
        foods = new ArrayList<>();
    }

    public void addFoods(List<Food> foods){
        this.foods = foods;
    }

    public List<Food> getFoods(){
        return foods;
    }

    public static FoodList getInstance() {
        return ourInstance;
    }
}
