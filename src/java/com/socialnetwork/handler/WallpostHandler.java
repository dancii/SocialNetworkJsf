package com.socialnetwork.handler;

import com.google.gson.Gson;
import com.socialnetwork.dao.Wallpost;
import com.socialnetwork.model.WallpostModel;

public class WallpostHandler {

    public static String sendPost(String post){
        Gson gson = new Gson();
        WallpostModel wallpostModel = new WallpostModel();
        Wallpost wallPostDao = new Wallpost();
        
        wallpostModel=gson.fromJson(post, WallpostModel.class);
        
        return gson.toJson(wallPostDao.postOnWall(wallpostModel));
    }
    
}
