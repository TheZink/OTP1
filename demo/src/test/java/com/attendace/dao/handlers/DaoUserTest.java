package com.attendace.dao.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

class DaoUserTest {

    @Test
    void testCanProcess() {
        DaoUser handler = new DaoUser();
        Map<String, Object> data = new HashMap<>();
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, data);
        assertEquals(true, handler.canProcess(request));
    }

    @Test
    void testCheckLogin() {
        DaoUser handler = new DaoUser();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Jukka Junitti");
        object.put("password", "12345");
        Request request = new Request(RequestDao.USERS, RequestType.SIGNIN, object);
        assertEquals(true, handler.handle(request));
    }

    @Test
    void testGetAllData() {
        DaoUser handler = new DaoUser();
        Request request = new Request(RequestDao.USERS, RequestType.GETALLDATA, new HashMap<>());
        Object result = handler.handle(request);
        assertNotNull(result);
    }

    @Test
    void testGetData() {
        DaoUser handler = new DaoUser();
        Map<String, Object> data = new HashMap<>();
        data.put("username", "Toni Testaaja");
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, data);
        Object result = handler.handle(request);
        assertNotNull(result);
    }

    @Test
    void testSetData() {
        DaoUser handler = new DaoUser();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Ilkka Sinkonen");
        object.put("user_student_id", 25004);
        object.put("degree", "I'm the engineer");
        object.put("password", "12345");
        Request request = new Request(RequestDao.USERS, RequestType.SETDATA, object);
        assertEquals(true, handler.handle(request));

    }

    @Test
    void testRemoveData() {
        DaoUser handler = new DaoUser();
        Map<String, Object> object = new HashMap<>();
        object.put("value", 4);
        object.put("label", "id");
        Request request = new Request(RequestDao.USERS, RequestType.REMOVEDATA, object);
        assertNotNull(handler.handle(request));
    }
}
