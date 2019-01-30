package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Roles extends Database {
    private int id;
    private String name;

    public Roles() throws SQLException, IOException, ClassNotFoundException {
        super("roles");
    }

    public ArrayList<Roles> get() throws SQLException, ClassNotFoundException, IOException{
        ArrayList<Roles> roles = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            Roles role = getRoles(result);
            roles.add(role);
        }
        return roles;
    }

    public Roles find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getRoles(super._find(id));
    }

    private Roles getRoles(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        Roles role = new Roles();
        role.setId(result.getInt(1));
        role.setName(result.getString(2));
        return role;
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
