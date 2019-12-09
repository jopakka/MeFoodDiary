package com.example.projectapp.food_stuff;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Meal {
    private ArrayList<Food> foods;
    private String nimi;

    public Meal(ArrayList<Food> list, String name) {
        foods = new ArrayList<>();
        foods = list;
        nimi = name;

    }
    public ArrayList getMeal() {
        return foods;

    }

    @Override
    public String toString() {

        return nimi;
    }
}
