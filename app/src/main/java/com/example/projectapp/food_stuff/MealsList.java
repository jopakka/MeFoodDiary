package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton for Meal items
 * @author Miro Norring
 */
public class MealsList {
    private List<Meal> meals;
    private static final MealsList ourInstance = new MealsList();

    /**
     * Constructor for MealsList singleton
     */
    private MealsList() {
        meals = new ArrayList<>();
    }

    /**
     * Get MealsList instance
     * @return Instance
     */
    public static MealsList getInstance() {
        return ourInstance;
    }

    /**
     * Get list of meals
     * @return List of meals
     */
    public List<Meal> getMeals() {
        return meals;
    }

    /**
     * Add new meal to list
     * @param meal Meal
     */
    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    /**
     * Replace whole list
     * @param meals List of meals
     */
    public void replaceList(List<Meal> meals){
        this.meals = meals;
    }
}
