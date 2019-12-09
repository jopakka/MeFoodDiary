package com.example.projectapp.food_stuff;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Meal {
    private List<Food> foods;
    private String nimi;

    public Meal(List<Food> list, String name) {
        foods = new ArrayList<>();
        foods = list;
        nimi = name;
    }

    public List<Food> getMeal() {
        return foods;
    }

    @Override
    public String toString() {
        return nimi;
    }
}
