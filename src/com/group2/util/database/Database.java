package com.group2.util.database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database extends Connection {
    private String tableName;
    private String whereClouse = "";

    public Database(String tableName) throws SQLException, IOException, ClassNotFoundException {
        super();
        this.tableName = tableName;
    }

    public void print(){
        System.out.println(this.whereClouse);
    }

    public Database update(HashMap<String, Object> set) throws SQLException{
        String setClose = "UPDATE " + this.tableName +" SET ";

        boolean first = true;

        for(Map.Entry<String, Object> s: set.entrySet()){
            if(first){
                first = false;
            }else{
                setClose += ",";
            }
            String value = (s.getValue() instanceof  String)?"'"+s.getValue()+"'":s.getValue().toString();
            setClose += s.getKey()+"="+value;
        }
        String updateQuery = setClose+" "+this.whereStatement();
        PreparedStatement updateStatement = this.getConnection().prepareStatement(updateQuery);
        if(updateStatement.executeUpdate() >0)
            return this;
        return null;
    }

    public Database insert(HashMap<String, Object> set) throws SQLException{
        String insertSql = "INSERT INTO "+this.tableName+"( $col ) VALUES( $values )";
        String col= "";
        String values = "";

        boolean first = true;

        for(Map.Entry<String, Object> s: set.entrySet()){
            if(first){
                first = false;
            }else{
                col += ",";
                values += ",";
            }
            values += (s.getValue() instanceof  String)?"'"+s.getValue()+"'":s.getValue().toString();
            col += s.getKey();
        }
        insertSql = insertSql.replace("$col", col);
        insertSql = insertSql.replace("$values",values);
        PreparedStatement insertStatment = this.getConnection().prepareStatement(insertSql);
        if(insertStatment.execute()){
            return this;
        }
        return null;
    }

    public Database delete() throws SQLException{
        String deleteSql = "DELETE FROM "+this.tableName + " " + this.whereStatement();
        PreparedStatement deleteState = this.getConnection().prepareStatement(deleteSql);
        if(deleteState.execute()){
            return this;
        }
        return null;
    }

    public ResultSet _find(int id) throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement idStatement = super.getConnection().prepareStatement("SELECT * FROM "+this.tableName+" WHERE id=?");
        idStatement.setInt(1,id);
        ResultSet row = idStatement.executeQuery();
        if(row.next()){
            return row;
        }
        return null;
    }

    public Database where(String col, Where op, String val){
        this.whereClouse += ((this.whereClouse.isEmpty())?"":" AND ")+ col + op.getOpperation() +"'"+val+"'";
        return this;
    }

    public Database where(String col, Where op, Number val){
        this.whereClouse += ((this.whereClouse.isEmpty())?"":" AND ")+col+ op.getOpperation() +val;
        return this;
    }

    public Database orWhere(String col, Where op, String val){
        this.whereClouse += ((this.whereClouse.isEmpty())?"":" OR ")+col+ op.getOpperation() +"'"+val+"'";
        return this;
    }

    public Database orWhere(String col, Where op, Number val){
        this.whereClouse += ((this.whereClouse.isEmpty())?"":" OR ")+col+ op.getOpperation() +val;
        return this;
    }

    public Database where(String col, String val){
        this.where(col,Where.EQUAL,val);
        return this;
    }

    public Database where(String col, Number val){
        this.where(col,Where.EQUAL,val);
        return this;
    }

    public Database orWhere(String col, String val){
        this.orWhere(col,Where.EQUAL,val);
        return this;
    }

    public Database orWhere(String col, Number val){
        this.orWhere(col,Where.EQUAL,val);
        return this;
    }

    public ResultSet _get() throws SQLException{
        String queryStatement = "SELECT * FROM " + this.tableName+ this.whereStatement();
        return executeQuery(queryStatement);
    }

    public ResultSet _first() throws SQLException{
        String queryStatement = "SELECT * FROM " + this.tableName+" "+this.whereStatement();
        queryStatement += " LIMIT 1";
        PreparedStatement getStatement = this.getConnection().prepareStatement(queryStatement);
        ResultSet row = getStatement.executeQuery();
        if(row.next()){
            return row;
        }
        return null;
    }

    private String whereStatement(){
        return (this.whereClouse.isEmpty())? "": (" WHERE " + this.whereClouse);
    }

    public ResultSet hasMany(String table) throws SQLException{
        String query = "SELECT * FROM "+ table+ " WHERE "+table+"."+ table+"_id=" +
                "( SELECT id FROM "+this.whereStatement() +")";
        return this.executeQuery(query);
    }

    public ResultSet belongsToMany(){
        return null;
    }

    public ResultSet hasOne(String table) throws SQLException {
        String query = "SELECT * FROM " + table + "where "+table+".user_id = "
                +" (SELECT id FROM "+ this.tableName+this.whereStatement();
        return this.executeOneQuery(query);
    }

    public ResultSet belongsTo(String table) throws SQLException {
        String query ="SELECT * FROM "+ table + "where id="+
                "(SELECT "+table+"_id FROM "+this.tableName+whereStatement();
        return this.executeOneQuery(query);
    }

    private ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement getStatement = this.getConnection().prepareStatement(query);

        ResultSet result = getStatement.executeQuery();
       return result;
    }

    private ResultSet executeOneQuery(String query) throws SQLException{
        PreparedStatement getStatement = this.getConnection().prepareStatement(query);

        ResultSet result = getStatement.executeQuery();
        result.next();
        return  result;
    }

}
