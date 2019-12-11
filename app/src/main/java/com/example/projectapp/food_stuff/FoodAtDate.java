package com.example.projectapp.food_stuff;

import java.util.ArrayList;
import java.util.List;

public class FoodAtDate {

    private int day;
    private int month;
    private int year;
    private Food food;
    private Meal meal;

    public FoodAtDate(int day, int month, int year, Food food) {
        this.food = food;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public FoodAtDate(int day, int month, int year, Meal meal) {
        this.meal = meal;
        this.day = day;
        this.month = month;
        this.year = year;
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

    public Food getFood() {
        return this.food;
    }

    public Meal getMeal() {
        return this.meal;
    }

    public boolean isMeal() {
        if(food == null)
            return true;
        else
            return false;
    }
}
