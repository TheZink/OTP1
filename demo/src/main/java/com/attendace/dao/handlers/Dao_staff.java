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

public class Dao_staff extends Handler {

    // This method check if handler can process the request

    @Override
    public boolean canProcess(Request request){
        return request.getDao() == RequestDao.STAFF;
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

        return null;
    }

    public ArrayList<String> getAllData(){

        // Fetch all staff from Staff-table.
        // Method return row id, name, role, admin-boolean and timestamp
        
        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM STAFF ORDER BY id DESC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("staff_name"));
                data.add(rs.getString("staff_role"));
                data.add(Boolean.toString(rs.getBoolean("staff_admin")));
                data.add(rs.getDate("created_at").toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
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
                data.add(rs.getDate("created_at").toString());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return data;
    }
    
    // Set staff data to the Staff-table
    public void setData(Map<String, Object> object){

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
                if (rs.getString("password").equals(password)){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
