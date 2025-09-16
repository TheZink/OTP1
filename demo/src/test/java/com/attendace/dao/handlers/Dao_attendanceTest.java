package com.attendace.dao.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class Dao_attendanceTest {
    @Test
    void testCanProcess() {
        Dao_attendance handler = new Dao_attendance();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.ATTENDANCE, RequestType.GETALLDATA, object);
        assertEquals(true, handler.canProcess(request));

    }

    @Test
    void testGetAllData() {
        Dao_attendance handler = new Dao_attendance();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.ATTENDANCE, RequestType.GETALLDATA, object);
        assertNotNull(handler.handle(request));
    }

    @Test
    void testGetData() {
        Dao_attendance handler = new Dao_attendance();
        Map<String, Object> object = new HashMap<>();
        object.put("value",2);
        object.put("label", "user_id");
        Request request = new Request(RequestDao.ATTENDANCE, RequestType.GETDATA, object);
        assertNotNull(handler.handle(request));
    }

    @Test
    void testProcess() {}

    @Test
    void testSetData() {
        Dao_attendance handler = new Dao_attendance();
        Map<String, Object> object = new HashMap<>();
        object.put("course_id", 1);
        object.put("user_id", 2);
        object.put("staff_id", 1);
        object.put("atten_status", false);
        object.put("atten_current", 1);
        Request request = new Request(RequestDao.ATTENDANCE, RequestType.SETDATA, object);
        assertEquals(true, handler.handle(request));
    }

    @Test
    void testUpdateData() {
        Dao_attendance handler = new Dao_attendance();
        Map<String, Object> object = new HashMap<>();
        object.put("value", 1);
        object.put("label", "atten_status");
        object.put("setValue", false);
        Request request = new Request(RequestDao.ATTENDANCE, RequestType.UPDATEDATA, object);
        assertNotNull(handler.handle(request));
    }
}
