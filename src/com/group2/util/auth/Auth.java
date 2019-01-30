package com.group2.util.auth;

import com.group2.model.User;
import com.group2.util.security.Hash;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Auth extends  User{
    public Auth() throws SQLException, IOException, ClassNotFoundException {
    }

    public static User login(HttpServletRequest request) {
        return Auth.login(request, "username", "password");
    }

    public static User login(HttpServletRequest request, String username) {
        return Auth.login(request, username, "password");
    }

    public static User login(HttpServletRequest request, String username, String password){
        String _username = request.getParameter(username);
        String _password = request.getParameter(password);
        try {
            User loginUser = new User();
            loginUser = (User)loginUser.where(username, _username).where(password, Hash.sha_256(_password));
            loginUser = loginUser.first();
            if(loginUser != null){
                HttpSession session =request.getSession();
                session.setAttribute("remember",loginUser.getRemember());
                return loginUser.first();
            }else{
                return null;
            }
        } catch (SQLException|IOException|ClassNotFoundException| NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User user(HttpServletRequest request) throws SQLException, IOException, ClassNotFoundException {
        Object remember_session = request.getSession().getAttribute("remember");
        User logedInUser = new User();
        if(remember_session == null){
            return null;
        }else{
            String remember = remember_session.toString();
            logedInUser = (User)logedInUser.where("remember", remember);
            return logedInUser.first();
        }
    }

    public static int id(HttpServletRequest request) throws SQLException, IOException, ClassNotFoundException {
        User user = Auth.user(request);
        if(user != null){
            return user.getId();
        }
        return -1;
    }

    public static void check(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        if(Auth.user(request) == null){
            response.sendRedirect("/login");
        }
    }

    public static boolean isLogedin(HttpServletRequest request) throws SQLException, IOException, ClassNotFoundException {
        return (Auth.user(request) != null);
    }
}
