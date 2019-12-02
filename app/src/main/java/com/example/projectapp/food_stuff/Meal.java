package com.example.projectapp.food_stuff;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Meal {
    private ArrayList<Food> foods;

    public Meal(ArrayList<Food> list) {
        foods = new ArrayList<>();
        foods = list;

    }
    public ArrayList getMeal() {
        return foods;

    }
}
