package com.group2.http.controller.admin;

import com.group2.model.Backup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet(name = "DBRestore", urlPatterns = {"/restore"})
public class DBRestore extends HttpServlet {
    private Properties config;

    public DBRestore() throws IOException {
        this.config = new Properties();
        this.config.load(new FileInputStream("/root/IdeaProjects/Coffe-project/resource/db_config.properties"));
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("backup_id"));
        try {
            Backup backup = new Backup().find(id);
            String restoreCmd = "mysql -u "+this.config.getProperty("DB_USERNAME")+" -p "+
                    this.config.getProperty("DB_PASSWORD")+" "+
                    this.config.getProperty("DB_NAME")+" < "+backup.getSql_file();
            String[] executeCmd = new String[]{"mysql", this.config.getProperty("DB_NAME"),
                    "-u" + this.config.getProperty("DB_USERNAME"),
                    "-p" + this.config.getProperty("DB_PASSWORD"),
                    "-e", " source " + backup.getSql_file()};
            System.out.println(executeCmd.toString()+"\n");

            Process restoreProcess = Runtime.getRuntime().exec(executeCmd);
            int isExcuted = restoreProcess.waitFor();

            if(isExcuted == 0){
                response.sendRedirect("/database mamagment?success_msg=" +
                        "You have succesfuly restored your database from "+backup.getCreated_at().toString()+" backup");
            }else{
                response.sendRedirect("/database mamagment?err_msg=" +
                        "Faild to backup the required backup");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
