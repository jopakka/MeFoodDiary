package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Meal item class
 * @author Miro Norring
 */
public class Meal {
    private List<Food> foods;
    private String nimi;

    /**
     * Constructor for meal
     * @param list List of food items
     * @param name Meals name
     */
    public Meal(List<Food> list, String name) {
        foods = new ArrayList<>();
        foods.addAll(list);
        nimi = name;
    }

    /**
     * Get current meal
     * @return List of food items
     */
    public List<Food> getMeal() {
        return foods;
    }

    /**
     * Return foods name
     * @return Food name
     */
    @Override
    public String toString() {
        return nimi;
    }
}
