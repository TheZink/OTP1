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

    // Fetch all courses from Course-table.
    public ArrayList<ArrayList<String>> getAllData(){
        
        ArrayList<ArrayList<String> > data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM COURSE ORDER BY id ASC";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                // Encapsulate each row in its own array and add to the main data list
                ArrayList<String> row = new ArrayList<>();

                row.add(Integer.toString(rs.getInt("id")));
                row.add(rs.getString("course_name"));
                row.add(rs.getString("course_topic"));
                row.add(rs.getString("course_desc"));
                row.add(Boolean.toString(rs.getBoolean("attendance_avaible")));
                row.add(Integer.toString(rs.getInt("attendance_key")));
                row.add(Integer.toString(rs.getInt("min_attendance")));
                row.add(Integer.toString(rs.getInt("max_attendance")));
                row.add(Boolean.toString(rs.getBoolean("course_active")));
                row.add(rs.getDate("created_at").toString());

                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // This check the result of the query
        if (data.isEmpty()) {
            return null;
        } else {
            return data;
        }
    }

    // Fetch specific course from Course-table
    public ArrayList<ArrayList<String>> getData(Map<String, Object> object){

        String value = (String) object.get("value");
        String label = (String) object.get("label");

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM COURSE WHERE " + label + " LIKE ?";


        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, value + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                ArrayList<String> row = new ArrayList<>();

                row.add(Integer.toString(rs.getInt("id")));
                row.add(rs.getString("course_name"));
                row.add(rs.getString("course_topic"));
                row.add(rs.getString("course_desc"));
                row.add(Boolean.toString(rs.getBoolean("attendance_avaible")));
                row.add(rs.getString("attendance_key"));
                row.add(Integer.toString(rs.getInt("min_attendance")));
                row.add(Integer.toString(rs.getInt("max_attendance")));
                row.add(Boolean.toString(rs.getBoolean("course_active")));
                row.add(rs.getDate("created_at").toString());

                data.add(row);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        
        if (data.isEmpty()){
            return null;
        } else {
            return data;
        }
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
        String sql = "INSERT INTO Course (course_name, course_topic, course_desc, " + 
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
            ps.setBoolean(8, course_active);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update specific course in Course-table
    public void updateData(Map<String, Object> object){

        int value = (int) object.get("value");
        String label = (String) object.get("label");
        String setValue = (String) object.get("setValue");

        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE course SET " + label + " = '" + setValue + "' WHERE id =" + value;
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
