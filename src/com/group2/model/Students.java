package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Students extends Database {
    private int id;
    private String first_name;
    private String father_name;
    private String gfather_name;
    private String reg_id;
    private int department_id;
    private int dorm_block;
    private int dorm_room;
    private int accadamic_year;

    public Students() throws SQLException, IOException, ClassNotFoundException {
        super("students");
    }

    public ArrayList<Students> get() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Students> students = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            Students student = getStudents(result);
            students.add(student);
        }
        return students;
    }
    public Students find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getStudents(super._find(id));
    }
    private Students getStudents(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        Students student = new Students();
        student.setId(result.getInt("id"));
        student.setAccadamic_year(result.getInt("accadamic_year"));
        student.setDepartment_id(result.getInt("deparment_id"));
        student.setDorm_block(result.getInt("dorm_block"));
        student.setDorm_room(result.getInt("dorm_room"));
        student.setFather_name(result.getString("father_name"));
        student.setFirst_name(result.getString("first_name"));
        student.setGfather_name(result.getString("gfather_name"));
        student.setReg_id(result.getString("reg_id"));
        return student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getGfather_name() {
        return gfather_name;
    }

    public void setGfather_name(String gfather_name) {
        this.gfather_name = gfather_name;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getDorm_block() {
        return dorm_block;
    }

    public void setDorm_block(int dorm_block) {
        this.dorm_block = dorm_block;
    }

    public int getDorm_room() {
        return dorm_room;
    }

    public void setDorm_room(int dorm_room) {
        this.dorm_room = dorm_room;
    }

    public int getAccadamic_year() {
        return accadamic_year;
    }

    public void setAccadamic_year(int accadamic_year) {
        this.accadamic_year = accadamic_year;
    }

}
