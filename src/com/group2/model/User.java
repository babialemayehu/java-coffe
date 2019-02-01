package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Database{

    private int id;
    private String first_name;
    private String father_name;
    private String gfather_name;
    private String email;
    private String worker_id;
    private String phone;
    private int reg_id;
    private boolean active;
    private int setup;

    public void setSetup(int setup){ this.setup = setup; }
    public int getSetup(){ return this.setup; }
    public void setActive(boolean active){
        this.active = active;
    }
    public boolean getActive(){
        return this.active;
    }
    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    private int role_id;

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    private String remember;

    public User() throws SQLException, IOException, ClassNotFoundException {
        super("users");
    }

    public ArrayList<User> get() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            User user = getUser(result);
            users.add(user);
        }
        return users;
    }

    private User getUser(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        if(result == null)
            return null;
        User user = new User();
        user.where("id", result.getInt("id"));
        user.setId(result.getInt("id"));
        user.setEmail(result.getString("email"));
        user.setFirst_name(result.getString("first_name"));
        user.setFather_name(result.getString("father_name"));
        user.setGfather_name(result.getString("gfather_name"));
        user.setPhone(result.getString("phone"));
        user.setWorker_id(result.getString("worker_id"));
        user.setRemember(result.getString("remember"));
        user.setRole_id(result.getInt("role_id"));
        user.setActive(result.getBoolean("active"));
        user.setSetup(result.getInt("setup"));
        return user;
    }

    public User find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getUser(super._find(id));
    }
    public User first()  throws SQLException, IOException, ClassNotFoundException{
        return this.getUser(super._first());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }
}
