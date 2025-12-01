package com.attendace.controller;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCourseController {
    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;
    private static final String USERID = "id";
    private static final String COURSEID = "course_id";

    public UserCourseController() {
        this.handler = new DefaultHandler();
    }
    public boolean createUserCourse(int user_id, int course_id) {
        data = new HashMap<>();
        data.put(USERID, user_id);
        data.put(COURSEID, course_id);
        request = new Request(RequestDao.USER_COURSE_JOIN, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);

    }
    public List<Integer> getUserCourses(int user_id) {
        data = new HashMap<>();
        data.put(USERID, user_id);
        request = new Request(RequestDao.USER_COURSE_JOIN, RequestType.GETDATA, data);
        return (ArrayList<Integer>) handler.handle(request);

    }
}
