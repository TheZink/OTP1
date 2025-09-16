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

        else if (request.getType() == RequestType.UPDATEDATA){
            updateData(object);

            return true;
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

    // Fetch specific course from Course-table
    public ArrayList<String> getData(Map<String, Object> object){

        String value = (String) object.get("value");
        String label = (String) object.get("label");

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM COURSE WHERE " + label + " = ?";


        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, value);
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
    
    // Set course data to the Course-table
    public void setData(Map<String, Object> object) {

        String name = (String) object.get("course_name");
        String topic = (String) object.get("course_topic");
        String desc = (String) object.get("course_desc");
        boolean attend_active = (boolean) object.get("attendance_avaible");
        String attend_key = (String) object.get("attendance_key");
        int min_attend = (int) object.get("min_attendance");
        int max_attend = (int) object.get("max_attendance");
        boolean course_active = (boolean) object.get("course_active");

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO STAFF (course_name, course_topic, course_desc, " + 
                        "attendance_avaible, attendance_key, min_attendance, max_attendance, " +
                        "course_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, topic);
            ps.setString(3, desc);
            ps.setBoolean(4, attend_active);
            ps.setString(5, attend_key);
            ps.setInt(6, min_attend);
            ps.setInt(7, max_attend);
            ps.setBoolean(4, course_active);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update specific course in Course-table
    public void updateData(Map<String, Object> object){

        String value = (String) object.get("value");
        String label = (String) object.get("label");
        String setValue = (String) object.get("setValue");

        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE course SET " + label + " = " + setValue + " WHERE id = " + value;
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
