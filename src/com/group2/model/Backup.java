package com.group2.model;

import com.group2.util.database.Database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Backup  extends Database {
    private int id;
    private String sql_file;

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    private Date created_at;

    public Backup() throws SQLException, IOException, ClassNotFoundException {
        super("backup");
    }

    public ArrayList<Backup> get() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Backup> backups = new ArrayList<>();
        ResultSet result = super._get();
        while(result.next()){
            Backup backup = getBackup(result);
            backups.add(backup);
        }
        return backups;
    }

    private Backup getBackup(ResultSet result) throws SQLException, IOException, ClassNotFoundException {
        Backup backup = new Backup();
        backup.setId(result.getInt("id"));
        backup.setSql_file(result.getString("sql_file"));
        backup.setCreated_at(result.getDate("created_at"));
        return backup;
    }

    public Backup find(int id) throws SQLException, IOException, ClassNotFoundException{
        return this.getBackup(super._find(id));
    }

    public String getSql_file() {
        return sql_file;
    }

    public void setSql_file(String sql_file) {
        this.sql_file = sql_file;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}