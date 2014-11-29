package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.Wallpost;
import com.socialnetwork.model.WallpostModel;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class WallpostHandler {

    public static String sendPost(String post){
        Gson gson = new Gson();
        WallpostModel wallpostModel = new WallpostModel();
        
        wallpostModel=gson.fromJson(post, WallpostModel.class);
        
        String registerSuccess = "false";
        Wallpost wallPost = null;
        EntityTransaction trans = null;
        EntityManagerFactory emf=null;
        EntityManager em=null;
        
        try{
            emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
            em = emf.createEntityManager();

            wallPost=returnPost(wallpostModel);
            trans=em.getTransaction();
            trans.begin();
            em.persist(wallPost);

            em.flush();
            trans.commit();
            registerSuccess="true";
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
        
        return gson.toJson(registerSuccess);
    }
    
    public static Wallpost returnPost(WallpostModel wallPostModel){
        Wallpost wallPostDao = new Wallpost();
        
        wallPostDao.setMessage(wallPostModel.getMessage());
        wallPostDao.setFromid(wallPostModel.getFromId());
        wallPostDao.setToid(wallPostModel.getToId());
        wallPostDao.setDate(new Date());
        return wallPostDao;
    }
    
}
