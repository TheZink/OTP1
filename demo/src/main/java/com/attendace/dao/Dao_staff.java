package com.attendace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.attendace.datasource.DbConnection;

public class Dao_staff {

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
            e.printStackTrace();;
        }
        
        return data;
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
