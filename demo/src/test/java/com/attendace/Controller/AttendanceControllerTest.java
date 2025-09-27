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
        attendanceController.addAttendance(3, 1, 1, true, 10);

    }
}
