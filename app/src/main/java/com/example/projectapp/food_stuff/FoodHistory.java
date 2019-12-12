package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

public class FoodHistory {
    private static final FoodHistory ourInstance = new FoodHistory();
    private List<FoodAtDate> foodHistory;

    public static FoodHistory getInstance() {
        return ourInstance;
    }

    private FoodHistory() {
        this.foodHistory = new ArrayList<>();
    }

    public void addFoodHistory(FoodAtDate foodHistory) {
        this.foodHistory.add(foodHistory);
    }

    public List<FoodAtDate> getFoodHistory() {
        return this.foodHistory;
    }
}
