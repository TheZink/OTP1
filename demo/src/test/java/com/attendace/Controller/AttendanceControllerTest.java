package com.attendace.Controller;

import com.attendace.Model.AttendanceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceControllerTest {

    AttendanceController attendanceController;

    @BeforeEach
    void setup() {
        attendanceController = new AttendanceController();
    }
    @Test
    void addAttendance() {
        attendanceController.addAttendance(1, 2, 3, 0);
        AttendanceModel attendance = attendanceController.getAttendance();
        assertEquals(1, attendance.getCourseId());
        assertEquals(2, attendance.getUserId());
        assertEquals(3, attendance.getStaffId());
        assertEquals(0, attendance.getCurrentAttendance());
    }
}
