package com.group2.http.controller.admin;

import com.group2.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "DeleteUser", urlPatterns = {"/delete user"})
public class DeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("delete"));
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        HashMap<String, Object> toUpdte = new HashMap<>();
        toUpdte.put("active", (active)?0:1);
        try {
            new User().find(user_id).update(toUpdte);
            response.sendRedirect("/user profile?user="+user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
