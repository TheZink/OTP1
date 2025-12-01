package com.attendace.dao.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;


class DaoStaffTest {
    
    @Test
    void testCanProcess() {
        DaoStaff handler = new DaoStaff();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, object);
        assertEquals(true, handler.canProcess(request));
    }

    @Test
    void testGetAllData() {
        DaoStaff handler = new DaoStaff();
        Map<String, Object> object = new HashMap<>();
        Request request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, object);
        assertNotNull(handler.handle(request));
    }

    @Test
    void testGetData() {
        DaoStaff handler = new DaoStaff();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Olli Opettaja");
        Request request = new Request(RequestDao.STAFF, RequestType.GETDATA, object);
        assertNotNull(handler.handle(request));
    }

    @Test
    void testCheckLogin() {
        DaoStaff handler = new DaoStaff();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Toni Tukihenkilö");
        object.put("password", "godmode12345");
        Request request = new Request(RequestDao.STAFF, RequestType.SIGNIN, object);
        assertEquals(true, handler.handle(request));
    }


    @Test
    void testSetData() {
        DaoStaff handler = new DaoStaff();
        Map<String, Object> object = new HashMap<>();
        object.put("username", "Ilkka Sinkonen");
        object.put("role", "Insinööri1");
        object.put("isAdmin", true);
        object.put("password", "12345");
        object.put("lang", "en");

        Request request = new Request(RequestDao.STAFF, RequestType.SETDATA, object);
        assertEquals(true, handler.handle(request));
    }

    @Test
    void testRemoveData() {
        DaoStaff handler = new DaoStaff();
        Map<String, Object> object = new HashMap<>();
        object.put("value", 4);
        object.put("label", "id");
        Request request = new Request(RequestDao.STAFF, RequestType.REMOVEDATA, object);
        assertNotNull(handler.handle(request));
    }
}
