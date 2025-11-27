package com.attendace.controller;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.view.MainInterfaceController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseController {
    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;
    private static final String CONSTLABEL = "label";
    private static final String CONSTVALUE = "value";

    private MainInterfaceController mainInterfaceController = new MainInterfaceController();

    public CourseController() {
        this.handler = new DefaultHandler();
    }


    public boolean createCourse(String courseName, String courseTopic, String courseDesc, boolean attendanceAvailable, String attendanceKey, int minAttendance, int maxAttendance, boolean courseActive) {


            System.out.println(mainInterfaceController.getAdminStatus());

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
        data.put(CONSTVALUE, value);
        data.put(CONSTLABEL, label);
        data.put("setValue", setValue);
        request = new Request(RequestDao.COURSE, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }

    public boolean removeCourse(int value, String label) {
        data = new HashMap<>();
        data.put(CONSTVALUE, value);
        data.put(CONSTLABEL, label);
        request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }


    public List<ArrayList<Object>> getAllCourses() {
        data = new HashMap<>();
        request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, data);
        return (ArrayList<ArrayList<Object>>) handler.handle(request);
    }


    public List<String> getCourse(String value, String label) {
        data = new HashMap<>();
        data.put(CONSTVALUE, value);
        data.put(CONSTLABEL, label);
        request = new Request(RequestDao.COURSE, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }
    public List<String> getCourseById(int courseid) {
        data = new HashMap<>();
        data.put("course_id", courseid);

        request = new Request(RequestDao.COURSE, RequestType.GETDATABYID, data);
        return (ArrayList<String>) handler.handle(request);
    }

    public List<Integer> getStaffsCourses(int staffId) {
        data = new HashMap<>();
        data.put("id", staffId);
        request = new Request(RequestDao.STAFF_COURSE_JOIN, RequestType.GETDATA, data);
        return (ArrayList<Integer>) handler.handle(request);
    }
}
