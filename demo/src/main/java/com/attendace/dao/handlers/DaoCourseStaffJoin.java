package com.attendace.dao.handlers;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handler for processing staff-course join requests in the chain of responsibility.
 * Handles operations for joining staff to courses and retrieving joined data.
 */
public class DaoCourseStaffJoin extends Handler {
    private static final String STAFFID = "id";
    private static final String STAFFNAME = "staff_name";
    private static final String STAFFROLE = "staff_role";
    private static final String STAFFADMIN = "staff_admin";
    private static final String CREATEDAT = "created_at";
    private static final String COURSEID = "course_id";

    /**
     * Determines if this handler can process the given request.
     * If the request is not for STAFF_COURSE_JOIN, sets the next handler and returns false.
     *
     * @param request the request to check
     * @return true if this handler can process the request, false otherwise
     */
    @Override
    public boolean canProcess(Request request) {
        if (request.getDao() != RequestDao.STAFF_COURSE_JOIN) {
            setNextHandler(new DaoDegree());
            return false;
        } else {
            return true;
        }
    }

    /**
     * Processes the given request based on its type.
     * Supports GETALLDATA, GETDATA, and SETDATA.
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
        return false;
    }

    /**
     * Fetches all staff from the STAFF table.
     * Each row is encapsulated in its own array and added to the main data list.
     *
     * @return a list of staff data rows
     */
    public List<ArrayList<String>> getAllData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, staff_name, staff_role, staff_admin, created_at FROM STAFF ORDER BY id ASC";

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(STAFFID)));
                row.add(rs.getString(STAFFNAME));
                row.add(rs.getString(STAFFROLE));
                row.add(Boolean.toString(rs.getBoolean(STAFFADMIN)));
                row.add(rs.getTime(CREATEDAT).toString());
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
     * Retrieves a list of course IDs joined by a staff member.
     *
     * @param object a map containing the staff ID
     * @return a list of course IDs, or an empty list if none found
     */
    public List<Integer> getData(Map<String, Object> object){
        Integer staffId = (Integer) object.get(STAFFID);

        ArrayList<Integer> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT course_id FROM course_staff_join WHERE staff_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();) {

            ps.setInt(1, staffId);
            while (rs.next()){
                data.add(Integer.valueOf(Integer.toString(rs.getInt(COURSEID))));
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
     * Inserts a new staff-course join entry into the COURSE_STAFF_JOIN table.
     *
     * @param data a map containing staff and course IDs
     */
    public void setData(Map<String, Object> data) {
        Integer staffId = (Integer) data.get(STAFFID);
        Integer courseId = (Integer) data.get(COURSEID);

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO COURSE_STAFF_JOIN (staff_id, course_id) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, staffId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}