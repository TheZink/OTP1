package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.attendace.Utils.UserUtils;
import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class Dao_staff extends Handler {

    // This method check if handler can process the request

    @Override
    public boolean canProcess(Request request) {
        // If handler cannot process this request, set next handler and return false
        if (request.getDao() != RequestDao.STAFF) {
            setNextHandler(new Dao_course());
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

        else if (request.getType() == RequestType.SIGNIN){
            return checkLogin(object);
        }

        else if (request.getType() == RequestType.REMOVEDATA){
            removeData(object);
        }

        return false;
    }

    // Fetch all staff from Staff-table.
    public ArrayList<ArrayList<String>> getAllData(){

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM STAFF ORDER BY id ASC";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                // Encapsulate each row in its own array and add to the main data list
                ArrayList<String> row = new ArrayList<>();

                row.add(Integer.toString(rs.getInt("id")));
                row.add(rs.getString("staff_name"));
                row.add(rs.getString("staff_role"));
                row.add(Boolean.toString(rs.getBoolean("staff_admin")));
                row.add(rs.getTime("created_at").toString());

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

    // Fetch specific user from Staff-table
    public ArrayList<String> getData(Map<String, Object> object){

        String username = (String) object.get("username");

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM STAFF WHERE staff_name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("staff_name"));
                data.add(rs.getString("staff_role"));
                data.add(Boolean.toString(rs.getBoolean("staff_admin")));
                data.add(rs.getString("staff_passw"));
                data.add(rs.getTime("created_at").toString());
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
    
    // Set staff data to the Staff-table
    public void setData(Map<String, Object> data){

        // Before setting staff data to the database, check the username
        UserUtils user = new UserUtils();
        Map<String, Object> object = user.checkStaff(data);

        String username = (String) object.get("username");
        String role = (String) object.get("role");
        boolean isAdmin = (boolean) object.get("isAdmin");
        String passw = (String) object.get("password");

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO STAFF (staff_name, staff_role, staff_admin, staff_passw) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, role);
            ps.setBoolean(3, isAdmin);
            ps.setString(4, passw);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(Map<String, Object> object){
        String username = (String) object.get("username");
        String password = (String) object.get("password");

        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM STAFF WHERE staff_name = ?";
        
        // This checks if the user exists in the database
               
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            // This checks if the password correct

            if (rs.next()){
                if (rs.getString("staff_passw").equals(password)){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void removeData(Map<String, Object> object) {

        int value = (int) object.get("value");
        String label = (String) object.get("label");

        Connection connection = DbConnection.getConnection();
        String sql = "DELETE FROM STAFF WHERE " + label + "  =  " + value;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeQuery();
            System.out.println(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
