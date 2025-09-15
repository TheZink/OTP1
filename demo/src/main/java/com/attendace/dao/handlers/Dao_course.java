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

public class Dao_course extends Handler {

// This method check if handler can process the request

    @Override
    public boolean canProcess(Request request){
        return request.getDao() == RequestDao.COURSE;
    }

    // This method check, if handler can process type of request

    @Override
    public Object process(Request request){
        Map<String, Object> data = request.getData(); //Fetch data from request

        if (request.getType() == RequestType.GETALLDATA){
            return getAllData();
        }

        else if (request.getType() ==RequestType.GETDATA){
            String username = (String) data.get("username");
            return getData(username);
        }

        return null;
    }

    public ArrayList<String> getAllData(){

        // Fetch all courses from Course-table.

        // Method return row id, name, topic, description, attendance-status, attendance-key, 
        // min-, max-attendance, course-activity and timestamp
        
        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM COURSE ORDER BY id DESC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("course_name"));
                data.add(rs.getString("course_topic"));
                data.add(rs.getString("course_desc"));
                data.add(Boolean.toString(rs.getBoolean("attendance_avaible")));
                data.add(Integer.toString(rs.getInt("attendance_key")));
                data.add(Integer.toString(rs.getInt("min_attendance")));
                data.add(Integer.toString(rs.getInt("max_attendance")));
                data.add(Boolean.toString(rs.getBoolean("course_active")));
                data.add(rs.getDate("created_at").toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ArrayList<String> getData(String courseName){

        // Fetch specific course from Course-table

        // Method return row id, name, topic, description, attendance-status, attendance-key, 
        // min-, max-attendance, course-activity and timestamp

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM COURSE WHERE course_name = ?";


        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("course_name"));
                data.add(rs.getString("course_topic"));
                data.add(rs.getString("course_desc"));
                data.add(Boolean.toString(rs.getBoolean("attendance_avaible")));
                data.add(rs.getString("attendance_key"));
                data.add(Integer.toString(rs.getInt("min_attendance")));
                data.add(Integer.toString(rs.getInt("max_attendance")));
                data.add(Boolean.toString(rs.getBoolean("course_active")));
                data.add(rs.getDate("created_at").toString());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return data;
    }
    
    public void setData(String name, String topic, String desc, boolean attenAvaible, String attenKey,  int attenMin, int attenMax, boolean active) {

        // Set course data to the Course-table

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO STAFF (course_name, course_topic, course_desc, " + 
                        "attendance_avaible, attendance_key, min_attendance, max_attendance, " +
                        "course_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, topic);
            ps.setString(3, desc);
            ps.setBoolean(4, attenAvaible);
            ps.setString(5, attenKey);
            ps.setInt(6, attenMin);
            ps.setInt(7, attenMax);
            ps.setBoolean(4, active);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
