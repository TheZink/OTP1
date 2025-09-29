package com.attendace.Utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class UserUtils {

    Handler handler = new DefaultHandler();
    
    // This method checks if the username exists in the database. 
    public Map<String, Object> checkUser(Map<String, Object> object){
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, object);
        ArrayList<String> result = (ArrayList<String>) handler.handle(request);

       // If the name exists, add a randomly selected 2-digit suffix to the name.
        if (result != null && !result.isEmpty()) {
            Random random = new Random();
            String username = (String) object.get("username");
            String modify = username + random.nextInt(1, 99);

            object.put("username", modify);
            return object;
        } 
        
        else {
            return object;
        }
    }

    // This method checks if the username exists in the database. 
    public Map<String, Object> checkStaff(Map<String, Object> object){
        Request request = new Request(RequestDao.STAFF, RequestType.GETDATA, object);
        ArrayList<String> result = (ArrayList<String>) handler.handle(request);

       // If the name exists, add a randomly selected 2-digit suffix to the name.
        if (result != null && !result.isEmpty()) {
            Random random = new Random();
            String username = (String) object.get("username");
            String modify = username + random.nextInt(1, 99);

            object.put("username", modify);
            return object;
        } 
        
        else {
            return object;
        }
    }
}
