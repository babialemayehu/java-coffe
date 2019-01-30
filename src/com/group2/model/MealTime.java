package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MealTime extends Database {
    private int id;
    private Date from;
    private Date to;
    private String name;

    public MealTime() throws SQLException, IOException, ClassNotFoundException {
        super("meal_time");
    }

    public ArrayList<MealTime> get() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<MealTime> mealTimes = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            MealTime mealTime = getMealTime(result);
            mealTimes.add(mealTime);
        }
        return mealTimes;
    }

    public MealTime find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getMealTime(super._find(id));
    }

    private MealTime getMealTime(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        MealTime mealTime = new MealTime();
        mealTime.setId(result.getInt("id"));
        mealTime.setFrom(result.getTime("from"));
        mealTime.setTo(result.getTime("to"));
        mealTime.setName(result.getString("name"));
        return mealTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
