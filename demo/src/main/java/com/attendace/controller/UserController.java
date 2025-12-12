package com.attendace.controller;

import com.attendace.utils.CryptoUtils;
import com.attendace.utils.LangUtils;
import com.attendace.utils.LoginUtils;

import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing user-related operations.
 * Handles user authentication, creation, update, retrieval, and removal.
 */
public class UserController {

    private DefaultHandler handler;
    private LoginUtils login;
    private LangUtils langUtils;
    private CryptoUtils crypto = new CryptoUtils();
    private Request request;
    private Map<String, Object> data;
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String STUDENTID = "user_student_id";
    private static final String DEGREE = "degree";
    private static final String LANGUAGE = "lang";

    /**
     * Constructs a new UserController and initializes utility classes and handler.
     */
    public UserController() {
        this.handler = new DefaultHandler();
        this.login = new LoginUtils();
        this.langUtils = new LangUtils();
    }

    /**
     * Authenticates a user with the provided username and password.
     * Sets the user's language preference after successful login.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the user type if authentication is successful
     */
    public String loginUser(String username, String password) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        data.put(PASSWORD, password);
        String userType = login.login(data);
        userLanguage(data, userType);
        return userType;
    }

    /**
     * Sets the user's language preference based on the provided data and user type.
     *
     * @param object a map containing user data
     * @param userType the type of the user
     */
    public void userLanguage(Map<String, Object> object, String userType){
        List<String> lang = langUtils.language(object, userType);
        String getLang = lang.get(lang.size() - 1);
        int id = Integer.parseInt(lang.get(lang.size() - 2));

        if (getLang == null || getLang.isEmpty() || getLang.equals("NULL")) {
            // String localLang = langUtils.getLanguage();
            // String setLang = langUtils.setLanguage(localLang);
            // updateUser("", id, "", "", setLang);
        } else {
            langUtils.setLanguage(getLang);
        }
    }

    /**
     * Creates a new user with the specified details.
     *
     * @param studentId the student ID of the user
     * @param name the username
     * @param password the user's password (will be hashed)
     * @param userDegree the user's degree
     * @param lang the user's language preference
     * @return true if the user was created successfully, false otherwise
     */
    public boolean createUser(int studentId, String name, String password, String userDegree, String lang) {
        data = new HashMap<>();
        data.put(USERNAME, name);
        data.put(STUDENTID, studentId);
        data.put(DEGREE, userDegree);
        data.put(PASSWORD, crypto.hash(password));
        data.put("lang", lang);
        request = new Request(RequestDao.USERS, RequestType.SETDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Updates an existing user's information.
     *
     * @param name the new username
     * @param id the user ID
     * @param degree the new degree
     * @param password the new password
     * @param language the new language preference
     * @return true if the user was updated successfully, false otherwise
     */
    public boolean updateUser(String name, int id, String degree, String password, String language) {
        data = new HashMap<>();
        data.put("name", name);
        data.put("id", id);
        data.put(DEGREE, degree);
        data.put(PASSWORD, password);
        data.put(LANGUAGE, language);

        request = new Request(RequestDao.USERS, RequestType.UPDATEDATA, data);
        return (boolean) handler.handle(request);
    }

    /**
     * Retrieves user information by username.
     *
     * @param username the username to search for
     * @return a list of user data fields
     */
    public List<String> getUser(String username) {
        data = new HashMap<>();
        data.put(USERNAME, username);
        request = new Request(RequestDao.USERS, RequestType.GETDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all user data fields
     */
    public List<String> getAllUsers() {
        data = new HashMap<>();
        request = new Request(RequestDao.USERS, RequestType.GETALLDATA, data);
        return (ArrayList<String>) handler.handle(request);
    }

    /**
     * Removes a user based on a specified value and label.
     *
     * @param value the value to match for removal
     * @param label the label (column) to match for removal
     * @return true if the user was removed successfully, false otherwise
     */
    public boolean removeUser(int value, String label) {
        data = new HashMap<>();
        data.put("value", value);
        data.put("label", label);
        request = new Request(RequestDao.USERS, RequestType.REMOVEDATA, data);
        return (boolean) handler.handle(request);
    }
}