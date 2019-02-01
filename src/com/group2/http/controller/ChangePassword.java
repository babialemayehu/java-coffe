package com.group2.http.controller;

import com.group2.model.User;
import com.group2.util.auth.Auth;
import com.group2.util.security.Hash;
import com.group2.util.support.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ChangePassword", urlPatterns = {"/change password"})
public class ChangePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> password = new HashMap<>();
        try{
            password.put("password", Hash.sha_256(request.getParameter("password")));
            password.put("setup", 1);
            Auth.user(request).update(password);
            response.sendRedirect("/?password=Password successfuly updated");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
