package com.group2.http.controller.admin;

import com.group2.util.support.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "MealTime", urlPatterns = {"/meal time"})
public class MealTime extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> userInput = new Request(request).all();

        try {
            if(userInput.get("id") ==null) {
                if (new com.group2.model.MealTime().insert(userInput) != null) {
                    response.sendRedirect("/meal%20time?success_msg=Successfully added new meal time");
                } else {
                    response.sendRedirect("/meal%20time?err_msg=Faild to add new meal time");
                }
            }else{
                if (new com.group2.model.MealTime().where("id", Integer.parseInt(userInput.get("id").toString())).update(userInput) != null) {
                    response.sendRedirect("/meal%20time?success_msg=Successfully updated meal time");
                } else {
                    response.sendRedirect("/meal%20time?err_msg=Faild to update meal time");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if(request.getParameter("update") != null){
                int meal_time_id = Integer.parseInt(request.getParameter("update"));
                com.group2.model.MealTime meal = new com.group2.model.MealTime().find(meal_time_id);
                request.getSession().setAttribute("update", meal);
            }else if((request.getParameter("delete") != null)){
                int meal_time_id = Integer.parseInt(request.getParameter("delete"));
                new com.group2.model.MealTime().where("id", meal_time_id);
                response.sendRedirect("/meal time?message=You have successfuly deleted");
                return;
            }
            com.group2.model.MealTime mealTime= new com.group2.model.MealTime();
            request.getSession().setAttribute("mealTimes",mealTime.get());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/pages/admin/meal time.jsp").forward(request, response);
    }
}
