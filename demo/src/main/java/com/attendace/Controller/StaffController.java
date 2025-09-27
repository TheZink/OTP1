package com.attendace.Controller;


import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.HashMap;
import java.util.Map;

public class StaffController {
    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;

    //ADD FXML TAGS HERE

    public StaffController() {
        this.handler = new DefaultHandler();
    }

    //ActionEvent event parameter later, strings used for testing only
    public void createStaff(String username, String role, boolean isAdmin, String password) {
        data = new HashMap<>();
        data.put("username", username);
        data.put("role", role);
        data.put("isAdmin", isAdmin);
        data.put("password", password);

        request = new Request(RequestDao.STAFF, RequestType.SETDATA, data);
        boolean createdStaff = (boolean)handler.handle(request);

        //returns false even though it creates staff correctly?
        if(createdStaff) {
            System.out.println("Created staff succesfully");
        } else {
            System.out.println("Failed to create staff");
        }
    }
}