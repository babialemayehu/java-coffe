package com.group2.http.controller;

import com.group2.util.auth.Auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if(Auth.login(request, "worker_id") != null){
            response.sendRedirect("/dashboard");
        }else{
            out.write("faild to login");
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
}
