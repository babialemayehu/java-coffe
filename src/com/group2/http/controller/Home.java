package com.group2.http.controller;

import com.group2.model.User;
import com.group2.util.auth.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet(name = "Home", urlPatterns = {"/dashboard"})
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        pr.write("home get");
//        request.getRequestDispatcher("/view/pages/admin/dashboard.jsp").forward(request,response);
        try{
           // Auth.check(request, response);
            int role = Auth.user(request).getRole_id();
            switch (role){
                case 1:
                    request.getRequestDispatcher("/view/pages/admin/dashboard.jsp").forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("/view/pages/officer/dashboard.jsp").forward(request, response);
                    break;
                case 3:
                    request.getRequestDispatcher("/tiker.jsp").forward(request, response);
                    break;
            }
        }catch(SQLException ex){
            System.out.println(ex.getSQLState());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
