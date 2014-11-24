package com.socialnetwork.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.socialnetwork.dao.User;

@ManagedBean
@SessionScoped
public class UserBean {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    
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
    
    public String registerUser(){
        User user=new User();
        user.registerUser(this);
        return "testPage";
    }
    
}
