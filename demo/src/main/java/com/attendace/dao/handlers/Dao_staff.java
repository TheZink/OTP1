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
        Map<String, Object> data = request.getData(); //Fetch data from request

        if (request.getType() == RequestType.GETALLDATA){
            return getAllData();
        }

        else if (request.getType() ==RequestType.GETDATA){
            String username = (String) data.get("username");
            return getData(username);
        }

        else if (request.getType() == RequestType.SIGNIN){
            String username = (String) data.get("username");
            String password = (String) data.get("password");
            return checkLogin(username, password);
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

    public ArrayList<String> getData(String userName){

        // Fetch specific user from Staff-table
        // Method return row id, name, role, admin-boolean, password and timestamp

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM STAFF WHERE staff_name = ?";


        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
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
    
    public boolean checkLogin(String username, String password){
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM STAFF WHERE staff_name = ?";
        
        // This checks if the user exists in the database
               
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            // This checks if the password correct

            if (rs.next()){
                if (rs.getString("password") == password){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void setData(String name, String role, boolean admin, String passw){

        // Set staff data to the Staff-table

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO STAFF (staff_name, staff_role, staff_admin, staff_passw) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, role);
            ps.setBoolean(3, admin);
            ps.setString(4, passw);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
