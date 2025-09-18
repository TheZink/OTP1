package com.attendace.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.attendace.dao.handlers.Dao_attendance;
import com.attendace.dao.handlers.Dao_course;
import com.attendace.dao.handlers.Dao_staff;
import com.attendace.dao.handlers.Dao_user;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class HandlerTest {

    Handler userHandler = new Dao_user();
    Handler staffHandler = new Dao_staff();
    Handler courseHandler = new Dao_course();
    Handler attendanceHandler = new Dao_attendance();

    { 
        userHandler.setNextHandler(staffHandler);
        staffHandler.setNextHandler(courseHandler);
        courseHandler.setNextHandler(attendanceHandler);
    }

    @Test
    void testAddStudent(){
        HashMap<String, Object> object = new HashMap<>();
        object.put("username", "Ilkka Sinkonen");
        object.put("student_id", 25004);
        object.put("degree", "I'm the engineer");
        object.put("password", "12345");
        Request request = new Request(RequestDao.USERS, RequestType.SETDATA,object);
        assertNotNull(userHandler.handle(request));
    }

    @Test
    void testAddStaff(){
        HashMap<String, Object> object = new HashMap<>();
        object.put("username", "Gabriel Godmode");
        object.put("role", "GodMode");
        object.put("isAdmin", true);
        object.put("password", "12345");
        Request request = new Request(RequestDao.STAFF, RequestType.SETDATA, object);
        assertNotNull(userHandler.handle(request));
    }

    @Test
    void testAddCourse(){
        Map<String, Object> object = new HashMap<>();
        object.put("course_name", "Chain of Rensponsibility");
        object.put("course_topic", "Testing Handlers");
        object.put("course_desc", "Testing setData-method");
        object.put("attendance_avaible", false);
        object.put("attendance_key", "null");
        object.put("min_attendance", 0);
        object.put("max_attendance", 30);
        object.put("course_active", true);
        Request request = new Request(RequestDao.COURSE, RequestType.SETDATA, object);
        assertNotNull(userHandler.handle(request));
    }

    @Test
    void testAddAttendance(){
        Map<String, Object> object = new HashMap<>();
        object.put("course_id", 1);
        object.put("user_id", 2);
        object.put("staff_id", 1);
        object.put("atten_status", false);
        object.put("atten_current", 1);
        Request request = new Request(RequestDao.ATTENDANCE, RequestType.SETDATA, object);
        assertNotNull(userHandler.handle(request));
    }
}
