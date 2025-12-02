package com.attendace.dao.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.attendace.utils.CryptoUtils;
import com.attendace.utils.UserUtils;
import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;

public class DaoUser extends Handler {
    private static final String ID = "id";
    private static final String STUDENTNAME = "user_name";
    private static final String STUDENTID = "user_student_id";
    private static final String STUDENTDEGREE = "user_degree";
    private static final String STUDENTPASSWORD = "user_passw";
    private static final String CREATEDAT = "created_at";
    private static final String LANGUAGE = "lang";

    private static final String VALUE = "value";
    private static final String LABEL = "label";
    private static final String USERNAME = "username";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String DEGREE = "degree";
    private static final CryptoUtils cryptoUtils = new CryptoUtils();


    // This method check if handler can process the request
    @Override
    public boolean canProcess(Request request) {
        // If handler cannot process this request, set next handler and return false
        if (request.getDao() != RequestDao.USERS) {
            setNextHandler(new DaoStaff());
            return false;
        } else {
            return true;
        }
    }

    // This method check, if handler can process type of request
    @Override
    public Object process(Request request){
        Map<String, Object> data = request.getData();

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
        else if (request.getType() == RequestType.LANGUAGE) {
            return checkLang(data);
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
    
    // Fetch all users from Users-table. Encapsulate each row in its own array and add to the main data list
    public List<ArrayList<String>> getAllData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, user_name, user_student_id, user_degree, created_at, lang FROM USERS ORDER BY id ASC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>(); 
                row.add(Integer.toString(rs.getInt(ID)));
                row.add(rs.getString(STUDENTNAME));
                row.add(Integer.toString(rs.getInt(STUDENTID)));
                row.add(rs.getString(STUDENTDEGREE));
                row.add(rs.getTime(CREATEDAT).toString());
                row.add(rs.getString(LANGUAGE));
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // This check the result of the query
        if(data.isEmpty()){
            return new ArrayList<>();
        } else {
            return data;}       
    }

    // Fetch specific user from Users-table
    public List<String> getData(Map<String, Object> object){

        String username = (String) object.get(USERNAME);

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, user_name, user_student_id, user_degree, user_passw, created_at, lang FROM USERS WHERE user_name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql))

        {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt(ID)));
                data.add(rs.getString(STUDENTNAME));
                data.add(Integer.toString(rs.getInt(STUDENTID)));
                data.add(rs.getString(STUDENTDEGREE));
                data.add(rs.getString(STUDENTPASSWORD));
                data.add(rs.getDate(CREATEDAT).toString());
                data.add(rs.getString(LANGUAGE));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        // This check the result of the query
        if(data.isEmpty()){
            return new ArrayList<>();
        } else {
            return data;
        }   
    }

    // Set users data to the table
    public void setData(Map<String, Object> data){

        // Before setting user data to the database, check username
        UserUtils user = new UserUtils();
        Map<String, Object> object = user.checkUser(data);

        String username = (String) object.get(USERNAME);
        int studentId = (int) object.get(STUDENTID);
        String degree = (String) object.get(DEGREE);
        String passw = (String) object.get(PASSWORD);
        String lang = (String) object.get(LANGUAGE);
        
        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO USERS (user_name, user_student_id, user_degree, user_passw, lang) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, username);
            ps.setInt(2, studentId);
            ps.setString(3, degree);
            ps.setString(4, passw);
            ps.setString(5, lang);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(Map<String, Object> object) {
        int id = (int) object.get(ID);
        String name = (String) object.get(NAME);
        String degree = (String) object.get(DEGREE);
        String passw = (String) object.get(PASSWORD);
        String lang = (String) object.get(LANGUAGE);


        Connection connection = DbConnection.getConnection();
        String sql = "UPDATE USERS SET " +
                    "user_name = COALESCE(NULLIF(?,''), user_name), " +
                    "user_student_id = COALESCE(?, user_student_id), " +
                    "user_degree = COALESCE(NULLIF(?,''), user_degree), " +
                    "user_passw = COALESCE(NULLIF(?,''), user_passw), " +
                    "lang = COALESCE(NULLIF(?,''), lang) " +
                    "WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.setString(3, degree);
            ps.setString(4, passw);
            ps.setString(5, lang);
            ps.setInt(6, id);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // This checks if the user exists in the database and checks if the password is correct
    public boolean checkLogin(Map<String, Object> data){
        String username = (String) data.get(USERNAME);
        String password = (String) data.get(PASSWORD);
    
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT user_passw FROM USERS WHERE user_name = ?";
        
        try(PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()){
                String dbpass = rs.getString(STUDENTPASSWORD);
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

    public List<String> checkLang(Map<String, Object> data){
        ArrayList<String> object = new ArrayList<>();
        String username = (String) data.get(USERNAME);
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT id, lang FROM USERS WHERE user_name = ?";
        
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

    public void removeData(Map<String, Object> object) {
        int value = (int) object.get(VALUE);
        String label = (String) object.get(LABEL);

        Connection connection = DbConnection.getConnection();
        String sql = "DELETE FROM USERS WHERE " + label + "  =  " + value;

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
