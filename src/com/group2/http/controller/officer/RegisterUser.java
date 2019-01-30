package com.group2.http.controller.officer;

import com.group2.model.Students;
import com.group2.model.User;
import com.group2.util.support.FileUpload;
import com.group2.util.support.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "RegisterUser", urlPatterns = {"/register student"})
public class RegisterUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> userInput = new FileUpload(request).uploadFile("profile pic/", "profile_pic");

        try {
            if(userInput.get("id") != null){
                int id = Integer.parseInt(userInput.get("id").toString());
                if (new Students().where("id", id).update(userInput) != null){
                    response.sendRedirect("/students list");
                }else{
                    response.getWriter().write("faild to register");
                }
            }else{
                if (new Students().insert(userInput) != null){
                    response.sendRedirect("/students list");
                }else{
                    response.getWriter().write("faild to register");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String update =request.getParameter("update");
        if(update != null){
            try {
                Students student = new Students().find(Integer.parseInt(update));
                request.getSession().setAttribute("student", student);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/view/pages/officer/register student.jsp").forward(request, response);
    }
}
