package com.attendace.Controller;

import com.attendace.Model.CourseModel;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseController {
    //private CourseModel courseModel;
    private DefaultHandler handler;
    private Request request;

    private Map<String, Object> data;

    public CourseController() {
        this.handler = new DefaultHandler();
    }


    public boolean createCourse(String courseName, String courseTopic, String courseDesc, boolean attendanceAvailable, String attendanceKey, int minAttendance, int maxAttendance, boolean courseActive) {
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
        return (boolean) handler.handle(request);

    }

    public boolean updateCourse(int value, String label, String setValue) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        data.put("setValue", setValue);
        request = new Request(RequestDao.COURSE, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }

    public boolean removeCourse(int value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }


    public ArrayList<ArrayList<Object>> getAllCourses() {
        data = new HashMap<>();
        request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, data);
        return (ArrayList<ArrayList<Object>>) handler.handle(request);
    }


    public ArrayList<String> getCourse(String value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.COURSE, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }
    public ArrayList<String> getCourseById(int course_id) {
        data = new HashMap<>();
        data.put("course_id", course_id);

        request = new Request(RequestDao.COURSE, RequestType.GETDATABYID, data);
        return (ArrayList<String>) handler.handle(request);
    }

    public ArrayList<Integer> getStaffsCourses(int staffId) {
        data = new HashMap<>();
        data.put("id", staffId);
        request = new Request(RequestDao.STAFF_COURSE_JOIN, RequestType.GETDATA, data);
        return (ArrayList<Integer>) handler.handle(request);
    }
}
