package com.attendace.Model;

public class UserModel {


    private int studentId;
    private String name;
    private String password;
    private String userDegree;


    public UserModel(int studentId, String name, String password, String userDegree) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.userDegree = userDegree;

    }
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getUserDegree() {
        return userDegree;
    }
}
