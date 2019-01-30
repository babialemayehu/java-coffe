package com.group2.http.controller.admin;

import com.group2.model.*;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserProfile", urlPatterns = {"/user profile"})
public class UserProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user"));
        try {
            User user =  new User().find(user_id);
            Roles role =  new Roles().find(user.getRole_id());
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", role);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/pages/admin/user profile.jsp").forward(request, response);
    }
}
