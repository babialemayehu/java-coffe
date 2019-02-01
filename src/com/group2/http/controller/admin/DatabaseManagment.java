package com.group2.http.controller.admin;

import com.group2.model.Backup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "DatabaseManagment", urlPatterns = {"/database mamagment"})
public class DatabaseManagment extends HttpServlet {
    private Properties config;

    public DatabaseManagment() throws IOException {
        this.config = new Properties();
        this.config.load(new FileInputStream("/root/IdeaProjects/Coffe-project/resource/db_config.properties"));
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = this.now("yyyy-mm-dd--HH:mm:ss");
        Runtime.getRuntime().exec("mysqldump -u "+this.config.getProperty("DB_USERNAME")
                +" -p"+this.config.getProperty("DB_PASSWORD")+" "+this.config.getProperty("DB_NAME")+" > \""
                +this.config.getProperty("DB_BACKUP_DIR")+date+"-backup.sql\"");

        response.getWriter().write("mysqldump -u "+this.config.getProperty("DB_USERNAME")
                +" -p"+this.config.getProperty("DB_PASSWORD")+" "+this.config.getProperty("DB_NAME")+" > \""
                +this.config.getProperty("DB_BACKUP_DIR")+date+"-backup.sql\"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Backup> backups = new Backup().get();
            request.setAttribute("backups", backups);
            request.getRequestDispatcher("/view/pages/admin/database management.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String now(String format) {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
