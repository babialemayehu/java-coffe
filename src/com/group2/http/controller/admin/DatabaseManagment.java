package com.group2.http.controller.admin;

import com.group2.model.Backup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "DatabaseManagment", urlPatterns = {"/database mamagment"})
public class DatabaseManagment extends HttpServlet {
    private Properties config;

    public DatabaseManagment() throws IOException {
        this.config = new Properties();
        this.config.load(new FileInputStream("/root/IdeaProjects/Coffe-project/resource/db_config.properties"));
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String backupFileName = this.now("yyyy-mm-dd--HH:mm:ss")+"-backup.sql";
        String backupFileDir = this.config.getProperty("DB_BACKUP_DIR")+backupFileName;
        String backupCmd = "mysqldump -u "+this.config.getProperty("DB_USERNAME")
                +" -p"+this.config.getProperty("DB_PASSWORD")+" "+this.config.getProperty("DB_NAME")+" -r "
                +backupFileDir;
        Process backupProcess =  Runtime.getRuntime().exec(backupCmd);

        HashMap<String, Object> backedUp = new HashMap<>();
        backedUp.put("sql_file", backupFileDir);
        backedUp.put("comment", request.getParameter("comment"));
        int processComplte = -20;
        try {
            processComplte = backupProcess.waitFor();
            if(processComplte == 0){
                new Backup().insert(backedUp);
            }
            response.getWriter().write((processComplte == 0)?"true":"false");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
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
