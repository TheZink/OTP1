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

public class Dao_user extends Handler {

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
            return getData(data);
        }

        else if (request.getType() == RequestType.SETDATA){
            setData(data);
            return true;
        }

        else if (request.getType() == RequestType.SIGNIN){
            return checkLogin(data);
        }

        return null;
    }
    
    // Fetch all users from Users-table.
    public ArrayList<String> getAllData(){
     
        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM USERS ORDER BY id DESC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("user_name"));
                data.add(Integer.toString(rs.getInt("user_student_id")));
                data.add(rs.getString("user_degree"));
                data.add(rs.getDate("pvm").toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    // Fetch specific user from Users-table
    public ArrayList<String> getData(Map<String, Object> object){

        String username = (String) object.get("user_name");

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM USERS WHERE user_name = ?";


        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("user_name"));
                data.add(Integer.toString(rs.getInt("user_student_id")));
                data.add(rs.getString("user_degree"));
                data.add(rs.getString("user_password"));
                data.add(rs.getDate("created_at").toString());
            }

            
        } catch (SQLException e){
            e.printStackTrace();;
        }
        
        return data;
    }

    
    // Set users data to the table
    public void setData(Map<String, Object> object){
        
        String username = (String) object.get("user_name");
        int studentId = (int) object.get("user_student_id");
        String degree = (String) object.get("user_degree");
        String passw = (String) object.get("user_passw");
        
        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO USERS (user_name, user_student_id, user_degree, user_passw) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, studentId);
            ps.setString(3, degree);
            ps.setString(4, passw);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateData(String name, int id, String degree, String passw) {
        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE USERS SET user_name = ?, user_student_id = ?, user_degree = ?, user_passw = ? WHERE id = ?";
        
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.setString(3, degree);
            ps.setString(4, passw);
            ps.setInt(5, id); // id for WHERE clause
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(Map<String, Object> data){
    
        String username = (String) data.get("username");
        String password = (String) data.get("password");
    
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM USERS WHERE user_name = ?";
        
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
}
