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
 * Controller for managing course-related operations.
 * Provides methods to create, update, remove, and retrieve courses.
 */
public class CourseController {
    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;
    private static final String CONSTLABEL = "label";
    private static final String CONSTVALUE = "value";

    /**
     * Constructs a new CourseController and initializes the handler.
     */
    public CourseController() {
        this.handler = new DefaultHandler();
    }

    /**
     * Creates a new course with the specified details.
     *
     * @param courseName the name of the course
     * @param courseTopic the topic of the course
     * @param courseDesc the description of the course
     * @param attendanceAvailable whether attendance is available for the course
     * @param attendanceKey the attendance key for the course
     * @param minAttendance the minimum attendance required
     * @param maxAttendance the maximum attendance allowed
     * @param courseActive whether the course is active
     * @return true if the course was created successfully, false otherwise
     */
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

    /**
     * Updates a course with the specified value, label, and new value.
     *
     * @param value the value to identify the course
     * @param label the label (column) to identify the course
     * @param setValue the new value to set
     * @return true if the course was updated successfully, false otherwise
     */
    public boolean updateCourse(int value, String label, String setValue) {
        data = new HashMap<>();
        data.put(CONSTVALUE, value);
        data.put(CONSTLABEL, label);
        data.put("setValue", setValue);
        request = new Request(RequestDao.COURSE, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Removes a course based on the specified value and label.
     *
     * @param value the value to identify the course
     * @param label the label (column) to identify the course
     * @return true if the course was removed successfully, false otherwise
     */
    public boolean removeCourse(int value, String label) {
        data = new HashMap<>();
        data.put(CONSTVALUE, value);
        data.put(CONSTLABEL, label);
        request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses, each represented as an ArrayList of objects
     */
    public List<ArrayList<Object>> getAllCourses() {
        data = new HashMap<>();
        request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, data);
        return (ArrayList<ArrayList<Object>>) handler.handle(request);
    }

    /**
     * Retrieves a course based on the specified value and label.
     *
     * @param value the value to identify the course
     * @param label the label (column) to identify the course
     * @return a list of course data fields
     */
    public List<String> getCourse(String value, String label) {
        data = new HashMap<>();
        data.put(CONSTVALUE, value);
        data.put(CONSTLABEL, label);
        request = new Request(RequestDao.COURSE, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param courseid the ID of the course
     * @return a list of course data fields
     */
    public List<String> getCourseById(int courseid) {
        data = new HashMap<>();
        data.put("course_id", courseid);
        request = new Request(RequestDao.COURSE, RequestType.GETDATABYID, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Retrieves the list of course IDs associated with a staff member.
     *
     * @param staffId the ID of the staff member
     * @return a list of course IDs
     */
    public List<Integer> getStaffsCourses(int staffId) {
        data = new HashMap<>();
        data.put("id", staffId);
        request = new Request(RequestDao.STAFF_COURSE_JOIN, RequestType.GETDATA, data);
        return (ArrayList<Integer>) handler.handle(request);
    }
}