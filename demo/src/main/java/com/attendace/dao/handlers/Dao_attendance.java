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
        Map<String, Object> data = request.getData(); //Fetch data from request

        if (request.getType() == RequestType.GETALLDATA){
            return getAllData();
        }

        else if (request.getType() == RequestType.GETDATA){

            String value = (String) data.get("value");
            String label = (String) data.get("label");

            return getData(value, label);
        }

        else if (request.getType() == RequestType.SETDATA){

            int course = (int) data.get("course_id"); 
            int user = (int) data.get("user_id");
            int staff = (int) data.get("staff_if");
            boolean status = (boolean) data.get("atten_status");
            int current = (int) data.get("atten_current");

            setData(course, user, staff, status, current);

            return true;
        }

        else if (request.getType() == RequestType.UPDATEDATA){

            String value = (String) data.get("value");
            String label = (String) data.get("label");
            int setValue = (int) data.get("setValue");

            updateData(value, label, setValue);

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

    public ArrayList<String> getData(String value, String label) {

        // Fetch specific attendance from Course-table
        // Method return row id, courseId, studentId, staffId, attenStatus, attenCurrent and timestamp

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

    public void setData(int course, int user, int staff, boolean status, int current) {

        // Set attendance data to the Course-table

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

    public void updateData(String value, String label, int setValue){

        // Update specific attendance in Attendance-table

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