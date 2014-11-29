package com.socialnetwork.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socialnetwork.client.WallpostRestClient;
import com.socialnetwork.model.WallpostModel;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class WallpostBean {

    private String message;
    private int fromId;
    private int toId;
    private Date date;
    private List<WallpostModel> wallpostModelList;
    
    public WallpostBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<WallpostModel> getWallpostModelList() {
        return wallpostModelList;
    }

    public void setWallpostModelList(List<WallpostModel> wallpostModelList) {
        this.wallpostModelList = wallpostModelList;
    }
    
    public String postOnWall(){
        WallpostRestClient wallPostRestClient=new WallpostRestClient();
        WallpostModel wallpostModel = new WallpostModel();
        Gson gson = new Gson();
        String checkIfPostSuccess="false";
        String sendPost = "";
        
        
        fromId = Integer.parseInt(FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("fromId"));
        toId=Integer.parseInt(FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("toId"));
        
        wallpostModel.setMessage(message);
        wallpostModel.setFromId(fromId);
        wallpostModel.setToId(toId);
        
        sendPost=gson.toJson(wallpostModel);
        
        try {
            checkIfPostSuccess=gson.fromJson(wallPostRestClient.sendPost(URLEncoder.encode(sendPost,"UTF-8")), String.class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WallpostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        clearAll();
        if(checkIfPostSuccess.equalsIgnoreCase("true")){
            return "profile";
        }else{
            return "welcome";
        }
        
    }
    
    public List<WallpostModel> getAllWallpostToUser(int fromId){
        String gsonAnswer="";
        Gson gson = new Gson();
        WallpostRestClient wallPostRestClient=new WallpostRestClient();
        
        gsonAnswer = wallPostRestClient.getAllwallPostToUser(gson.toJson(fromId));
        
        wallpostModelList = gson.fromJson(gsonAnswer, new TypeToken<List<WallpostModel>>() {}.getType());
        return wallpostModelList;
    }
    
    public void clearAll(){
        message="";
        fromId=0;
        toId=0;       
    }
    
}
