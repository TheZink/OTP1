package com.attendace.model;

public class CourseModel {

    private final String courseName;
    private final String courseTopic;
    private final String courseDesc;
    private String attendanceKey;
    private int minAttendance;
    private int maxAttendance;


    private Boolean attendanceAvailable;
    private Boolean courseActive;

    public CourseModel(String courseName, String courseTopic, String courseDesc, int minAttendance, int maxAttendance) {
        this.courseName = courseName;
        this.courseTopic = courseTopic;
        this.courseDesc = courseDesc;
        this.minAttendance = minAttendance;
        this.maxAttendance = maxAttendance;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCourseTopic() {
        return courseTopic;
    }
    public String getCourseDesc() {
        return courseDesc;
    }
    public int getMinAttendance() {
        return minAttendance;
    }
    public int getMaxAttendance() {
        return maxAttendance;
    }
    public void setKey(String key) {
        attendanceKey = key;
    }
    public String getKey() {
        return attendanceKey;
    }
    public void setCourseAvailable(Boolean set) {
        courseActive = set;
    }
    public Boolean getCourseAvailable() {
        return courseActive;
    }
    public void setAttendanceAvailable(Boolean set) {
        attendanceAvailable = set;
    }
    public Boolean getAttendanceAvailable() {
        return attendanceAvailable;
    }
}
