package com.socialnetwork.bean;

import com.google.gson.Gson;
import com.socialnetwork.client.UserRestClient;
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
        
        registerSuccess=userClient.registerUser(username, password, firstname, lastname, email);
        
        if(registerSuccess.equalsIgnoreCase("true")){
            return "index";
        }else{
            return "register";
        }
    }
    
    public String loginUser(){
        Gson gson = new Gson();
        UserRestClient userClient = new UserRestClient();
        
        /*User user=new User();
        userModel=user.loginUser(this);*/
        
        userModel = gson.fromJson(userClient.loginUser(username, password), UserModel.class);
        System.out.println("CHECKIING!!!!!!!!!!!!!!!!"+userModel.getFirstname());
        if(userModel!=null){
            return "welcome";
        }else{
            return "index";
        }
    }
    
}
