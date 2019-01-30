package com.group2.http.controller.admin;

import com.group2.model.User;
import com.group2.util.security.Hash;
import com.group2.util.support.Email;
import com.group2.util.support.Request;
import com.group2.util.support.Str;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
@WebServlet(name = "CreateUser", urlPatterns = {"/create user"})
public class CreateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            HashMap<String, Object> userInput = new Request(request).all();

            if(!userInput.containsKey("id")) {
                String newPass = Str.random(6);
                String hashdPass =  Hash.sha_256(newPass);
                userInput.put("password",hashdPass);
                userInput.put("remember",Hash.sha_512(userInput.get("worker_id")+hashdPass));
                new Email(userInput.get("email").toString()).send("Your Coffe password", newPass);
                if (user.insert(userInput) != null) {
                    response.getWriter().write("success full inesrted");
                } else {
                    response.getWriter().write("faild");
                }
            }else{
                int id = Integer.parseInt(userInput.get("id").toString());
                if (user.find(id).update(userInput) != null) {
                    response.getWriter().write("success full Upate");
                } else {
                    response.getWriter().write("faild");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String update =request.getParameter("update");
        if(update != null){
            try {
                User user = new User().find(Integer.parseInt(update));
                request.getSession().setAttribute("user", user);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/view/pages/admin/create user.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        resp.getWriter().write("delteded");
    }

}
