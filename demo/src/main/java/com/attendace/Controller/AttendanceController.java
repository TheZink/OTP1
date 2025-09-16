package com.attendace.Controller;

import com.attendace.Model.AttendanceModel;

public class AttendanceController {

    private AttendanceModel attendanceModel;


    //add dao later
    public AttendanceController() {

    }

    public void addAttendance(int courseId, int userId, int staffId, int currentAttendance) {
        attendanceModel = new AttendanceModel(courseId, userId, staffId, currentAttendance);
        System.out.println("attendance created");

    }
    public AttendanceModel getAttendance() {
        return attendanceModel;
    }
}
