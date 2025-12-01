package com.attendace.dao.handlers;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoCourseUserJoin extends Handler {

    private static final String USERID = "id";
    //private static final String USER_NAME = "user_name";
    private static final String COURSEID = "course_id";


    @Override
    // If handler cannot process this request, set next handler and return false
    public boolean canProcess(Request request) {
        if (request.getDao() != RequestDao.USER_COURSE_JOIN) {
            setNextHandler(new DaoDegree());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object process(Request request) {
        Map<String, Object> object = request.getData();


        if (request.getType() == RequestType.GETDATA) {
            return getData(object);
        }
        else if (request.getType() == RequestType.SETDATA){
            setData(object);
            return true;
        }
        return false;
    }
    public void setData(Map<String, Object> data) {


    }

public List<Integer> getData(Map<String, Object> object) {

        Integer userId = (Integer) object.get(USERID);

        ArrayList<Integer> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT course_id FROM course_user_join WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            ps.setInt(1, userId);
            while (rs.next()) {
                data.add(Integer.valueOf(Integer.toString(rs.getInt(COURSEID))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // This check the result of the query
        if (data.isEmpty()) {
            return new ArrayList<>();
        } else {
            return data;
        }
    }
}