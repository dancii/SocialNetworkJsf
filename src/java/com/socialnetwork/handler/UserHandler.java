package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.User;
import com.socialnetwork.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserHandler {
    
    public static String loginUser(String username, String pass){
        Gson gson = new Gson();
        System.out.println("CHEEEEEEEECKIIIIIIIIIINGGGGGG!!!!!!!!      :"+username+"PASSSS"+pass);
        User userDao=null;
        UserModel userModel=null;
        EntityManagerFactory emf=null;
        EntityManager em=null;
        
        try{
            emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
            em = emf.createEntityManager();

            Query query = em.createNamedQuery("User.logIn");
            
            query.setParameter("username",username);
            query.setParameter("password",pass);
            userDao = (User) query.getSingleResult();
            
            if(userDao!=null){
                userModel = new UserModel();
                userModel.setId(userDao.getId());
                userModel.setUsername(userDao.getUsername());
                userModel.setFirstname(userDao.getFirstname());
                userModel.setLastname(userDao.getLastname());
                userModel.setEmail(userDao.getEmail());
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
        
        return gson.toJson(userModel);
    }
    
    public static String registerUser(String user){
        UserModel userModel = new UserModel();
        Gson gson = new Gson();
        String registerSuccess = "false";
        User userDao=null;
        EntityTransaction trans = null;
        EntityManagerFactory emf=null;
        EntityManager em=null;
        
        userModel = gson.fromJson(user, UserModel.class);

        try{
            emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
            em = emf.createEntityManager();

            userDao=returnUser(userModel);
            trans=em.getTransaction();
            trans.begin();
            em.persist(userDao);

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

        return registerSuccess;
    }
    
    public static String searchUserByUsername(String username){
        String searchUsername ="";
        Gson gson = new Gson();
        List<User> userDao=null;
        List<UserModel> userList=new ArrayList<UserModel>();
        UserModel userModel=null;
        EntityManagerFactory emf=null;
        EntityManager em=null;
        
        searchUsername = gson.fromJson(username, String.class);
        
        try{
            emf = Persistence.createEntityManagerFactory("SocialNetworkJsfPU");
            em = emf.createEntityManager();

            Query query = em.createNamedQuery("User.searchByUsernameLike");
            
            query.setParameter("username","%"+searchUsername+"%");
            userDao = query.getResultList();
            
            if(userDao!=null){
                for(int i=0;i<userDao.size();i++){
                    userModel = new UserModel();
                    userModel.setId(userDao.get(i).getId());
                    userModel.setUsername(userDao.get(i).getUsername());
                    userModel.setFirstname(userDao.get(i).getFirstname());
                    userModel.setLastname(userDao.get(i).getLastname());
                    userModel.setEmail(userDao.get(i).getLastname());
                    userList.add(userModel);
                }
            } else {
                userModel = new UserModel();
                userModel.setUsername("");
                userList.add(userModel);
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
        
        return gson.toJson(userList);       
    }
    
    public static User returnUser(UserModel user){
        User userDao = new User();
        userDao.setId(user.getId());
        userDao.setUsername(user.getUsername());
        userDao.setPassword(user.getPassword());
        userDao.setFirstname(user.getFirstname());
        userDao.setLastname(user.getLastname());
        userDao.setEmail(user.getEmail());
        return userDao;
    }
}
