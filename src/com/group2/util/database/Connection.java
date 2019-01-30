package com.group2.util.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {
    private java.sql.Connection connection;
    private Properties config;
    public Connection() throws SQLException, ClassNotFoundException, IOException {

        this.config = new Properties();
        this.config.load(new FileInputStream("/root/IdeaProjects/Coffe-project/resource/db_config.properties"));

        Class.forName(this.config.getProperty("DB_DRIVER"));
        this.connection = DriverManager.getConnection(
                this.config.getProperty("DB_URL"),
                this.config.getProperty("DB_USERNAME"),
                this.config.getProperty("DB_PASSWORD"));
    }

    public java.sql.Connection getConnection(){ return this.connection; }
}
