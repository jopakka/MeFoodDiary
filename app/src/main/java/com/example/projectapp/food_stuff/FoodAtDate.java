package com.example.projectapp.food_stuff;

/**
 * Food or meal with day
 * @author Elmeri Katainen
 */
public class FoodAtDate {
    private int day;
    private int month;
    private int year;
    private Food food;
    private Meal meal;

    /**
     * Constructor for food
     * @param day Day eaten
     * @param month Month eaten
     * @param year Year eater
     * @param food What food
     */
    public FoodAtDate(int day, int month, int year, Food food) {
        this.food = food;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Constructor for meal
     * @param day Day eaten
     * @param month Month eaten
     * @param year Year eater
     * @param meal What meal
     */
    public FoodAtDate(int day, int month, int year, Meal meal) {
        this.meal = meal;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Get which day food or meal has been eaten
     * @return Day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Get which month food or meal has been eaten
     * @return Month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Get which year food or meal has been eaten
     * @return Year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Get which food has been eaten
     * @return Food
     */
    public Food getFood() {
        return this.food;
    }

    /**
     * Get which meal has been eaten
     * @return Meal
     */
    public Meal getMeal() {
        return this.meal;
    }

    /**
     * Checks if is meal
     * @return Boolean
     */
    public boolean isMeal() {
        if(food == null)
            return true;
        else
            return false;
    }
}
