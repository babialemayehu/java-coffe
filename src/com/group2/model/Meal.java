package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Meal  extends Database {
    private int id;
    private int meal_time_id;
    private int week;
    private int food;

    public Meal() throws SQLException, IOException, ClassNotFoundException {
        super("meal");
    }
    public ArrayList<Meal> get() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Meal> meals = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            Meal meal = getMeal(result);
            meals.add(meal);
        }
        return meals;
    }

    private Meal getMeal(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        Meal meal = new Meal();
        meal.setId(result.getInt("id"));
        meal.setMeal_time_id(result.getInt("meal_time_id"));
        meal.setWeek(result.getInt("week"));
        meal.setFood(result.getInt("food"));
        return meal;
    }

    public Meal find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getMeal(super._find(id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeal_time_id() {
        return meal_time_id;
    }

    public void setMeal_time_id(int meal_time_id) {
        this.meal_time_id = meal_time_id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

}
