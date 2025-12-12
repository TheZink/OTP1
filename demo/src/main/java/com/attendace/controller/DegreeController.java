package com.attendace.controller;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.model.DegreeModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for managing degree-related operations.
 * Provides methods to create a degree and retrieve the current degree.
 */
public class DegreeController {
    private DegreeModel degreeModel;

    private static final String NAME = "degreeName";
    private static final String ECTS = "degreeEcts";

    private final DefaultHandler handler;

    /**
     * Constructs a new DegreeController and initializes the handler.
     */
    public DegreeController() {
        this.handler = new DefaultHandler();
    }

    /**
     * Creates a new degree with the specified name and ECTS value.
     *
     * @param name the name of the degree
     * @param ects the ECTS value of the degree
     * @return true if the degree was created successfully, false otherwise
     */
    public boolean createDegree(String name, int ects) {
        Map<String, Object> data;
        Request request;
        data = new HashMap<>();
        data.put(NAME, name);
        data.put(ECTS, ects);
        request = new Request(RequestDao.DEGREE, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Retrieves the current DegreeModel.
     *
     * @return the current DegreeModel instance
     */
    public DegreeModel getDegree() {
        return degreeModel;
    }
}