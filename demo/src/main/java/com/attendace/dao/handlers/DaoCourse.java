package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class DaoCourse extends Handler {
    private static final String ID = "id";
    private static final String COURSENAME = "course_name";
    private static final String COURSETOPIC = "course_topic";
    private static final String COURSEDESC = "course_desc";
    private static final String ATTENDAVAIBLE = "attendance_avaible";
    private static final String ATTENDKEY = "attendance_key";
    private static final String MINATTEND = "min_attendance";
    private static final String MAXATTEND = "max_attendance";
    private static final String COURSEACTIVE = "course_active";
    private static final String CREATEDAT = "created_at";

    private static final String VALUE = "value";
    private static final String LABEL = "label";

    // This method check if handler can process the request
    @Override
    public boolean canProcess(Request request) {
        // If handler cannot process this request, set next handler and return false
        if (request.getDao() != RequestDao.COURSE) {
            setNextHandler(new DaoAttendance());
            return false;
        } else {
            return true;
        }
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
        else if (request.getType() ==RequestType.GETDATABYID){
            return getDataById(object);
        }
        else if (request.getType() == RequestType.SETDATA){
            setData(object);
            return true;
        }
        else if (request.getType() == RequestType.UPDATEDATA){
            updateData(object);
            return true;
        }
        else if (request.getType() == RequestType.REMOVEDATA){
            removeData(object);
        }
        return false;
    }

    // Fetch all courses from Course-table. Encapsulate each row in its own array and add to the main data list
    public List<ArrayList<String>> getAllData(){
        
        ArrayList<ArrayList<String> > data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, course_name, course_topic, course_desc, attendance_avaible, "+
         " attendance_key, min_attendance, max_attendance, course_active, created_at FROM COURSE ORDER BY id ASC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(rs.getString(COURSENAME));
                row.add(rs.getString(COURSETOPIC));
                row.add(rs.getString(COURSEDESC));
                row.add(Boolean.toString(rs.getBoolean(ATTENDAVAIBLE)));
                row.add(rs.getString(ATTENDKEY));
                row.add(Integer.toString(rs.getInt(MINATTEND)));
                row.add(Integer.toString(rs.getInt(MAXATTEND)));
                row.add(Boolean.toString(rs.getBoolean(COURSEACTIVE)));
                row.add(rs.getDate(CREATEDAT).toString());
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

    public List<ArrayList<String>> getDataById(Map<String, Object> object){

        Integer courseId = (Integer) object.get("course_id");


        ArrayList<ArrayList<String>> data = new ArrayList<>();

        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, course_name, course_topic, course_desc, attendance_avaible, + " +
                    "attendance_key, min_attendance, max_attendance, course_active, created_at FROM COURSE WHERE id = ?";


        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){

            ps.setInt(1, courseId);

            while (rs.next()){
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(rs.getString(COURSENAME));
                row.add(rs.getString(COURSETOPIC));
                row.add(rs.getString(COURSEDESC));
                row.add(Boolean.toString(rs.getBoolean(ATTENDAVAIBLE)));
                row.add(rs.getString(ATTENDKEY));
                row.add(Integer.toString(rs.getInt(MINATTEND)));
                row.add(Integer.toString(rs.getInt(MAXATTEND)));
                row.add(Boolean.toString(rs.getBoolean(COURSEACTIVE)));
                row.add(rs.getDate(CREATEDAT).toString());
                data.add(row);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        if(data.isEmpty()){
            return new ArrayList<>();
        } else {
            return data;
        }
    }

    // Fetch specific course from Course-table
    public List<ArrayList<String>> getData(Map<String, Object> object){

        String value = (String) object.get(VALUE);
        String label = (String) object.get(LABEL);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, course_name, course_topic, course_desc, attendance_avaible, + " +
            "  attendance_key, min_attendance, max_attendance, course_active, created_at FROM COURSE WHERE " + label + " LIKE ?";


        try (PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();){

            ps.setString(1, value + "%");

            while (rs.next()){
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(rs.getString(COURSENAME));
                row.add(rs.getString(COURSETOPIC));
                row.add(rs.getString(COURSEDESC));
                row.add(Boolean.toString(rs.getBoolean(ATTENDAVAIBLE)));
                row.add(rs.getString(ATTENDKEY));
                row.add(Integer.toString(rs.getInt(MINATTEND)));
                row.add(Integer.toString(rs.getInt(MAXATTEND)));
                row.add(Boolean.toString(rs.getBoolean(COURSEACTIVE)));
                row.add(rs.getDate(CREATEDAT).toString());
                data.add(row);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        
        if(data.isEmpty()){
            return new ArrayList<>();
        } else {
            return data;
        }   
    }
    
    // Set course data to the Course-table
    public void setData(Map<String, Object> object) {

        boolean attendActive = (boolean) object.get(ATTENDAVAIBLE);
        int minAttend = (int) object.get(MINATTEND);
        int maxAttend = (int) object.get(MAXATTEND);
        boolean courseActive = (boolean) object.get(COURSEACTIVE);

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO Course (course_name, course_topic, course_desc, " + 
                    "attendance_avaible, attendance_key, min_attendance, max_attendance, " +
                    "course_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, COURSENAME);
            ps.setString(2, COURSETOPIC);
            ps.setString(3, COURSEDESC);
            ps.setBoolean(4, attendActive);
            ps.setString(5, ATTENDKEY);
            ps.setInt(6, minAttend);
            ps.setInt(7, maxAttend);
            ps.setBoolean(8, courseActive);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update specific course in Course-table
    public void updateData(Map<String, Object> object){

        int id = (int) object.get("id");
        String couresName = (String) object.get("courseName");
        String couresTopic = (String) object.get("courseTopic");
        String couresDesc = (String) object.get("courseDesc");
        Boolean attendAvaib = (Boolean) object.get("attendAvaib");
        String attendKey = (String) object.get("attendKey");
        int attendMin = (int) object.get("attendMin");
        int attendMax = (int) object.get("attendMax");
        Boolean courseActive = (Boolean) object.get("courseActive");
        
        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE course SET course_name = ?, course_topic = ?, course_desc = ?, attendance_avaible = ?, " +
                    "attendance_key= ?, min_attendance= ?, max_attendance= ?, course_active= ? WHERE id= ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, couresName);
            ps.setString(2, couresTopic);
            ps.setString(3, couresDesc);
            ps.setBoolean(4, attendAvaib);
            ps.setString(5, attendKey);
            ps.setInt(6, attendMin);
            ps.setInt(7, attendMax);
            ps.setBoolean(8, courseActive);
            ps.setInt(9, id);

            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeData(Map<String, Object> object) {

        int value = (int) object.get(VALUE);
        String label = (String) object.get(LABEL);

        Connection connection = DbConnection.getConnection();
        String sql = "DELETE FROM Course WHERE " + label + "  =  " + value;

        try(PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
