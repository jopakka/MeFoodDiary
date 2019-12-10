package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miro Norring
 */
public class MealsList {
    private List<Meal> meals;
    private static final MealsList ourInstance = new MealsList();

    private MealsList() {
        meals = new ArrayList<>();
    }

    public static MealsList getInstance() {
        return ourInstance;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void replaceList(List<Meal> meals){
        this.meals = meals;
    }
}
