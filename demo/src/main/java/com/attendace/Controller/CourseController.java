package com.attendace.Controller;

import com.attendace.Model.CourseModel;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseController {
    private CourseModel courseModel;
    private DefaultHandler handler;
    private Request request;

    private Map<String, Object> data;
    private ArrayList<String> list;

    //FOR TEST, ADD FXML TAGS LATER
    private String courseName;
    private String courseTopic;
    private String courseDesc;
    private int minAttendance;


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
    //TEST
    public void updateCourse(int value, String label, String setValue) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        data.put("setValue", setValue);

        request = new Request(RequestDao.COURSE, RequestType.UPDATEDATA, data);
        boolean updatedCourse = (boolean)handler.handle(request);

        if(updatedCourse){
            System.out.println("Updated course.");
        } else {
            System.out.println("Failed to update course");
        }
    }
    //TEST
    public void removeCourse(int value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, data);

        boolean deletedCourse = (boolean)handler.handle(request);

        if(deletedCourse) {
            System.out.println("Deleted course");
        } else {
            System.out.println("Failed to delete course");
        }

    }

    public ArrayList<String> getAllCourses() {
        data = new HashMap<>();
        request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    //TEST
    public ArrayList<String> getCourse(String value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);

        request = new Request(RequestDao.COURSE, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }
}
