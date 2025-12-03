package com.attendace.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {


    private int id;
    private int studentId;
    private String name;
    private String userDegree;
    private ArrayList<CourseModel> courses;
    private String lang;


    public UserModel(int id, int studentId, String name, String userDegree, String lang) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.userDegree = userDegree;
        this.courses = new ArrayList<>();
        this.lang = lang;

    }
    public int getId() {
        return id;
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
    public void addCourse(CourseModel course) {
        courses.add(course);
    }
    public List<CourseModel> getUserCourses() {
        return courses;
    }
}

