package com.attendace.utils;

import java.util.Map;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

/**
 * Utility class for handling user and staff login operations.
 * Attempts to authenticate a user as either a regular user or staff member.
 */
public class LoginUtils {

    /** Handler for processing DAO requests. */
    Handler handler = new DefaultHandler();

    /**
     * Attempts to log in a user or staff member with the provided credentials.
     * First tries to authenticate as a user; if unsuccessful, tries as staff.
     *
     * @param object a map containing login credentials (e.g., username and password)
     * @return "User" if authenticated as a user, "Staff" if authenticated as staff, or "false" if authentication fails
     */
    public String login(Map<String, Object> object) {
        Request request1 = new Request(RequestDao.USERS, RequestType.SIGNIN, object);
        boolean singin1 = (boolean) handler.handle(request1);

        // If Users-handler returns false, try Staff-handler
        if (!singin1) {
            Request request2 = new Request(RequestDao.STAFF, RequestType.SIGNIN, object);
            boolean singin2 = (boolean) handler.handle(request2);

            if (singin2) {
                return "Staff";
            } else {
                return "false";
            }
        } else {
            return "User";
        }
    }
}
