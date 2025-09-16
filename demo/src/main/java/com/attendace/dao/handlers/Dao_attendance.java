package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class Dao_attendance extends Handler {
    
    @Override
    public boolean canProcess(Request request){
        return request.getDao() == RequestDao.ATTENDANCE;
    }

    // This method check, if handler can process type of request

    @Override
    public Object process(Request request){
        Map<String, Object> object = request.getData(); //Fetch data from request

        if (request.getType() == RequestType.GETALLDATA){
            return getAllData();
        }

        else if (request.getType() == RequestType.GETDATA){
            return getData(object);
        }

        else if (request.getType() == RequestType.SETDATA){
            setData(object);
            return true;
        }

        else if (request.getType() == RequestType.UPDATEDATA){
            updateData(object);

            return true;
        }

        return null;
    }

    public ArrayList<String> getAllData(){
        // Fetch all attendance from Course-table.

        // Method return row id, courseId, studentId, staffId, attenStatus, attenCurrent and timestamp

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM ATTENDANCE ORDER BY id DESC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(Integer.toString(rs.getInt("id")));
                data.add(Integer.toString(rs.getInt("course_id")));
                data.add(Integer.toString(rs.getInt("user_id")));
                data.add(Integer.toString(rs.getInt("staff_id")));
                data.add(Boolean.toString(rs.getBoolean("atten_status")));
                data.add(Integer.toString(rs.getInt("atten_current")));
                data.add(rs.getDate("created_at").toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;

    }
    
    // Fetch specific attendance from Course-table
    public ArrayList<String> getData(Map<String, Object> object) {

        String value = (String) object.get("value");
        String label = (String) object.get("label");

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM ATTENDANCE WHERE "+ label +" = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt("id")));
                data.add(Integer.toString(rs.getInt("course_id")));
                data.add(Integer.toString(rs.getInt("user_id")));
                data.add(Integer.toString(rs.getInt("staff_id")));
                data.add(Boolean.toString(rs.getBoolean("atten_status")));
                data.add(Integer.toString(rs.getInt("atten_current")));
                data.add(rs.getDate("created_at").toString());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return data;
    }

    // Set attendance data to the Course-table
    public void setData(Map<String, Object> object) {

        int course = (int) object.get("course_id"); 
        int user = (int) object.get("user_id");
        int staff = (int) object.get("staff_if");
        boolean status = (boolean) object.get("atten_status");
        int current = (int) object.get("atten_current");


        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO STAFF (course_id, user_id, staff_id, atten_status, atten_current) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, course);
            ps.setInt(2, user);
            ps.setInt(3, staff);
            ps.setBoolean(4, status);
            ps.setInt(5, current);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update specific attendance in Attendance-table
    public void updateData(Map<String, Object> object){

        String value = (String) object.get("value");
        String label = (String) object.get("label");
        int setValue = (int) object.get("setValue");

        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE attendance SET " + label + " = " + setValue + " WHERE id = " + value;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}