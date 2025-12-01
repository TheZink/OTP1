package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class DaoDegree extends Handler {
    private static final String ID = "id";
    private static final String DEGREENAME = "degree_name";
    private static final String DEGREEECTS = "degree_ects";

    private static final String VALUE = "value";
    private static final String LABEL = "label";
    private static final String NAME = "degreeName";
    private static final String ECTS = "degreeEcts";


    // This method check if handler can process the request. If handler cannot process this request, set next handler and return false
    @Override
    public boolean canProcess(Request request) {
        return request.getDao() == RequestDao.DEGREE;
    }

    // This method check, if handler can process type of request
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
        else if (request.getType() == RequestType.UPDATEDATA){
            updateData(object);
            return true;
        }
        else if (request.getType() == RequestType.REMOVEDATA){
            removeData(object);
        }
        return false;
    }

    // Fetch all degree from Degree-table. Encapsulate each row in its own array and add to the main data list
    public List<ArrayList<String>> getAllData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, degree_name, degree_ects FROM DEGREE ORDER BY id ASC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(rs.getString(DEGREENAME));
                row.add(Integer.toString(rs.getInt(DEGREEECTS)));
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

    // Fetch specific degree from Degree-table
    public List<String> getData(Map<String, Object> object){
        String value = (String) object.get(VALUE);
        String label = (String) object.get(LABEL);

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, degree_name, degree_etcs FROM DEGREE WHERE " + label + " LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){  
            ps.setString(1, value + "%");

            while (rs.next()){
                data.add(Integer.toString(rs.getInt(ID)));
                data.add(rs.getString(DEGREENAME));
                data.add(Integer.toString(rs.getInt(DEGREEECTS)));
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
    
    // Set staff data to the Degree-table
    public void setData(Map<String, Object> object){

        String degreeName = (String) object.get(NAME);
        int degreeEcts = (int) object.get(ECTS);

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO DEGREE (degree_name, degree_ects) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, degreeName);
            ps.setInt(2, degreeEcts);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(Map<String, Object> object) {
        int id = (int) object.get(ID);
        String degreeName = (String) object.get(NAME);
        int degreeEcts = (int) object.get(ECTS);

        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE DEGREE SET degree_name = ?, degree_ects = ? WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql);){            
            ps.setString(1, degreeName);
            ps.setInt(2, degreeEcts);
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void removeData(Map<String, Object> object) {

        int value = (int) object.get(VALUE);
        String label = (String) object.get(LABEL);

        Connection connection = DbConnection.getConnection();
        String sql = "DELETE FROM DEGREE WHERE " + label + "  =  " + value;

        try(PreparedStatement ps = connection.prepareStatement(sql);){ 
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
