package com.group2.http.controller.admin;

import com.group2.model.Food;
import com.group2.util.support.Request;
import org.apache.commons.beanutils.converters.SqlDateConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "Foods", urlPatterns = {"/foods"})
public class Foods extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HashMap<String, Object> userInputs = new Request(request).all();
            if(userInputs.get("id") == null){
                if(new Food().insert(userInputs) != null){
                    response.sendRedirect("/foods?success_msg=New food is added");
                }else{
                    response.sendRedirect("/err_msg=Fail to add new message");
                }
            }else{
                if(new Food().where("id", Integer.parseInt(userInputs.get("id").toString())).update(userInputs) != null){
                    response.sendRedirect("/foods?success_msg=Food is successfuly updated");
                }else{
                    response.sendRedirect("/foods?success_msg=faild to update food");
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
            request.getSession().setAttribute("foods", new com.group2.model.Food().get());
        }catch(SQLException e){

        }catch(Exception e){

        }
        request.getRequestDispatcher("/view/pages/admin/foods.jsp").forward(request, response);
    }
}
