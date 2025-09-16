package com.attendace.Controller;

import com.attendace.Model.UserModel;

public class UserController {
    private UserModel user;

    //dao in the constructor later
    public UserController() {

    }

    public void createUser(int studentId, String name, String password, String userDegree) {
        user = new UserModel(studentId, name, password, userDegree);
        System.out.println("Account created!");
    }
    public UserModel getUser() {
        return user;
    }
}
