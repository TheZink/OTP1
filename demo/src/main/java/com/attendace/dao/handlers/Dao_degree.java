package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class Dao_degree extends Handler {

    // This method check if handler can process the request

    @Override
    public boolean canProcess(Request request) {
        // If handler cannot process this request, set next handler and return false
        if (request.getDao() != RequestDao.DEGREE) {
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

        else if (request.getType() == RequestType.SETDATA){
            setData(object);
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

    // Fetch all degree from Degree-table.
    public ArrayList<ArrayList<String>> getAllData(){

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM DEGREE ORDER BY id ASC";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                // Encapsulate each row in its own array and add to the main data list
                ArrayList<String> row = new ArrayList<>();

                row.add(Integer.toString(rs.getInt("id")));
                row.add(rs.getString("degree_name"));
                row.add(Integer.toString(rs.getInt("degree_ects")));

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

    // Fetch specific user from Degree-table
    public ArrayList<String> getData(Map<String, Object> object){

        String value = (String) object.get("value");
        String label = (String) object.get("label");

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM DEGREE WHERE " + label + " LIKE ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, value + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("degree_name"));
                data.add(Integer.toString(rs.getInt("degree_ects")));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        
        // This check the result of the query
        if(data.isEmpty()){
            return null;}
        else {
            return data;}  
    }
    
    // Set staff data to the Degree-table
    public void setData(Map<String, Object> object){

        String degreeName = (String) object.get("degreeName");
        int degreeEcts = (int) object.get("degreeEcts");

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO DEGREE (degree_name, degree_ects) VALUES (?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, degreeName);
            ps.setInt(2, degreeEcts);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(Map<String, Object> object) {

        int id = (int) object.get("id");
        String degreeName = (String) object.get("degreeName");
        int degreeEcts = (int) object.get("degreeEcts");

        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE DEGREE SET degree_name = ?, degree_ects = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, degreeName);
            ps.setInt(2, degreeEcts);
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void removeData(Map<String, Object> object) {

        int value = (int) object.get("value");
        String label = (String) object.get("label");

        Connection connection = DbConnection.getConnection();
        String sql = "DELETE FROM DEGREE WHERE " + label + "  =  " + value;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
