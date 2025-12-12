package com.attendace.controller;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing attendance-related operations.
 * Provides methods to add, update, remove, and retrieve attendance records.
 */
public class AttendanceController {

    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;
    private static final String VALUE = "value";
    private static final String LABEL = "label";

    /**
     * Constructs a new AttendanceController and initializes the handler.
     */
    public AttendanceController() {
        this.handler = new DefaultHandler();
    }

    /**
     * Adds a new attendance record with the specified details.
     *
     * @param courseId the ID of the course
     * @param userId the ID of the user
     * @param staffId the ID of the staff member
     * @param attendanceStatus the attendance status (present or not)
     * @param currentAttendance the current attendance count
     * @return true if the attendance was added successfully, false otherwise
     */
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

    /**
     * Retrieves attendance records.
     *
     * @return a list of attendance data fields
     */
    public List<String> getAttendance() {
        data = new HashMap<>();
        request = new Request(RequestDao.ATTENDANCE, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Retrieves all attendance records.
     *
     * @return a list of all attendance data fields
     */
    public List<String> getAllAttendance() {
        data = new HashMap<>();
        request = new Request(RequestDao.ATTENDANCE, RequestType.GETALLDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Updates an attendance record with the specified value, label, and new value.
     *
     * @param value the value to identify the attendance record
     * @param label the label (column) to identify the attendance record
     * @param setValue the new value to set
     * @return true if the attendance was updated successfully, false otherwise
     */
    public boolean updateAttendance(int value, String label, String setValue) {
        data = new HashMap<>();
        data.put(VALUE, value);
        data.put(LABEL, label);
        data.put("setValue", setValue);
        request = new Request(RequestDao.ATTENDANCE, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Removes an attendance record based on the specified value and label.
     *
     * @param value the value to identify the attendance record
     * @param label the label (column) to identify the attendance record
     * @return true if the attendance was removed successfully, false otherwise
     */
    public boolean removeAttendance(int value, String label) {
        data = new HashMap<>();
        data.put(VALUE, value);
        data.put(LABEL, label);
        request = new Request(RequestDao.ATTENDANCE, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }
}