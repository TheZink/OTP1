package com.attendace.model;

public class UserModel {


    private int studentId;
    private String name;
    private String userDegree;


    public UserModel(int studentId, String name, String userDegree) {
        this.studentId = studentId;
        this.name = name;
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
