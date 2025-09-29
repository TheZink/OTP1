package com.attendace.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class UserUtils {

    Handler handler = new DefaultHandler();
    
    // This methods check, if username exist in the database. 
    public Map<String, Object> checkUser(Map<String, Object> object){
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, object);
        boolean existing = (boolean) handler.handle(request);

       // If name exist, add randomly selected 2-digit suffix to the name
        if (existing) {
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

    // This methods check, if username exist in the database. 
    public Map<String, Object> checkStaff(Map<String, Object> object){
        Request request = new Request(RequestDao.STAFF, RequestType.GETDATA, object);
        boolean existing = (boolean) handler.handle(request);

       // If name exist, add randomly selected 2-digit suffix to the name
        if (existing) {
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
