package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Food extends Database {
    private int id;

    private String name;

    public Food() throws SQLException, IOException, ClassNotFoundException {
        super("foods");
    }

    public ArrayList<Food> get() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Food> meals = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            Food meal = getFood(result);
            meals.add(meal);
        }
        return meals;
    }

    private Food getFood(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        Food meal = new Food();
        meal.setId(result.getInt("id"));
        meal.setName(result.getString("name"));
        return meal;
    }

    public Food find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getFood(super._find(id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
