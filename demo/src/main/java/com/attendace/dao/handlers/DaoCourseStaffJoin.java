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

public class DaoCourseStaffJoin extends Handler {
    private static final String STAFFID = "id";
    private static final String STAFFNAME = "staff_name";
    private static final String STAFFROLE = "staff_role";
    private static final String STAFFADMIN = "staff_admin";
    private static final String CREATEDAT = "created_at";

    private static final String COURSEID = "course_id";

    @Override
    // If handler cannot process this request, set next handler and return false
    public boolean canProcess(Request request) {
        if (request.getDao() != RequestDao.STAFF_COURSE_JOIN) {
            setNextHandler(new DaoDegree());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object process(Request request){
        Map<String, Object> object = request.getData();

        if (request.getType() == RequestType.GETALLDATA){

            return getAllData();
        }
        else if (request.getType() ==RequestType.GETDATA){
            return getData(object);
        }
        else if (request.getType() == RequestType.SETDATA){
            setData(object);
            return true;
        }
        return false;
    }

    // Fetch all. Encapsulate each row in its own array and add to the main data list
    public List<ArrayList<String>> getAllData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, staff_name, staff_role, staff_admin, created_at FROM STAFF ORDER BY id ASC";

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(STAFFID)));
                row.add(rs.getString(STAFFNAME));
                row.add(rs.getString(STAFFROLE));
                row.add(Boolean.toString(rs.getBoolean(STAFFADMIN)));
                row.add(rs.getTime(CREATEDAT).toString());
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // This check the result of the query
        if(data.isEmpty()){
            return new ArrayList<>();
        } else {
            return data;
        }
    }

    // Fetch course_id's of specific staff. SHOULD WORK
    public List<Integer> getData(Map<String, Object> object){

        Integer staffId = (Integer) object.get(STAFFID);

        ArrayList<Integer> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT course_id FROM course_staff_join WHERE staff_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();) {

            ps.setInt(1, staffId);
            while (rs.next()){
                data.add(Integer.valueOf(Integer.toString(rs.getInt(COURSEID))));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        // This check the result of the query
        if(data.isEmpty()){
            return new ArrayList<>();
        } else {
            return data;
        }   
    }

    public void setData(Map<String, Object> data) {
        // NOT WORKING
    }
}
