package com.example.projectapp.food_stuff;

import java.util.List;

public class FoodAtDate {

    private int day;
    private int month;
    private int year;
    private List<Food> foods;
    private List<Meal> meals;

    public FoodAtDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
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

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }
}
