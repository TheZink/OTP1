package com.attendace.model;

/**
 * Represents a course in the system, including its name, topic, description, attendance requirements, and status flags.
 */
public class CourseModel {

    /** The name of the course. */
    private final String courseName;

    /** The topic of the course. */
    private final String courseTopic;

    /** The description of the course. */
    private final String courseDesc;

    /** The attendance key for the course. */
    private String attendanceKey;

    /** The minimum attendance required for the course. */
    private int minAttendance;

    /** The maximum attendance allowed for the course. */
    private int maxAttendance;

    /** Indicates if attendance is currently available for the course. */
    private Boolean attendanceAvailable;

    /** Indicates if the course is currently active. */
    private Boolean courseActive;

    /**
     * Constructs a new CourseModel with the specified details.
     *
     * @param courseName      the name of the course
     * @param courseTopic     the topic of the course
     * @param courseDesc      the description of the course
     * @param minAttendance   the minimum attendance required
     * @param maxAttendance   the maximum attendance allowed
     */
    public CourseModel(String courseName, String courseTopic, String courseDesc, int minAttendance, int maxAttendance) {
        this.courseName = courseName;
        this.courseTopic = courseTopic;
        this.courseDesc = courseDesc;
        this.minAttendance = minAttendance;
        this.maxAttendance = maxAttendance;
    }

    /**
     * Gets the name of the course.
     *
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets the topic of the course.
     *
     * @return the course topic
     */
    public String getCourseTopic() {
        return courseTopic;
    }

    /**
     * Gets the description of the course.
     *
     * @return the course description
     */
    public String getCourseDesc() {
        return courseDesc;
    }

    /**
     * Gets the minimum attendance required for the course.
     *
     * @return the minimum attendance
     */
    public int getMinAttendance() {
        return minAttendance;
    }

    /**
     * Gets the maximum attendance allowed for the course.
     *
     * @return the maximum attendance
     */
    public int getMaxAttendance() {
        return maxAttendance;
    }

    /**
     * Sets the attendance key for the course.
     *
     * @param key the attendance key to set
     */
    public void setKey(String key) {
        attendanceKey = key;
    }

    /**
     * Gets the attendance key for the course.
     *
     * @return the attendance key
     */
    public String getKey() {
        return attendanceKey;
    }

    /**
     * Sets whether the course is currently active.
     *
     * @param set true if the course is active, false otherwise
     */
    public void setCourseAvailable(Boolean set) {
        courseActive = set;
    }

    /**
     * Gets whether the course is currently active.
     *
     * @return true if the course is active, false otherwise
     */
    public Boolean getCourseAvailable() {
        return courseActive;
    }

    /**
     * Sets whether attendance is currently available for the course.
     *
     * @param set true if attendance is available, false otherwise
     */
    public void setAttendanceAvailable(Boolean set) {
        attendanceAvailable = set;
    }

    /**
     * Gets whether attendance is currently available for the course.
     *
     * @return true if attendance is available, false otherwise
     */
    public Boolean getAttendanceAvailable() {
        return attendanceAvailable;
    }
}