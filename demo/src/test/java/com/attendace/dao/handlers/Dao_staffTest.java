package com.attendace.dao.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;


public class Dao_staffTest {
    
    @Test
    void testCanProcess() {
        Dao_staff handler = new Dao_staff();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, object);
        assertEquals(true, handler.canProcess(request));
    }

    @Test
    void testProcess() {}

    @Test
    void testGetAllData() {
        Dao_staff handler = new Dao_staff();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, object);
        assertNotNull(handler.canProcess(request));
    }

    @Test
    void testGetData() {
        Dao_staff handler = new Dao_staff();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Olli Opettaja");
        Request request = new Request(RequestDao.STAFF, RequestType.GETDATA, object);
        assertNotNull(handler.canProcess(request));
    }

    @Test
    void testCheckLogin() {
        Dao_staff handler = new Dao_staff();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Olli Opettaja");
        object.put("password", "Opettaja12345");
        Request request = new Request(RequestDao.STAFF, RequestType.SIGNIN, object);
        assertEquals((true), handler.handle(request));
    }


    @Test
    void testSetData() {
        Dao_staff handler = new Dao_staff();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Ilkka Sinkonen");
        object.put("role", "Insinööri1");
        object.put("isAdmin", true);
        object.put("password", "12345");

        Request request = new Request(RequestDao.STAFF, RequestType.SETDATA, object);
        assertEquals(null, handler.handle(request));

    }
}
