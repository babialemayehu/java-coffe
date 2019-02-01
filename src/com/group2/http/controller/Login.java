package com.group2.http.controller;

import com.group2.util.auth.Auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    private Properties config;
    public Login(){
        this.config = new Properties();
        try {
            this.config.load(new FileInputStream("/root/IdeaProjects/Coffe-project/resource/db_config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Logger log = Logger.getLogger("Login log");

        String logFile = this.config.getProperty("LOG_DIR")+"login/"+this.now("yyyy-mm-dd")+".log";
        File file = new File(logFile);
        file.createNewFile();
        FileHandler logFileHandler = new FileHandler(logFile, true);

        log.addHandler(logFileHandler);
        logFileHandler.setFormatter(new SimpleFormatter());

        if(Auth.login(request, "worker_id") != null){
            try {
                log.info(Auth.user(request).getWorker_id()+" is loged in");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/dashboard");
        }else{
            log.warning(request.getParameter("worker_id")+" is faild to login");

            response.sendRedirect("/login?err_msg=Invalid username or password");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(Auth.isLogedin(request)){
                response.sendRedirect("/dashboard");
                return;
            }
        }catch (Exception e){

        }
        response.addHeader("content-type", "text/html");
        request.getRequestDispatcher("/view/pages/login.jsp").forward(request,response);
    }

    private String now(String format) {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
