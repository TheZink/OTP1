package com.attendace.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system, including their ID, student ID, name, degree, language, and enrolled courses.
 */
public class UserModel {

    /** The unique identifier for the user. */
    private int id;

    /** The student ID associated with the user. */
    private int studentId;

    /** The name of the user. */
    private String name;

    /** The degree or academic level of the user. */
    private String userDegree;

    /** The list of courses the user is enrolled in. */
    private ArrayList<CourseModel> courses;

    /** The language preference of the user. */
    private String lang;

    /**
     * Constructs a new UserModel with the specified details.
     *
     * @param id         the unique identifier for the user
     * @param studentId  the student ID of the user
     * @param name       the name of the user
     * @param userDegree the degree or academic level of the user
     * @param lang       the language preference of the user
     */
    public UserModel(int id, int studentId, String name, String userDegree, String lang) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.userDegree = userDegree;
        this.courses = new ArrayList<>();
        this.lang = lang;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the student ID associated with the user.
     *
     * @return the student ID
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Gets the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the degree or academic level of the user.
     *
     * @return the user's degree
     */
    public String getUserDegree() {
        return userDegree;
    }

    /**
     * Adds a course to the user's list of enrolled courses.
     *
     * @param course the course to add
     */
    public void addCourse(CourseModel course) {
        courses.add(course);
    }

    /**
     * Gets the list of courses the user is enrolled in.
     *
     * @return a list of the user's courses
     */
    public List<CourseModel> getUserCourses() {
        return courses;
    }
}
