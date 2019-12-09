package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

public class MealsList {

    private List<Meal> meals;

    private static final MealsList ourInstance = new MealsList();

    public void addMeal(Meal meals) {
        this.meals.add(meals);
    }

    public static MealsList getInstance() {
        return ourInstance;
    }

    public List<Meal> getMeals() {
        return meals;

    }

    private MealsList() {
        meals = new ArrayList<>();

    }
}
