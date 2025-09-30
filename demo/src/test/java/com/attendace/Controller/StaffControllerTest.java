package com.attendace.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffControllerTest {
    StaffController staffController;

    @BeforeEach
    void setUp() {
        staffController = new StaffController();
    }

    @Test
    void createStaff() {
        assertTrue(staffController.createStaff("jerno", "rehtori", true, "godmode"));
    }

}