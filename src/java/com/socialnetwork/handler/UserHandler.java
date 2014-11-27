package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.User;
import com.socialnetwork.model.UserModel;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {
    
    public static String loginUser(String username, String pass){
        UserModel user = new UserModel();
        User userDao = new User();
        Gson gson = new Gson();
        
        user.setUsername(username);
        user.setPassword(pass);
        
        return gson.toJson(userDao.loginUser(user));
    }
    
    public static String registerUser(String user){
        UserModel userModel = new UserModel();
        User userDao=new User();
        Gson gson = new Gson();
        
        userModel = gson.fromJson(user, UserModel.class);

        return userDao.registerUser(userModel);
    }
    
    public static String searchUserByUsername(String username){
        String searchUsername ="";
        User userDao = new User();
        Gson gson = new Gson();
        List<UserModel> userList=new ArrayList<UserModel>();
        
        searchUsername = gson.fromJson(username, String.class);
        userList = userDao.searchUserByUsername(searchUsername);
        
        return gson.toJson(userList);
        
    }
}
