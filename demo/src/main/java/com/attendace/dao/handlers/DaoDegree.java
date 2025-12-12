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

/**
 * Handler for processing degree-related requests in the chain of responsibility.
 * Handles CRUD operations for degrees in the DEGREE table.
 */
public class DaoDegree extends Handler {
    private static final String ID = "id";
    private static final String DEGREENAME = "degree_name";
    private static final String DEGREEECTS = "degree_ects";

    private static final String VALUE = "value";
    private static final String LABEL = "label";
    private static final String NAME = "degreeName";
    private static final String ECTS = "degreeEcts";

    /**
     * Determines if this handler can process the given request.
     * If the request is not for DEGREE, sets the next handler and returns false.
     *
     * @param request the request to check
     * @return true if this handler can process the request, false otherwise
     */
    @Override
    public boolean canProcess(Request request) {
        if (request.getDao() != RequestDao.DEGREE) {
            setNextHandler(new DaoCourseUserJoin());
            return false;
        } else {
            return true;
        }
    }

    /**
     * Processes the given request based on its type.
     * Supports GETALLDATA, GETDATA, SETDATA, UPDATEDATA, and REMOVEDATA.
     *
     * @param request the request to process
     * @return the result of processing the request, or false if not handled
     */
    @Override
    public Object process(Request request){
        Map<String, Object> object = request.getData();

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
        else if (request.getType() == RequestType.REMOVEDATA){
            removeData(object);
        }
        return false;
    }

    /**
     * Fetches all degrees from the DEGREE table.
     * Each row is encapsulated in its own array and added to the main data list.
     *
     * @return a list of degree data rows
     */
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

        if(data.isEmpty()){
            return new ArrayList<>();
        } else {
            return data;
        }
    }

    /**
     * Fetches a specific degree from the DEGREE table based on a label and value.
     *
     * @param object a map containing the label and value for the query
     * @return a list of degree data fields, or an empty list if not found
     */
    public List<String> getData(Map<String, Object> object){
        String value = (String) object.get(VALUE);
        String label = (String) object.get(LABEL);

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, degree_name, degree_ects FROM DEGREE WHERE " + label + " LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, value + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt(ID)));
                data.add(rs.getString(DEGREENAME));
                data.add(Integer.toString(rs.getInt(DEGREEECTS)));
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

    /**
     * Inserts a new degree into the DEGREE table.
     *
     * @param object a map containing degree data fields
     */
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

    /**
     * Updates degree data in the DEGREE table.
     *
     * @param object a map containing degree data fields to update
     */
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

    /**
     * Removes a degree from the DEGREE table based on a specified label and value.
     *
     * @param object a map containing the label and value for deletion
     */
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