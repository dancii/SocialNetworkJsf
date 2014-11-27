/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialnetwork.webservice;

import com.socialnetwork.handler.UserHandler;
import com.socialnetwork.model.UserModel;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of com.socialnetwork.webservice.UserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @POST
    @Path("userlogin/{user}/{pass}")
    @Produces("application/json")
    public String loginUser(@PathParam("user")String username,@PathParam("pass")String pass){
        
        return UserHandler.loginUser(username,pass);
    }
    
    @POST
    @Path("userregister/{user}")
    @Produces("application/json")
    public String registerUser(@PathParam("user")String user){
        
        return UserHandler.registerUser(user);
    }
    
    @POST
    @Path("usersearch/{username}")
    @Produces("application/json")
    public String searchUser(@PathParam("username")String username){
        return UserHandler.searchUserByUsername(username);
    }
    
    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
