package com.attendace.controller;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceController {

    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;


    public AttendanceController() {
        this.handler = new DefaultHandler();
    }

    public boolean addAttendance(int courseId, int userId, int staffId, boolean attendanceStatus, int currentAttendance) {
        data = new HashMap<>();
        data.put("course_id", courseId);
        data.put("user_id", userId);
        data.put("staff_id", staffId);
        data.put("atten_status", attendanceStatus);
        data.put("atten_current", currentAttendance);
        request = new Request(RequestDao.ATTENDANCE, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }
    public List<String> getAttendance() {
        data = new HashMap<>();
        request = new Request(RequestDao.ATTENDANCE, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }
    public List<String> getAllAttendance() {
        data = new HashMap<>();
        request = new Request(RequestDao.ATTENDANCE, RequestType.GETALLDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }
    public boolean updateAttendance(int value, String label, String setValue) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        data.put("setValue", setValue);
        request = new Request(RequestDao.ATTENDANCE, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }
    public boolean removeAttendance(int value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.ATTENDANCE, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }
}
