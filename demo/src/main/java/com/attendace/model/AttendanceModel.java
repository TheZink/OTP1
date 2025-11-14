package com.attendace.model;

public class AttendanceModel {
    private int courseId;
    private int userId;
    private int staffId;
    private boolean attendanceStatus;
    private int currentAttendance;

    public AttendanceModel(int courseId, int userId, int staffId, int currentAttendance) {
        this.courseId = courseId;
        this.userId = userId;
        this.staffId = staffId;
        this.currentAttendance = currentAttendance;
    }
    public int getCourseId() {
        return courseId;
    }
    public int getUserId() {
        return userId;
    }
    public int getStaffId() {
        return staffId;
    }
    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }
    public int getCurrentAttendance() {
        return currentAttendance;
    }
    public void setAttendanceStatus(boolean set) {
        attendanceStatus = set;
    }
    public void setCurrentAttendance(int set) {
        currentAttendance = set;
    }
}
