package com.attendace.Controller;

import com.attendace.Model.CourseModel;

public class CourseController {
    private CourseModel courseModel;


    //Add dao later
    public CourseController() {
    }
    public void createCourse(String courseName, String courseTopic, String courseDesc, int minAttendance, int maxAttendance) {
        courseModel = new CourseModel(
                courseName,
                courseTopic,
                courseDesc,
                minAttendance,
                maxAttendance
                );
        System.out.println("You just created a course");

    }
    public CourseModel getCourse() {
        return courseModel;
    }
}
