package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.attendace.utils.UserUtils;
import com.attendace.utils.CryptoUtils;
import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

/**
 * Handler for processing staff-related requests in the chain of responsibility.
 * Handles CRUD operations and authentication for staff in the STAFF table.
 */
public class DaoStaff extends Handler {
    private static final String ID = "id";
    private static final String STAFFNAME = "staff_name";
    private static final String STAFFROLE = "staff_role";
    private static final String STAFFADMIN = "staff_admin";
    private static final String STAFFPASSWORD = "staff_passw";
    private static final String CREATEDAT = "created_at";
    private static final String LANGUAGE = "lang";

    private static final String VALUE = "value";
    private static final String LABEL = "label";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String ISADMIN = "isAdmin";
    private static final CryptoUtils cryptoUtils = new CryptoUtils();

    /**
     * Determines if this handler can process the given request.
     * If the request is not for STAFF, sets the next handler and returns false.
     *
     * @param request the request to check
     * @return true if this handler can process the request, false otherwise
     */
    @Override
    public boolean canProcess(Request request) {
        if (request.getDao() != RequestDao.STAFF) {
            setNextHandler(new DaoCourse());
            return false;
        } else {
            return true;
        }
    }

    /**
     * Processes the given request based on its type.
     * Supports GETALLDATA, GETDATA, SETDATA, SIGNIN, LANGUAGE, UPDATEDATA, and REMOVEDATA.
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
        else if (request.getType() ==RequestType.GETDATA){
            return getData(object);
        }
        else if (request.getType() == RequestType.SETDATA){
            setData(object);
            return true;
        }
        else if (request.getType() == RequestType.SIGNIN){
            return checkLogin(object);
        }
        else if (request.getType() == RequestType.LANGUAGE){
            return checkLang(object);
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
     * Fetches all staff from the STAFF table.
     * Each row is encapsulated in its own array and added to the main data list.
     *
     * @return a list of staff data rows
     */
    public List<ArrayList<String>> getAllData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, staff_name, staff_role, staff_admin, created_at, lang FROM STAFF ORDER BY id ASC";

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(rs.getString(STAFFNAME));
                row.add(rs.getString(STAFFROLE));
                row.add(Boolean.toString(rs.getBoolean(STAFFADMIN)));
                row.add(rs.getTime(CREATEDAT).toString());
                row.add(rs.getString(LANGUAGE));
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
     * Fetches a specific staff member from the STAFF table by username.
     *
     * @param object a map containing the username
     * @return a list of staff data fields, or an empty list if not found
     */
    public List<String> getData(Map<String, Object> object){
        String username = (String) object.get(USERNAME);

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, staff_name, staff_role, staff_admin, staff_passw, created_at, lang FROM STAFF WHERE staff_name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data.add(Integer.toString(rs.getInt(ID)));
                    data.add(rs.getString(STAFFNAME));
                    data.add(rs.getString(STAFFROLE));
                    data.add(Boolean.toString(rs.getBoolean(STAFFADMIN)));
                    data.add(rs.getString(STAFFPASSWORD));
                    data.add(rs.getTime(CREATEDAT).toString());
                    data.add(rs.getString(LANGUAGE));
                }
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
     * Inserts a new staff member into the STAFF table.
     * Validates and prepares staff data before insertion.
     *
     * @param data a map containing staff data fields
     */
    public void setData(Map<String, Object> data){
        UserUtils user = new UserUtils();
        Map<String, Object> object = user.checkStaff(data);

        String username = (String) object.get(USERNAME);
        String role = (String) object.get(ROLE);
        boolean isAdmin = (boolean) object.get(ISADMIN);
        String passw = (String) object.get(PASSWORD);
        String lang = (String) object.get(LANGUAGE);

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO STAFF (staff_name, staff_role, staff_admin, staff_passw, lang) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, username);
            ps.setString(2, role);
            ps.setBoolean(3, isAdmin);
            ps.setString(4, passw);
            ps.setString(5, lang);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the staff member exists in the database and verifies the password.
     *
     * @param object a map containing username and password
     * @return true if authentication is successful, false otherwise
     */
    public boolean checkLogin(Map<String, Object> object) {
        String username = (String) object.get(USERNAME);
        String password = (String) object.get(PASSWORD);

        Connection connection = DbConnection.getConnection();
        String sql = "SELECT staff_passw FROM STAFF WHERE staff_name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String dbpass = rs.getString(STAFFPASSWORD);
                boolean correctOrNot = cryptoUtils.verify(password, dbpass);
                if (correctOrNot){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves the language preference for a staff member.
     *
     * @param data a map containing the username
     * @return a list containing the staff id and language, or an empty list if not found
     */
    public List<String> checkLang(Map<String, Object> data) {
        ArrayList<String> object = new ArrayList<>();
        String username = (String) data.get(USERNAME);
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, lang FROM STAFF WHERE staff_name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                object.add(Integer.toString(rs.getInt(ID)));
                object.add(rs.getString(LANGUAGE));
                return object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (object.isEmpty()) {
            return new ArrayList<>();
        } else {
            return object;
        }
    }

    /**
     * Updates staff data in the STAFF table.
     * Only non-empty fields are updated.
     *
     * @param object a map containing staff data fields to update
     */
    public void updateData(Map<String, Object> object) {
        int id = (int) object.get(ID);
        String name = (String) object.get(STAFFNAME);
        String role = (String) object.get(STAFFROLE);
        String passw = (String) object.get(PASSWORD);
        boolean admin = (boolean) object.get(STAFFADMIN);
        String lang = (String) object.get(LANGUAGE);

        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE STAFF SET " +
                "staff_name = COALESCE(NULLIF(?,''), staff_name), " +
                "staff_role = COALESCE(NULLIF(?,''), staff_role), " +
                "staff_admin = COALESCE(?, staff_admin), " +
                "staff_passw = COALESCE(NULLIF(?,''), staff_passw), " +
                "lang = COALESCE(NULLIF(?,''), lang) " +
                "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, name);
            ps.setString(2, role);
            ps.setBoolean(3, admin);
            ps.setString(4, passw);
            ps.setString(5, lang);
            ps.setInt(6, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a staff member from the STAFF table based on a specified label and value.
     *
     * @param object a map containing the label and value for deletion
     */
    public void removeData(Map<String, Object> object) {
        int value = (int) object.get(VALUE);
        String label = (String) object.get(LABEL);

        Connection connection = DbConnection.getConnection();
        String sql = "DELETE FROM STAFF WHERE " + label + "  =  " + value;

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}