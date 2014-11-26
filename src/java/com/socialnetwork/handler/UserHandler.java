package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.User;
import com.socialnetwork.model.UserModel;

public class UserHandler {
    
    public static String loginUser(String userStr, String passStr){
        UserModel user = new UserModel();
        User userDao = new User();
        Gson gson = new Gson();
        
        user.setUsername(userStr);
        user.setPassword(passStr);
        
        return gson.toJson(userDao.loginUser(user));
    }
    
}
