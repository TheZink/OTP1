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

/**
 * Handler for processing attendance-related requests in the chain of responsibility.
 * Handles CRUD operations for attendance records in the ATTENDANCE table.
 */
public class DaoAttendance extends Handler {
    private static final String ID = "id";
    private static final String COURSEID = "course_id";
    private static final String USERID = "user_id";
    private static final String STAFFID = "staff_id";
    private static final String ATTENSTATUS = "atten_status";
    private static final String ATTENCURRENT = "atten_current";
    private static final String CREATEDAT = "created_at";
    private static final String VALUE = "value";
    private static final String LABEL = "label";

    private static final List<String> allowedLabels = List.of(ID, COURSEID, USERID, STAFFID, ATTENSTATUS, ATTENCURRENT, CREATEDAT);

    /**
     * Determines if this handler can process the given request.
     * If the request is not for ATTENDANCE, sets the next handler and returns false.
     *
     * @param request the request to check
     * @return true if this handler can process the request, false otherwise
     */
    @Override
    public boolean canProcess(Request request) {
        if (request.getDao() != RequestDao.ATTENDANCE) {
            setNextHandler(new DaoCourseStaffJoin());
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
     * Fetches all attendance records from the ATTENDANCE table.
     * Each row is encapsulated in its own array and added to the main data list.
     *
     * @return a list of attendance data rows
     */
    public List<ArrayList<String>> getAllData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, course_id, user_id, staff_id, atten_status, atten_current, created_at FROM ATTENDANCE ORDER BY id ASC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();){

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(Integer.toString(rs.getInt(COURSEID)));
                row.add(Integer.toString(rs.getInt(USERID)));
                row.add(Integer.toString(rs.getInt(STAFFID)));
                row.add(Boolean.toString(rs.getBoolean(ATTENSTATUS)));
                row.add(Integer.toString(rs.getInt(ATTENCURRENT)));
                row.add(rs.getDate(CREATEDAT).toString());

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
     * Fetches specific attendance records from the ATTENDANCE table based on a label and value.
     *
     * @param object a map containing the label and value for the query
     * @return a list of attendance data rows, or an empty list if not found
     * @throws IllegalArgumentException if the label is not allowed
     */
    public List<ArrayList<String>> getData(Map<String, Object> object) {
        int value = (int) object.get(VALUE);
        String label = (String) object.get(LABEL);

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();

        if (!allowedLabels.contains(label)) {
            throw new IllegalArgumentException("Invalid column name");
        }

        String sql = "SELECT * FROM ATTENDANCE WHERE " + label + " = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();){

            ps.setInt(1, value);

            while (rs.next()){
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(Integer.toString(rs.getInt(COURSEID)));
                row.add(Integer.toString(rs.getInt(USERID)));
                row.add(Integer.toString(rs.getInt(STAFFID)));
                row.add(Boolean.toString(rs.getBoolean(ATTENSTATUS)));
                row.add(Integer.toString(rs.getInt(ATTENCURRENT)));
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

    /**
     * Inserts a new attendance record into the ATTENDANCE table.
     *
     * @param object a map containing attendance data fields
     */
    public void setData(Map<String, Object> object) {
        int course = (int) object.get(COURSEID);
        int user = (int) object.get(USERID);
        int staff = (int) object.get(STAFFID);
        boolean status = (boolean) object.get(ATTENSTATUS);
        int current = (int) object.get(ATTENCURRENT);

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO attendance (course_id, user_id, staff_id, atten_status, atten_current) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
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

    /**
     * Updates a specific attendance record in the ATTENDANCE table.
     *
     * @param object a map containing the label, value, and new value to set
     * @throws IllegalArgumentException if the label is not allowed
     */
    public void updateData(Map<String, Object> object){
        int value = (int) object.get(VALUE);
        String label = (String) object.get(LABEL);
        boolean setValue = (boolean) object.get("setValue");

        Connection connection = DbConnection.getConnection();

        if (!allowedLabels.contains(label)) {
            throw new IllegalArgumentException("Invalid column name");
        }

        String sql = "UPDATE attendance SET " + label + " = " + setValue + " WHERE id = " + value;

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an attendance record from the ATTENDANCE table based on a specified label and value.
     *
     * @param object a map containing the label and value for deletion
     * @throws IllegalArgumentException if the label is not allowed
     */
    public void removeData(Map<String, Object> object) {
        int value = (int) object.get(VALUE);
        String label = (String) object.get(LABEL);

        Connection connection = DbConnection.getConnection();

        if (!allowedLabels.contains(label)) {
            throw new IllegalArgumentException("Invalid column name");
        }

        String sql = "DELETE FROM attendance WHERE " + label + "  =  " + value;

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}