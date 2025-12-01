package com.attendace.controller;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.model.DegreeModel;

import java.util.HashMap;
import java.util.Map;

public class DegreeController {
    private DegreeModel degreeModel;

    private static final String NAME = "degreeName";
    private static final String ECTS = "degreeEcts";

    private final DefaultHandler handler;


    public DegreeController() {
        this.handler = new DefaultHandler();
    }

    public boolean createDegree(String name, int ects) {
        Map<String, Object> data;
        Request request;
        data = new HashMap<>();
        data.put(NAME, name);
        data.put(ECTS, ects);
        request = new Request(RequestDao.DEGREE, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
}
    public DegreeModel getDegree() {
        return degreeModel;
    }
}
