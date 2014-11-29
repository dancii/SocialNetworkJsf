/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialnetwork.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:WallPostResource
 * [WallPost]<br>
 * USAGE:
 * <pre>
 *        WallpostRestClient client = new WallpostRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author dancii
 */
public class WallpostRestClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SocialNetworkJsf/webresources";

    public WallpostRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("WallPost");
    }

    public String sendPost(String post) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("sendPost/{0}", new Object[]{post})).request().post(null, String.class);
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String getAllwallPostToUser(String toId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("getAllPost/{0}", new Object[]{toId})).request().post(null, String.class);
    }

    public String getJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
