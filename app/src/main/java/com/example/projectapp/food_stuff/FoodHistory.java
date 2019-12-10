package com.example.projectapp.food_stuff;

import java.util.List;

public class FoodHistory {
    private static final FoodHistory ourInstance = new FoodHistory();
    private List<FoodAtDate> foodsAtday;

    public static FoodHistory getInstance() {
        return ourInstance;
    }

    private FoodHistory() {
    }
}
