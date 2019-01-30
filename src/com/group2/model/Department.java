package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Department extends Database {
    private int id;
    private String name;

    public Department() throws SQLException, IOException, ClassNotFoundException {
        super("department");
    }

    public ArrayList<Department> get() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Department> meals = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            Department meal = getDepartment(result);
            meals.add(meal);
        }
        return meals;
    }

    private Department getDepartment(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        Department meal = new Department();
        meal.setId(result.getInt("id"));
        meal.setName(result.getString("name"));
        return meal;
    }

    public Department find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getDepartment(super._find(id));
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
