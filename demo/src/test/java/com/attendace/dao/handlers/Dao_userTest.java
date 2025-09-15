package com.attendace.dao.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class Dao_userTest {
    @Test
    void testCanProcess() {
        Dao_user handler = new Dao_user();
        Map<String, Object> data = new HashMap<>();
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, data);
        assertEquals(true, handler.canProcess(request));
    }

    @Test
    void testCheckLogin() {
        Dao_user handler = new Dao_user();
        Map<String, Object> object = new HashMap<>();
        object.put("user_name", "Toni Testaaja");
        object.put("password", "Salasana");
        Request request = new Request(RequestDao.USERS, RequestType.SIGNIN, object);
        assertEquals(true, handler.handle(request));
    }

    @Test
    void testGetAllData() {
        Dao_user handler = new Dao_user();
        Request request = new Request(RequestDao.USERS, RequestType.GETALLDATA, new HashMap<>());
        Object result = handler.handle(request);
        assertNotNull(result);
    }

    @Test // TODO: Method return always NotNull-value
    void testGetData() {
        Dao_user handler = new Dao_user();
        Map<String, Object> data = new HashMap<>();
        data.put("username", "Toni Testaaj");
        Request request = new Request(RequestDao.USERS, RequestType.GETDATA, data);
        Object result = handler.handle(request);
        assertNotNull(result);
    }

    @Test //TODO: Cannot invoke "java.lang.Boolean.booleanValue()" because the return value of "java.util.Map.get(Object)" is null
    void testSetData() {
        Dao_staff handler = new Dao_staff();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Ilkka Sinkonen");
        object.put("student_id", 25004);
        object.put("degree", "Don't worry ma'am, i'm the engineer");
        object.put("password", "12345");

        Request request = new Request(RequestDao.STAFF, RequestType.SETDATA, object);
        assertEquals(null, handler.handle(request));

    }
}
