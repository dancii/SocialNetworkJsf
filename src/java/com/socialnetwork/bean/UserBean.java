package com.socialnetwork.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.socialnetwork.dao.User;
import com.socialnetwork.model.UserModel;

@ManagedBean
@SessionScoped
public class UserBean {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private UserModel userModel=null;
    
    public UserBean() {
    }

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
        Boolean registerSuccess=false;
        User user=new User();
        user.registerUser(this);
        if(registerSuccess){
            return "index";
        }else{
            return "register";
        }
        
    }
    
    public String loginUser(){
        
        User user=new User();
        userModel=user.loginUser(this);
        if(userModel!=null){
            return "welcome";
        }else{
            return "index";
        }
    }
    
}
