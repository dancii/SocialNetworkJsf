package com.socialnetwork.bean;

import com.google.gson.Gson;
import com.socialnetwork.client.UserRestClient;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.socialnetwork.dao.User;
import com.socialnetwork.model.UserModel;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class UserBean {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private UserModel userModel=null;
    
    public UserBean() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
    
    
    
    public String registerUser(){
        String registerSuccess="";
        UserRestClient userClient = new UserRestClient();
        Gson gson = new Gson();
        String gsonUserReg="";
        UserModel userModel = new UserModel();
        
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        
        gsonUserReg=gson.toJson(userModel);
        System.out.println("TEEEEEEEEEEEEEESTSTTT!!!!!!!"+gsonUserReg);
        try {
            registerSuccess=userClient.registerUser(URLEncoder.encode(gsonUserReg,"UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(registerSuccess.equalsIgnoreCase("true")){
            return "index";
        }else{
            return "register";
        }
    }
    
    public String loginUser(){
        Gson gson = new Gson();
        UserRestClient userClient = new UserRestClient();
        
        userModel = gson.fromJson(userClient.loginUser(username, password), UserModel.class);
        System.out.println("CHECKIING!!!!!!!!!!!!!!!!"+userModel.getFirstname());
        if(userModel!=null){
            return "welcome";
        }else{
            return "index";
        }
    }
    
}
