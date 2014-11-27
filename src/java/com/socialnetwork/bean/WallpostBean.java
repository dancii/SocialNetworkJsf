package com.socialnetwork.bean;

import com.google.gson.Gson;
import com.socialnetwork.client.WallpostRestClient;
import com.socialnetwork.model.WallpostModel;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class WallpostBean {

    private String message;
    private int fromId;
    private int toId;
    private Date date;
    
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
    
    public String postOnWall(){
        WallpostRestClient wallPostRestClient=new WallpostRestClient();
        WallpostModel wallpostModel = new WallpostModel();
        Gson gson = new Gson();
        String checkIfPostSuccess="false";
        
        
        fromId = Integer.parseInt(FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("fromId"));
        toId=Integer.parseInt(FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("toId"));
        
        wallpostModel.setMessage(message);
        wallpostModel.setFromId(fromId);
        wallpostModel.setToId(toId);
        
        try {
            checkIfPostSuccess=gson.fromJson(wallPostRestClient.sendPost(URLEncoder.encode(gson.toJson(wallpostModel),"UTF-8")), String.class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WallpostBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(checkIfPostSuccess.equalsIgnoreCase("true")){
            return "profile";
        }else{
            return "welcome";
        }
        
        //System.out.println("TESYSYSYSYFUOAJDFPAJSPDOJASPOJD:     Message: "+message+" fromID: "+fromId+" toId: "+toId);
    }
    
}
