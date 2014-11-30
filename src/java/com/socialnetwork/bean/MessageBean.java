/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialnetwork.bean;

import com.google.gson.Gson;
import com.socialnetwork.client.MessageRestClient;
import com.socialnetwork.model.MessageModel;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessageBean {
    
    private int id;
    private String message;
    private String fromUsername;
    private String toUsername;
    private Boolean isRead;
    private Date datetime;
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    public int getMessageCount(){
        Gson gson = new Gson();
        MessageRestClient messageClient = new MessageRestClient();
        String fromGson="";
        fromGson=messageClient.getMessageCount(gson.toJson(userBean.getUserModel().getUsername()));
        
        return gson.fromJson(fromGson, Integer.class);
    }
    
    public String sendMessage(){
        Gson gson = new Gson();
        MessageRestClient messageClient = new MessageRestClient();
        String sendMessageSuccess = "false";
        MessageModel messageModel = new MessageModel(this.message,userBean.getUserModel().getUsername(),userBean.getUsername());
        
        try {
            sendMessageSuccess=messageClient.sendMessage(URLEncoder.encode(gson.toJson(messageModel),"UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(sendMessageSuccess.equalsIgnoreCase("true")){
            return "message";
        }else{
            return "welcome";
        }
       
    }
    
    
    
    
    
}
