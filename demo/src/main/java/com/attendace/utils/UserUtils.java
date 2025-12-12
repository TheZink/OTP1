package com.attendace.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

/**
 * Utility class for user-related operations such as checking username existence
 * and generating unique usernames for students and staff.
 */
public class UserUtils {

    /** Handler for DAO operations. */
    Handler handler = new DefaultHandler();

    /** Random number generator for username suffixes. */
    Random random = new Random();

    /** Key for the username field in the object map. */
    private static final String USERNAME_STRING = "username";

    /**
     * Checks if the given student username exists in the database.
     * If it exists, appends a random 2-digit suffix to make it unique.
     *
     * @param object a map containing at least the "username" key
     * @return the original or modified map with a unique username
     */
    public Map<String, Object> checkUser(Map<String, Object> object) {
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, object);
        ArrayList<String> studdentresult = (ArrayList<String>) handler.handle(request);

        // If the name exists, add a randomly selected 2-digit suffix to the name.
        if (studdentresult != null && !studdentresult.isEmpty()) {
            String username = (String) object.get(USERNAME_STRING);
            String modify = username + (random.nextInt(98) + 1);

            object.put(USERNAME_STRING, modify);
            return object;
        } else {
            return object;
        }
    }

    /**
     * Checks if the given staff username exists in the database.
     * If it exists, appends a random 2-digit suffix to make it unique.
     *
     * @param object a map containing at least the "username" key
     * @return the original or modified map with a unique username
     */
    public Map<String, Object> checkStaff(Map<String, Object> object) {
        Request request = new Request(RequestDao.STAFF, RequestType.GETDATA, object);
        ArrayList<String> staffresult = (ArrayList<String>) handler.handle(request);

        // If the name exists, add a randomly selected 2-digit suffix to the name.
        if (staffresult != null && !staffresult.isEmpty()) {
            String username = (String) object.get(USERNAME_STRING);
            String modify = username + (random.nextInt(98) + 1);

            object.put(USERNAME_STRING, modify);
            return object;
        } else {
            return object;
        }
    }
}

