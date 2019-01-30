package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Students_meal extends Database {
    private int id;
    private int user_id;
    private int student_id;
    private int meal_program_id;

    public Students_meal() throws SQLException, IOException, ClassNotFoundException {
        super("students_meal");
    }

    public ArrayList<Students_meal> get() throws SQLException, IOException, ClassNotFoundException{
        ArrayList<Students_meal> students_meals = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            Students_meal students_meal = getStudents_meal(result);
            students_meals.add(students_meal);
        }
        return students_meals;
    }

    private Students_meal getStudents_meal(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        Students_meal students_meal = new Students_meal();
        students_meal.setId(result.getInt("id"));
        students_meal.setMeal_program_id(result.getInt("meal_program_id"));
        students_meal.setStudent_id(result.getInt("student_id"));
        students_meal.setUser_id(result.getInt("user_id"));
        return students_meal;
    }

    private Students_meal find(int id) throws SQLException, IOException, ClassNotFoundException {
        return getStudents_meal(super._find(id));
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getMeal_program_id() {
        return meal_program_id;
    }

    public void setMeal_program_id(int meal_program_id) {
        this.meal_program_id = meal_program_id;
    }

}
