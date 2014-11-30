package com.socialnetwork.model;

import java.util.Date;

public class MessageModel {
    
    private int id;
    private String message;
    private String fromUsername;
    private String toUsername;
    private Boolean isRead;
    private Date datetime;

    public MessageModel(String message, String fromUsername, String toUsername){
        this.message=message;
        this.fromUsername=fromUsername;
        this.toUsername=toUsername;
    }
    
    public MessageModel(int id, String message, String fromUsername, String toUsername, Boolean isRead, Date datetime){
        this.id=id;
        this.message=message;
        this.fromUsername=fromUsername;
        this.toUsername=toUsername;
        this.isRead=isRead;
        this.datetime=datetime;
    }
    
    public MessageModel(){}
    
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
    
}
