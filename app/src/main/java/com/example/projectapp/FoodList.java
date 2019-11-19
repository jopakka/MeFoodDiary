package com.example.projectapp;

import java.util.ArrayList;
import java.util.List;

public class FoodList {
    private static FoodList ourInstance = null;
    private List<Food> foods;

    public FoodList(List foods) {
        this.foods = new ArrayList<>();
        this.foods = foods;
        ourInstance = this;
    }

    public List<Food> getFoods(){
        return this.foods;
    }

    public static FoodList getInstance() {
        return ourInstance;
    }
}
