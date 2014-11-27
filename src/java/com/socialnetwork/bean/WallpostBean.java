package com.socialnetwork.bean;

import java.util.Date;
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
    
    public void postOnWall(){
        fromId = Integer.parseInt(FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("fromId"));
        toId=Integer.parseInt(FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("toId"));
        
        System.out.println("TESYSYSYSYFUOAJDFPAJSPDOJASPOJD:     Message: "+message+" fromID: "+fromId+" toId: "+toId);
    }
    
}
