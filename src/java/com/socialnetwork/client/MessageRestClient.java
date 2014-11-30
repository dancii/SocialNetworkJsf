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
 * Jersey REST client generated for REST resource:MessageResource [Message]<br>
 * USAGE:
 * <pre>
 *        MessageRestClient client = new MessageRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author dancii
 */
public class MessageRestClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SocialNetworkJsf/webresources";

    public MessageRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Message");
    }

    public String getAllMessages(String toUsername) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("messageAll/{0}", new Object[]{toUsername})).request().post(null, String.class);
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String getMessageCount(String toUsername) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("messageCount/{0}", new Object[]{toUsername})).request().post(null, String.class);
    }

    public String sendMessage(String messageObj) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("sendMessage/{0}", new Object[]{messageObj})).request().post(null, String.class);
    }

    public String getJson() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
