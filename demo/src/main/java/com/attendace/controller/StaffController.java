package com.attendace.controller;


import com.attendace.utils.LoginUtils;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffController {
    private DefaultHandler handler;
    private LoginUtils login;
    private Request request;
    private Map<String, Object> data;
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    public StaffController() {
        this.handler = new DefaultHandler();
        this.login = new LoginUtils();
    }


    public String loginStaff(String username, String password) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        data.put(PASSWORD, password);
        request = new Request(RequestDao.STAFF, RequestType.SIGNIN, data);
        return login.login(data);
    }


    public boolean createStaff(String username, String role, boolean isAdmin, String password) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        data.put("role", role);
        data.put("isAdmin", isAdmin);
        data.put(PASSWORD, password);
        request = new Request(RequestDao.STAFF, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }

    public List<String> getStaff(String username) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        request = new Request(RequestDao.STAFF, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }
    public boolean updateStaff(String name, int id, String role, String password) {
        data = new HashMap<>();
        data.put("name", name);
        data.put("id", id);
        data.put("role", role);
        data.put(PASSWORD, password);

        request = new Request(RequestDao.STAFF, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }

    public List<String> getAllStaff() {
        data = new HashMap<>();
        request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }
    public boolean removeStaff(int value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.STAFF, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }
}