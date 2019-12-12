package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton that holds all foods that has ate
 * @author Elmeri Katainen
 */
public class FoodHistory {
    private static final FoodHistory ourInstance = new FoodHistory();
    private List<FoodAtDate> foodHistory;

    /**
     * Get FoodHistory instance
     * @return Instance
     */
    public static FoodHistory getInstance() {
        return ourInstance;
    }

    /**
     * Constructor for singleton
     */
    private FoodHistory() {
        this.foodHistory = new ArrayList<>();
    }

    /**
     * Adds eaten food to list
     * @param foodHistory FoodAtDate
     */
    public void addFoodHistory(FoodAtDate foodHistory) {
        this.foodHistory.add(foodHistory);
    }

    /**
     * Get FoodHistory list
     * @return List
     */
    public List<FoodAtDate> getFoodHistory() {
        return this.foodHistory;
    }

    /**
     * Replace whole list
     * @param list List of FoodAtDate items
     */
    public void replaceList(List<FoodAtDate> list){
        foodHistory = list;
    }
}
