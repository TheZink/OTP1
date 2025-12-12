package com.attendace.model;

/**
 * Represents an attendance record for a user in a course, including course ID, user ID, staff ID,
 * attendance status, and the current attendance count.
 */
public class AttendanceModel {

    /** The unique identifier for the course. */
    private int courseId;

    /** The unique identifier for the user. */
    private int userId;

    /** The unique identifier for the staff member. */
    private int staffId;

    /** The attendance status (true if present, false otherwise). */
    private boolean attendanceStatus;

    /** The current attendance count for the user in the course. */
    private int currentAttendance;

    /**
     * Constructs a new AttendanceModel with the specified course ID, user ID, staff ID, and current attendance.
     *
     * @param courseId          the unique identifier for the course
     * @param userId            the unique identifier for the user
     * @param staffId           the unique identifier for the staff member
     * @param currentAttendance the current attendance count
     */
    public AttendanceModel(int courseId, int userId, int staffId, int currentAttendance) {
        this.courseId = courseId;
        this.userId = userId;
        this.staffId = staffId;
        this.currentAttendance = currentAttendance;
    }

    /**
     * Gets the unique identifier for the course.
     *
     * @return the course ID
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the unique identifier for the staff member.
     *
     * @return the staff ID
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * Gets the attendance status.
     *
     * @return true if the user is present, false otherwise
     */
    public boolean getAttendanceStatus() {
        return attendanceStatus;
    }

    /**
     * Gets the current attendance count for the user in the course.
     *
     * @return the current attendance count
     */
    public int getCurrentAttendance() {
        return currentAttendance;
    }

    /**
     * Sets the attendance status.
     *
     * @param set true if the user is present, false otherwise
     */
    public void setAttendanceStatus(boolean set) {
        attendanceStatus = set;
    }

    /**
     * Sets the current attendance count for the user in the course.
     *
     * @param set the new attendance count
     */
    public void setCurrentAttendance(int set) {
        currentAttendance = set;
    }
}