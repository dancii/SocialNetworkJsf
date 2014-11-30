/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialnetwork.webservice;

import com.socialnetwork.handler.MessageHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author dancii
 */
@Path("Message")
public class MessageResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MessageResource
     */
    public MessageResource() {
    }

    /**
     * Retrieves representation of an instance of com.socialnetwork.webservice.MessageResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @POST
    @Path("messageCount/{toUsername}")
    @Produces("application/json")
    public String getMessageCount(@PathParam("toUsername")String toUsername){
        return MessageHandler.getMessageCount(toUsername);
    }
    
    @POST
    @Path("messageAll/{toUsername}")
    @Produces("application/json")
    public String getAllMessages(@PathParam("toUsername")String toUsername){
        return MessageHandler.getAllMessages(toUsername);
    }
    
    @POST
    @Path("sendMessage/{messageObj}")
    @Produces("application/json")
    public String sendMessage(@PathParam("messageObj")String messageObj){
        return MessageHandler.sendMessage(messageObj);
    }
    
    @POST
    @Path("readAMessage/{messageObj}")
    @Produces("application/json")
    public String readAMessage(@PathParam("messageObj")String messageObj){
        return MessageHandler.readAMessage(messageObj);
    }
    
    /**
     * PUT method for updating or creating an instance of MessageResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
