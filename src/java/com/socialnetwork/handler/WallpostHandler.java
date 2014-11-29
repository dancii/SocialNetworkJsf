package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.Wallpost;
import com.socialnetwork.model.WallpostModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
    
    public static String getAllWallpost(String toUsernameStr){
        String toUsername="";
        Gson gson = new Gson();
        List<WallpostModel> wallPostList = new ArrayList<WallpostModel>();
        List<Wallpost> wallPostDaoList = null;
        WallpostModel wallPostModel=null;
        EntityManagerFactory emf=null;
        EntityManager em=null;
        
        toUsername=gson.fromJson(toUsernameStr, String.class);
        
        try{
            emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
            em = emf.createEntityManager();
            
            Query query = em.createNamedQuery("Wallpost.findByToUsername");
            query.setParameter("toUsername", toUsername);
            
            wallPostDaoList = query.getResultList();
            
            if(wallPostDaoList!=null){
                for(int i=0;i<wallPostDaoList.size();i++){
                    wallPostModel = new WallpostModel();
                    wallPostModel.setId(wallPostDaoList.get(i).getId());
                    wallPostModel.setMessage(wallPostDaoList.get(i).getMessage());
                    wallPostModel.setToUsername(wallPostDaoList.get(i).getToUsername());
                    wallPostModel.setFromUsername(wallPostDaoList.get(i).getFromUsername());
                    wallPostModel.setDatetime(wallPostDaoList.get(i).getDate());
                    wallPostList.add(wallPostModel);
                    System.out.println("CHECKING ERROR !!!!!!!!!!!! MESSAGE: "+wallPostDaoList.get(i).getMessage());
                }
            }else{
                wallPostModel=new WallpostModel();
                wallPostModel.setMessage("");
                wallPostList.add(wallPostModel);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em!=null){
                em.close();
            }
            if(emf!=null){
                emf.close();
            }
        }
        return gson.toJson(wallPostList);
    }
    
    public static Wallpost returnPost(WallpostModel wallPostModel){
        Wallpost wallPostDao = new Wallpost();
        
        wallPostDao.setMessage(wallPostModel.getMessage());
        wallPostDao.setFromUsername(wallPostModel.getFromUsername());
        wallPostDao.setToUsername(wallPostModel.getToUsername());
        wallPostDao.setDate(new Date());
        return wallPostDao;
    }
    
}
