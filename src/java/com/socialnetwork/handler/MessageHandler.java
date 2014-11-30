package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.Message;
import com.socialnetwork.model.MessageModel;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MessageHandler {

    public static String getMessageCount(String toUsername){
        Gson gson = new Gson();
        Message messageDao = new Message();
        EntityManager em = null;
        EntityManagerFactory emf = null;
        String fromGson="";
        Long countMessages=0L;
        
        fromGson=gson.fromJson(toUsername, String.class);
        
        emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
        em = emf.createEntityManager();
        
        Query query = em.createNamedQuery("Message.findByIsRead");
        query.setParameter("isRead", false);
        query.setParameter("toUsername", fromGson);
        countMessages=(Long)query.getSingleResult();
        
        return gson.toJson(countMessages);
    }
    
    public static String getAllMessages(String toUsername){
        Gson gson = new Gson();
        List<Message> messageDao = null;
        EntityManager em = null;
        EntityManagerFactory emf = null;
        String fromGson="";
        List<MessageModel> messageModelList=null;
        MessageModel messageModel = null;
        
        fromGson=gson.fromJson(toUsername, String.class);
        
        emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
        em = emf.createEntityManager();
        
        Query query = em.createNamedQuery("Message.findByToUsername");
        query.setParameter("toUsername", fromGson);
        messageDao=query.getResultList();
        
        if(!messageDao.isEmpty()){
            for(int i=0;i<messageDao.size();i++){
                messageModel = new MessageModel(messageDao.get(i).getId(),messageDao.get(i).getMessage(),messageDao.get(i).getFromUsername(),messageDao.get(i).getToUsername(),messageDao.get(i).getIsRead(),messageDao.get(i).getDatetime());
                messageModelList.add(messageModel);
            }
        }else{
            messageModel = new MessageModel(-1,"No messages found","","",false,new Date());
            messageModelList.add(messageModel);
        }
        
        return gson.toJson(messageModelList);
    }
    
    public static String sendMessage(String messageObj){
        Gson gson = new Gson();
        EntityManager em = null;
        EntityManagerFactory emf = null;
        EntityTransaction trans = null;
        Message messageDao = new Message();
        MessageModel messageModel = new MessageModel();
        String sendMessageSuccess = "false";
        
        messageModel=gson.fromJson(messageObj, MessageModel.class);
        
        try{
            emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
            em = emf.createEntityManager();

            messageDao=returnMessage(messageModel);
            trans=em.getTransaction();
            trans.begin();
            em.persist(messageDao);

            em.flush();
            trans.commit();
            sendMessageSuccess="true";
        }catch(Exception e){
            if(trans!=null){
                trans.rollback();
            }
        }finally{
            if(em!=null){
                em.close();
            }
            if(emf!=null){
                emf.close();
            }
        }
        return sendMessageSuccess;
    }
    
    public static Message returnMessage(MessageModel messageModel){
        Message messageDao=new Message();
        messageDao.setMessage(messageModel.getMessage());
        messageDao.setToUsername(messageModel.getToUsername());
        messageDao.setFromUsername(messageModel.getFromUsername());
        messageDao.setIsRead(false);
        messageDao.setDatetime(new Date());
        return messageDao;
    }
    
}
