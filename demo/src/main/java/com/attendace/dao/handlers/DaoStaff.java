package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.attendace.utils.UserUtils;
import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class DaoStaff extends Handler {
    private static final String ID = "id";
    private static final String STAFFNAME = "staff_name";
    private static final String STAFFROLE = "staff_role";
    private static final String STAFFADMIN = "staff_admin";
    private static final String STAFFPASSWORD = "staff_passw";
    private static final String CREATEDAT = "created_at";

    private static final String VALUE = "value";
    private static final String LABEL = "label";
    private static final String USERNAME = "username";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String ISADMIN = "isAdmin";

    // This method check if handler can process the request
    @Override
    public boolean canProcess(Request request) {
        // If handler cannot process this request, set next handler and return false
        if (request.getDao() != RequestDao.STAFF) {
            setNextHandler(new DaoCourse());
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
            return true;
        }
        else if (request.getType() == RequestType.SIGNIN){
            return checkLogin(object);
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

    // Fetch all staff from Staff-table. Encapsulate each row in its own array and add to the main data list
    public List<ArrayList<String>> getAllData(){

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, staff_name, staff_role, staff_admin, created_at FROM STAFF ORDER BY id ASC";

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(rs.getString(STAFFNAME));
                row.add(rs.getString(STAFFROLE));
                row.add(Boolean.toString(rs.getBoolean(STAFFADMIN)));
                row.add(rs.getTime(CREATEDAT).toString());
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

    // Fetch specific user from Staff-table
    public List<String> getData(Map<String, Object> object){

        String username = (String) object.get(USERNAME);

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, staff_name, staff_role, staff_admin, staff_passw, created_at FROM STAFF WHERE staff_name = ?";

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
                }
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
    
    // Set staff data to the Staff-table
    public void setData(Map<String, Object> data){

        // Before setting user data to the database, check username
        UserUtils user = new UserUtils();
        Map<String, Object> object = user.checkStaff(data);

        String username = (String) object.get(USERNAME);
        String role = (String) object.get(ROLE);
        boolean isAdmin = (boolean) object.get(ISADMIN);
        String passw = (String) object.get(PASSWORD);

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO STAFF (staff_name, staff_role, staff_admin, staff_passw) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, username);
            ps.setString(2, role);
            ps.setBoolean(3, isAdmin);
            ps.setString(4, passw);
            ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This checks if the user exists in the database and checks if the password correct
    public boolean checkLogin(Map<String, Object> object){
        String username = (String) object.get(USERNAME);
        String password = (String) object.get(PASSWORD);

        Connection connection = DbConnection.getConnection();
        String sql = "SELECT staff_passw FROM STAFF WHERE staff_name = ?";
                       
        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String dbpass = rs.getString(STAFFPASSWORD);

                if (dbpass != null && dbpass.equals(password)){
                    return true;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updateData(Map<String, Object> object) {
        int id = (int) object.get(ID);
        String name = (String) object.get(NAME);
        String role = (String) object.get(ROLE);
        String passw = (String) object.get(PASSWORD);
        boolean admin = (boolean) object.get(ISADMIN);

        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE STAFF SET staff_name = ?, staff_role = ?, staff_admin = ?, staff_passw = COALESCE(NULLIF(?,''), staff_passw) WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, name);
            ps.setString(2, role);
            ps.setBoolean(3, admin);
            ps.setString(4, passw);
            ps.setInt(5, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
