package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.attendace.utils.UserUtils;
import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class Dao_user extends Handler {

// This method check if handler can process the request

    @Override
    public boolean canProcess(Request request) {
        // If handler cannot process this request, set next handler and return false
        if (request.getDao() != RequestDao.USERS) {
            setNextHandler(new Dao_staff());
            return false;
        } else {
            return true;
        }
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

        else if (request.getType() == RequestType.UPDATEDATA){
            updateData(data);
            return true;
        }

        else if (request.getType() == RequestType.REMOVEDATA){
            removeData(data);
        }

        return false;
    }
    
    // Fetch all users from Users-table.
    public ArrayList<ArrayList<String>> getAllData(){
     
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM USERS ORDER BY id ASC";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                // Encapsulate each row in its own array and add to the main data list
                ArrayList<String> row = new ArrayList<>(); 

                row.add(Integer.toString(rs.getInt("id")));
                row.add(rs.getString("user_name"));
                row.add(Integer.toString(rs.getInt("user_student_id")));
                row.add(rs.getString("user_degree"));
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

    // Fetch specific user from Users-table
    public ArrayList<String> getData(Map<String, Object> object){

        String username = (String) object.get("username");

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
                data.add(rs.getString("user_passw"));
                data.add(rs.getDate("created_at").toString());
            }

        } catch (SQLException e){
            e.printStackTrace();;
        }

        // This check the result of the query
        if(data.isEmpty()){
            return null;}
        else {
            return data;}   
    }

    // Set users data to the table
    public void setData(Map<String, Object> data){

        // Before setting user data to the database, check username
        UserUtils user = new UserUtils();
        Map<String, Object> object = user.checkUser(data);

        String username = (String) object.get("username");
        int studentId = (int) object.get("student_id");
        String degree = (String) object.get("degree");
        String passw = (String) object.get("password");
        
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



    public void updateData(Map<String, Object> object) {

        int id = (int) object.get("id");
        String name = (String) object.get("name");
        String degree = (String) object.get("degree");
        String passw = (String) object.get("password");


        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE USERS SET user_name = ?, user_student_id = ?, user_degree = ?, user_passw = COALESCE(NULLIF(?,''), user_passw) WHERE id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.setString(3, degree);
            ps.setString(4, passw);
            ps.setInt(5, id);
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
    
            // This checks if the password is correct
    
            if (rs.next()){
                if (rs.getString("user_passw").equals(password)){
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
        String sql = "DELETE FROM USERS WHERE " + label + "  =  " + value;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
