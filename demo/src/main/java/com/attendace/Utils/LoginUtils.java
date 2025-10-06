package com.attendace.Utils;

import java.util.Map;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class LoginUtils {

        Handler handler = new DefaultHandler();

        public String login(Map<String, Object> object){
            Request request1 = new Request(RequestDao.USERS, RequestType.SIGNIN, object);
            boolean singin1 = (boolean) handler.handle(request1);

            // If Users-handler return false, try Staff-handler
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
