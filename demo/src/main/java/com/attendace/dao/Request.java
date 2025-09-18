package com.attendace.dao;

import java.util.Map;

import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

public class Request{
    private RequestDao dao; // Request for Dao handler
    private RequestType type; // Request type for Dao (GetData, GetAlldata etc.)
    private Map<String, Object> data; // Data for Dao (username, role etc.)

    public Request(RequestDao dao, RequestType type, Map<String, Object> data){
        this.dao = dao;
        this.type = type;
        this.data = data;
    }

    public RequestDao getDao(){
        return dao;
    }

    public RequestType getType(){
        return type;
    }

    public Map<String, Object> getData(){
        return data;
    }

}