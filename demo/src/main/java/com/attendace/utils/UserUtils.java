package com.attendace.utils;

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
    Random random = new Random();

    private static final String USERNAME_STRING = "username";
    
    // This method checks if the username exists in the database. 
    public Map<String, Object> checkUser(Map<String, Object> object){
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, object);
        ArrayList<String> studdentresult = (ArrayList<String>) handler.handle(request);

       // If the name exists, add a randomly selected 2-digit suffix to the name.
        if (studdentresult != null && !studdentresult.isEmpty()) {
            String username = (String) object.get(USERNAME_STRING);
            String modify = username + random.nextInt(98) + 1;

            object.put(USERNAME_STRING, modify);
            return object;
        } 
        
        else {
            return object;
        }
    }

    // This method checks if the username exists in the database. 
    public Map<String, Object> checkStaff(Map<String, Object> object){
        Request request = new Request(RequestDao.STAFF, RequestType.GETDATA, object);
        ArrayList<String> staffresult = (ArrayList<String>) handler.handle(request);

       // If the name exists, add a randomly selected 2-digit suffix to the name.
        if (staffresult != null && !staffresult.isEmpty()) {
            String username = (String) object.get(USERNAME_STRING);
            String modify = username + random.nextInt(98) + 1;

            object.put(USERNAME_STRING, modify);
            return object;
        } 
        
        else {
            return object;
        }
    }
}
