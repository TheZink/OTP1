package com.attendace.controller;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.model.CourseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing user-course relationships.
 * Provides methods to create a user-course join and retrieve courses joined by a user.
 */
public class UserCourseController {
    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;
    private static final String USERID = "id";
    private static final String COURSEID = "course_id";

    /**
     * Constructs a new UserCourseController and initializes the handler.
     */
    public UserCourseController() {
        this.handler = new DefaultHandler();
    }

    /**
     * Creates a join between a user and a course.
     *
     * @param user_id the user ID
     * @param course_id the course ID
     * @return true if the join was created successfully, false otherwise
     */
    public boolean createUserCourse(int user_id, int course_id) {
        data = new HashMap<>();
        data.put(USERID, user_id);
        data.put(COURSEID, course_id);
        request = new Request(RequestDao.USER_COURSE_JOIN, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Retrieves the list of courses joined by a user.
     *
     * @param user_id the user ID
     * @return a list of CourseModel objects representing the user's courses
     */
    public List<CourseModel> getUserCoursesById(int user_id) {
        data = new HashMap<>();
        data.put(USERID, user_id);
        request = new Request(RequestDao.USER_COURSE_JOIN, RequestType.GETDATABYID, data);
        return (List<CourseModel>) handler.handle(request);
    }
}