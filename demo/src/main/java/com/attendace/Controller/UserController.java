package com.attendace.Controller;

import com.attendace.Model.UserModel;
import com.attendace.Utils.LoginUtils;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.Dao_user;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    private UserModel user;
    private DefaultHandler handler;
    private LoginUtils login;
    private Request request;
    private Map<String, Object> data;

    public UserController() {
        this.handler = new DefaultHandler();
        this.login = new LoginUtils();
    }

    public boolean loginUser(String username, String password) {
        data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        return (boolean) login.login(data);
    }
    public boolean createUser(int studentId, String name, String password, String userDegree) {
        data = new HashMap<>();
        data.put("student_id", studentId);
        data.put("username", name);
        data.put("password", password);
        data.put("degree", userDegree);
        request = new Request(RequestDao.USERS, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }


    public boolean updateUser(String name, int id, String degree, String password) {
        data = new HashMap<>();
        data.put("name", name);
        data.put("id", id);
        data.put("degree", degree);
        data.put("password", password);

        request = new Request(RequestDao.USERS, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }



    public ArrayList<String> getUser(String username) {
        data = new HashMap<>();
        data.put("username", username);
        request = new Request(RequestDao.USERS, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }


    public ArrayList<String> getAllUsers() {
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
