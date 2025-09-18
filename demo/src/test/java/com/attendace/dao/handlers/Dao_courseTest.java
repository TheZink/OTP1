package com.attendace.dao.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class Dao_courseTest {
    
    @Test
    void testCanProcess() {
        Dao_course handler = new Dao_course();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, object);
        assertEquals(true, handler.canProcess(request));
    }

    @Test
    void testGetAllData() {
        Dao_course handler = new Dao_course();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, object);
        assertNotNull(handler.handle(request));
    }

    @Test
    void testGetData() {
        Dao_course handler = new Dao_course();
        Map<String, Object> object = new HashMap<>();
        object.put("value","Python");
        object.put("label","course_name");
        Request request = new Request(RequestDao.COURSE, RequestType.GETDATA, object);
        assertNotNull(handler.handle(request));
    }

    @Test
    void testProcess() {

    }

    @Test
    void testSetData() {
        Dao_course handler = new Dao_course();
        Map<String, Object> object = new HashMap<>();
        object.put("course_name", "Java Basic");
        object.put("course_topic", "Programming");
        object.put("course_desc", "Testing setData-method");
        object.put("attendance_avaible", false);
        object.put("attendance_key", "null");
        object.put("min_attendance", 0);
        object.put("max_attendance", 30);
        object.put("course_active", true);
        Request request = new Request(RequestDao.COURSE, RequestType.SETDATA, object);
        assertEquals(true, handler.handle(request));
    }

    @Test
    void testUpdateData() {
        Dao_course handler = new Dao_course();
        Map<String, Object> object = new HashMap<>();

        object.put("value", 4);
        object.put("label", "course_desc");
        object.put("setValue", "Testing");

        Request request = new Request(RequestDao.COURSE, RequestType.UPDATEDATA, object);
        System.out.println(handler.handle(request));
        assertEquals(true, handler.handle(request));
    }

    @Test
    void testRemoveData() {
        Dao_course handler = new Dao_course();
        Map<String, Object> object = new HashMap<>();
        object.put("value", 7);
        object.put("label", "id");
        Request request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, object);
        assertNotNull(handler.handle(request));
    }
}
