package com.group2.http.controller.tiker;

import com.google.gson.JsonObject;
import com.group2.model.Meal;
import com.group2.model.MealTime;
import com.group2.model.Students;
import com.group2.model.Students_meal;
import com.group2.util.auth.Auth;
import com.group2.util.database.Database;
import com.group2.util.database.Where;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "SearchStudent", urlPatterns = {"/student"})
public class SearchStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("search");
        try {
            Students student = (Students)new Students().where("reg_id",key);

            MealTime mealTime = (MealTime)new MealTime()
                    .where("from", Where.LESSTHANEQUAL, this.now("HH:mm:ss"))
                    .where("to", Where.GREATERTHANEQUAL, this.now("HH:mm:ss"));
            if(mealTime.first() == null){
                response.setHeader("status", "406");
                response.getWriter().write("Sorry,This is not meal time.");
                return;
            }else{
                int mealTime_id = mealTime.first().getId();
                boolean eligiblity = isEligiblity(student, mealTime_id);

                register(request, student, mealTime_id, eligiblity);
                response.getWriter().write(this.responceJson(student.first(), eligiblity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isEligiblity(Students student, int mealTime_id) throws SQLException, IOException, ClassNotFoundException {
        Students_meal meals = (Students_meal)new Students_meal()
                .where("created_at", Where.GREATERTHANEQUAL,  this.now("yyyy-MM-dd")+" 00:00:00")
                .where("created_at",Where.LESSTHANEQUAL, this.now("yyyy-MM-dd")+" 23:59:59")
                .where("meal_time_id", mealTime_id)
                .where("student_id", student.first().getId());
        return meals.get() == null;
    }

    private void register(HttpServletRequest request, Students student, int mealTime_id, boolean eligiblity) throws SQLException, IOException, ClassNotFoundException {
        HashMap<String, Object> row = new HashMap<>();
        row.put("user_id", new Auth().id(request));
        row.put("student_id", student.first().getId());
        row.put("meal_time_id", mealTime_id);
        row.put("is_allowed", eligiblity);

        Students_meal newStudentMeal = (Students_meal) new Students_meal().insert(row);
    }

    private String now(String format) {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    private String responceJson(Students stud, boolean eligible){
        JsonObject obj = new JsonObject();
        obj.addProperty("id", stud.getId());
        obj.addProperty("firstname", stud.getFirst_name());
        obj.addProperty("fathername", stud.getFather_name());
        obj.addProperty("gfathername", stud.getGfather_name());
        obj.addProperty("profile_pic", stud.getProfile_pic());
        obj.addProperty("reg_id", stud.getReg_id());
        obj.addProperty("eligible", eligible);
        return obj.toString();
    }
}
