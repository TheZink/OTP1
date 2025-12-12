package com.attendace.dao;

import java.util.Map;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

/**
 * Represents a request to be handled by a DAO (Data Access Object).
 * Encapsulates the DAO handler, the type of request, and any associated data.
 */
public class Request {

    /** The DAO handler for processing the request. */
    private RequestDao dao;

    /** The type of request (e.g., GetData, GetAllData, etc.). */
    private RequestType type;

    /** The data associated with the request (e.g., username, role, etc.). */
    private Map<String, Object> data;

    /**
     * Constructs a new Request with the specified DAO handler, request type, and data.
     *
     * @param dao  the DAO handler for the request
     * @param type the type of request
     * @param data the data associated with the request
     */
    public Request(RequestDao dao, RequestType type, Map<String, Object> data) {
        this.dao = dao;
        this.type = type;
        this.data = data;
    }

    /**
     * Gets the DAO handler for this request.
     *
     * @return the DAO handler
     */
    public RequestDao getDao() {
        return dao;
    }

    /**
     * Gets the type of this request.
     *
     * @return the request type
     */
    public RequestType getType() {
        return type;
    }

    /**
     * Gets the data associated with this request.
     *
     * @return a map containing the request data
     */
    public Map<String, Object> getData() {
        return data;
    }
}