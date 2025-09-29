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
    
    // This methods check, if username exist in the database. 
    public Map<String, Object> checkUser(Map<String, Object> object){
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, object);
        Object result = handler.handle(request);
        boolean exists = (result instanceof ArrayList) && !((ArrayList<?>) result).isEmpty();

       // If name exist, add randomly selected 2-digit suffix to the name
        if (exists) {
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
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, object);
        Object result = handler.handle(request);
        boolean exists = (result instanceof ArrayList) && !((ArrayList<?>) result).isEmpty();

       // If name exist, add randomly selected 2-digit suffix to the name
        if (exists) {
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
