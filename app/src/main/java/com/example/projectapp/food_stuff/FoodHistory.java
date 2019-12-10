package com.example.projectapp.food_stuff;

import java.util.Calendar;
import java.util.List;

public class FoodHistory {

    private int day;
    private List<Food> foods;
    private List<Meal> meals;

    public FoodHistory(Calendar date) {
        this.date = date;
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public List<Food> getFoods() {
        return foods;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
