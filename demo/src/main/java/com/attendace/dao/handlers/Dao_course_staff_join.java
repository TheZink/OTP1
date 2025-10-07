package com.attendace.dao.handlers;

import com.attendace.Utils.UserUtils;
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
import java.util.Map;

public class Dao_course_staff_join extends Handler {

    @Override
    public boolean canProcess(Request request) {
        // If handler cannot process this request, set next handler and return false
        if (request.getDao() != RequestDao.STAFF_COURSE_JOIN) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object process(Request request){
        Map<String, Object> object = request.getData(); //Fetch data from request

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

    // Fetch all
    public ArrayList<ArrayList<String>> getAllData(){

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM STAFF ORDER BY id ASC";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                // Encapsulate each row in its own array and add to the main data list
                ArrayList<String> row = new ArrayList<>();

                row.add(Integer.toString(rs.getInt("id")));
                row.add(rs.getString("staff_name"));
                row.add(rs.getString("staff_role"));
                row.add(Boolean.toString(rs.getBoolean("staff_admin")));
                row.add(rs.getTime("created_at").toString());

                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // This check the result of the query
        if(data.isEmpty()){
            return null;}
        else {
            return data;}
    }

    // Fetch course_id's of specific staff. SHOULD WORK
    public ArrayList<Integer> getData(Map<String, Object> object){
        System.out.println("olen täällä");

        Integer staffId = (Integer) object.get("id");

        ArrayList<Integer> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT course_id FROM course_staff_join WHERE staff_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, staffId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                data.add(Integer.valueOf(Integer.toString(rs.getInt("course_id"))));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        // This check the result of the query
        if(data.isEmpty()){
            return null;}
        else {
            return data;
        }
    }

    // NOT WORKING
    public void setData(Map<String, Object> data) {

    }
}
