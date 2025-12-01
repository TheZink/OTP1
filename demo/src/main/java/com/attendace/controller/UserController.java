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

public class UserController {

    private DefaultHandler handler;
    private LoginUtils login;
    private Request request;
    private Map<String, Object> data;
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String STUDENTID = "user_student_id";
    private static final String DEGREE = "degree";

    public UserController() {
        this.handler = new DefaultHandler();
        this.login = new LoginUtils();
    }

    public String loginUser(String username, String password) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        data.put(PASSWORD, password);
        return login.login(data);
    }
    public boolean createUser(int studentId, String name, String password, String userDegree) {
        data = new HashMap<>();
        data.put(USERNAME, name);
        data.put(STUDENTID, studentId);
        data.put(DEGREE, userDegree);
        data.put(PASSWORD, password);
        request = new Request(RequestDao.USERS, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }


    public boolean updateUser(String name, int id, String degree, String password) {
        data = new HashMap<>();
        data.put("name", name);
        data.put("id", id);
        data.put(DEGREE, degree);
        data.put(PASSWORD, password);

        request = new Request(RequestDao.USERS, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }



    public List<String> getUser(String username) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        request = new Request(RequestDao.USERS, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }


    public List<String> getAllUsers() {
        data = new HashMap<>();
        request = new Request(RequestDao.USERS, RequestType.GETALLDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    public boolean removeUser(int value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.USERS, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }
}
