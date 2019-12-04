package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains singleton list of foods
 */
public class FoodList {
    private static final FoodList ourInstance = new FoodList();
    private List<Food> foods;

    /**
     * Creates new foodlist singleton
     */
    private FoodList() {
        foods = new ArrayList<>();
    }

    /**
     * Add list of foods to singleton
     * @param foods List of foods that user wants to add
     */
    public void addFoods(List<Food> foods){
        this.foods = foods;
    }

    /**
     * Get food list
     * @return Return singleton list of foods
     */
    public List<Food> getFoods(){
        return foods;
    }

    /**
     * Get food list instance
     * @return Return foodList instance
     */
    public static FoodList getInstance() {
        return ourInstance;
    }
}
