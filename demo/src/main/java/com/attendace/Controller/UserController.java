package com.attendace.Controller;

import com.attendace.Model.UserModel;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.Dao_user;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    private UserModel user;
    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;


    //TEST ONLY REPLACE WITH REAL FXML TAGS FROM SCENE BUILDER
    private String username;
    private String password;


    public UserController() {
        this.handler = new DefaultHandler();
    }


    //ActionEvent event parameter later, strings used for testing only
    public boolean loginUser(String username, String password) {
        data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        request = new Request(RequestDao.USERS, RequestType.SIGNIN, data);
        boolean login = (boolean) handler.handle(request);

        //CHANGE STAGE HERE?
        if(login) {
            System.out.println("Logging in succeeded");
            return true;
        } else {
            System.out.println("Logging in failed.");
            return false;
        }
    }

    //ActionEvent event parameter later, strings used for testing only
    public void createUser(int studentId, String name, String password, String userDegree) {
        data = new HashMap<>();
        data.put("student_id", studentId);
        data.put("username", name);
        data.put("password", password);
        data.put("degree", userDegree);

        request = new Request(RequestDao.USERS, RequestType.SETDATA, data);
        boolean register = (boolean) handler.handle(request);

        //CHANGE STAGE HERE?
        if(register) {
            System.out.println("Register succeeded");
        } else {
            System.out.println("Register failed.");
        }
    }

    public UserModel getUser() {
        return user;
    }
}
