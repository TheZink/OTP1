package com.attendace.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttendanceControllerTest {

    AttendanceController attendanceController;

    @BeforeEach
    void setup() {
        attendanceController = new AttendanceController();
    }
    @Test
    void addAttendance() {
        attendanceController.addAttendance(3, 1, 1, true, 10);

    }
}
