package com.attendace.controller;

import com.attendace.utils.LoginUtils;
import com.attendace.utils.CryptoUtils;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing staff-related operations.
 * Handles staff authentication, creation, update, retrieval, and removal.
 */
public class StaffController {
    private DefaultHandler handler;
    private LoginUtils login;
    private final CryptoUtils crypto = new CryptoUtils();
    private Request request;
    private Map<String, Object> data;
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    /**
     * Constructs a new StaffController and initializes utility classes and handler.
     */
    public StaffController() {
        this.handler = new DefaultHandler();
        this.login = new LoginUtils();
    }

    /**
     * Authenticates a staff member with the provided username and password.
     *
     * @param username the username of the staff member
     * @param password the password of the staff member
     * @return the staff type if authentication is successful
     */
    public String loginStaff(String username, String password) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        data.put(PASSWORD, password);
        request = new Request(RequestDao.STAFF, RequestType.SIGNIN, data);
        return login.login(data);
    }

    /**
     * Creates a new staff member with the specified details.
     *
     * @param username the username of the staff member
     * @param role the role of the staff member
     * @param isAdmin whether the staff member is an admin
     * @param password the password of the staff member (will be hashed)
     * @param lang the language preference of the staff member
     * @return true if the staff member was created successfully, false otherwise
     */
    public boolean createStaff(String username, String role, boolean isAdmin, String password, String lang) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        data.put("role", role);
        data.put("isAdmin", isAdmin);
        data.put(PASSWORD, crypto.hash(password));
        data.put("lang", lang);
        request = new Request(RequestDao.STAFF, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Retrieves staff information by username.
     *
     * @param username the username to search for
     * @return a list of staff data fields
     */
    public List<String> getStaff(String username) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        request = new Request(RequestDao.STAFF, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Updates an existing staff member's information.
     *
     * @param id the staff member's ID
     * @param name the new name
     * @param role the new role
     * @param password the new password
     * @param isAdmin whether the staff member is an admin
     * @return true if the staff member was updated successfully, false otherwise
     */
    public boolean updateStaff(int id, String name, String role, String password, boolean isAdmin) {
        data = new HashMap<>();
        data.put("id", id);
        data.put("staff_name", name);
        data.put("staff_role", role);
        data.put(PASSWORD, password);
        data.put("staff_admin", isAdmin);

        request = new Request(RequestDao.STAFF, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Retrieves all staff members.
     *
     * @return a list of all staff data fields
     */
    public List<String> getAllStaff() {
        data = new HashMap<>();
        request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Removes a staff member based on a specified value and label.
     *
     * @param value the value to match for removal
     * @param label the label (column) to match for removal
     * @return true if the staff member was removed successfully, false otherwise
     */
    public boolean removeStaff(int value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.STAFF, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }
}