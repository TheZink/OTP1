package com.attendace.Controller;

import com.attendace.Model.CourseModel;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.HashMap;
import java.util.Map;

public class CourseController {
    private CourseModel courseModel;
    private DefaultHandler handler;
    private Request request;

    private Map<String, Object> data;

    //FOR TEST, ADD FXML TAGS LATER
    private String courseName;
    private String courseTopic;
    private String courseDesc;
    private int minAttendance;


    //Add dao later
    public CourseController() {
        this.handler = new DefaultHandler();
    }

    //ActionEvent event parameter later, strings used for testing only
    public void createCourse(String courseName, String courseTopic, String courseDesc, boolean attendanceAvailable, String attendanceKey, int minAttendance, int maxAttendance, boolean courseActive) {
        data = new HashMap<>();
        data.put("course_name", courseName);
        data.put("course_topic", courseTopic);
        data.put("course_desc", courseDesc);
        data.put("attendance_avaible", attendanceAvailable);
        data.put("attendance_key", attendanceKey);
        data.put("min_attendance", minAttendance);
        data.put("max_attendance", maxAttendance);
        data.put("course_active", courseActive);

        request = new Request(RequestDao.COURSE, RequestType.SETDATA, data);

        boolean createdCourse = (boolean)handler.handle(request);

        if(createdCourse) {
            System.out.println("Created course");
        } else {
            System.out.println("Failed to create course");
        }
    }

    public CourseModel getCourse() {
        return courseModel;
    }
}
