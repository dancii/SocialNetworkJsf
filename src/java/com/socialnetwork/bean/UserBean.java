package com.socialnetwork.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socialnetwork.client.UserRestClient;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.socialnetwork.model.UserModel;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;


@ManagedBean
@SessionScoped
public class UserBean {

    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private UserModel userModel= null;
    private List<UserModel> userList = new ArrayList<UserModel>();
    private UserModel userProfile=null;
    
    public UserBean() {
        UserModel mod = new UserModel();
        mod.setUsername("");
        userList.add(mod);
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<UserModel> getUserList() {
        return userList;
    }

    public void setUserList(List<UserModel> userList) {
        this.userList = userList;
    }

    public UserModel getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserModel userProfile) {
        this.userProfile = userProfile;
    }
    
    
    public String checkProfile(){
        userProfile = userModel;
        return "profile";
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
        
        if(userModel!=null){
            clearAll();
            return "welcome";
        }else{
            clearAll();
            return "index";
        }
        
    }
    
    public ArrayList<String> searchUserByUsername(String query){
        Gson gson = new Gson();
        UserRestClient userClient = new UserRestClient();
        ArrayList<String> strList = new ArrayList<String>();
        userList = new ArrayList<UserModel>();
        
        username = query;
        userList = gson.fromJson(userClient.searchUser(username),new TypeToken<List<UserModel>>() {}.getType());
        
        if(!userList.isEmpty()){
            for(int i = 0; i < userList.size(); i++) {
                strList.add(userList.get(i).getUsername());
            }
        }else{
            strList.add("No results");
        }
        
        
        return strList;
    }
    
    public void redirectToProfile(AjaxBehaviorEvent event){
        
        if(!userList.isEmpty()){
            
            for(int i=0;i<userList.size();i++){
                if(userList.get(i).getUsername().equals(username)){
                    userProfile=userList.get(i);
                }
            }
            
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else{
            username="";
        }
    }
    
    public void clearAll(){
        id=0;
        username="";
        password="";
        firstname="";
        lastname="";
        email="";
    }
    
}
