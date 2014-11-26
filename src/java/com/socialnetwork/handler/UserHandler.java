package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.User;
import com.socialnetwork.model.UserModel;

public class UserHandler {
    
    public static String loginUser(String username, String pass){
        UserModel user = new UserModel();
        User userDao = new User();
        Gson gson = new Gson();
        
        user.setUsername(username);
        user.setPassword(pass);
        
        return gson.toJson(userDao.loginUser(user));
    }
    
    public static String registerUser(String username, String pass, String firstname, String lastname, String email){
        UserModel user = new UserModel();
        User userDao=new User();
        Gson gson = new Gson();
        
        user.setUsername(username);
        user.setPassword(pass);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        
        return userDao.registerUser(user);
    }
    
}
