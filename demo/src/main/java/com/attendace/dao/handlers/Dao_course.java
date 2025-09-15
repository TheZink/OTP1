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
            String value = (String) data.get("value");
            String label = (String) data.get("label");
            return getData(value, label);
        }

        else if (request.getType() == RequestType.SETDATA){

            String name = (String) data.get("course_name");
            String topic = (String) data.get("course_topic");
            String desc = (String) data.get("course_desc");
            boolean attend_active = (boolean) data.get("attendance_avaible");
            String attend_key = (String) data.get("attendance_key");
            int min_attend = (int) data.get("min_attendance");
            int max_attend = (int) data.get("max_attendance");
            boolean course_active = (boolean) data.get("course_active");


            setData(name, topic, desc, attend_active, attend_key, min_attend, max_attend, course_active);

            return true;
        }

        else if (request.getType() == RequestType.UPDATEDATA){

            String value = (String) data.get("value");
            String label = (String) data.get("label");
            String setValue = (String) data.get("setValue");

            updateData(value, label, setValue);

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

    public ArrayList<String> getData(String value, String label){

        // Fetch specific course from Course-table

        // Method return row id, name, topic, description, attendance-status, attendance-key, 
        // min-, max-attendance, course-activity and timestamp

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

    public void updateData(String value, String label, String setValue){

        // Update specific course in Course-table

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
