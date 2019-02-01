package com.group2.http.controller;

import com.group2.model.Food;
import com.group2.model.Students;
import com.group2.model.Students_meal;
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
        try {
            if(!Auth.isLogedin(request)) {
                response.sendRedirect("/login");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        request.getRequestDispatcher("/view/pages/admin/dashboard.jsp").forward(request,response);
        try{
           // Auth.check(request, response);
            int role = Auth.user(request).getRole_id();
            switch (role){
                case 1:
                    this.admin(request);
                    request.getRequestDispatcher("/view/pages/admin/dashboard.jsp").forward(request, response);
                    break;
                case 2:
                    this.officer(request);
                    request.getRequestDispatcher("/view/pages/officer/dashboard.jsp").forward(request, response);
                    break;
                case 3:
                    request.getRequestDispatcher("/view/pages/tiker/dashboard.jsp").forward(request, response);
                    break;
            }
        }catch(SQLException ex){
            System.out.println(ex.getSQLState());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void admin(HttpServletRequest request){
        try{
            int user = new User().get().size();
            int students = new Students().get().size();

            User officer = (User)new User().where("role_id", 2);
            User tiker = (User)new User().where("role_id", 3);

            int officers = officer.get().size();
            int tikers = tiker.get().size();

            request.setAttribute("users", user);
            request.setAttribute("officer", officers);
            request.setAttribute("tiker", tikers);
            request.setAttribute("student", students);

        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void officer(HttpServletRequest request){
        try{
            Students student = (Students)new Students().where("active", 1);
            int students = student.get().size();
            request.setAttribute("student", students);

            int meals = new Students_meal().get().size();
            request.setAttribute("meal",  meals);

            Students_meal allowed = (Students_meal)new Students_meal().where("is_allowed", 1);
            int allowedMeals = allowed.get().size();
            request.setAttribute("allowed",  allowedMeals);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void tiker(HttpServletRequest request){

    }
}
