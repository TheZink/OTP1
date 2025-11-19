package com.attendace.controller;

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


    @Test
    void updateStaff() {
        assertTrue(staffController.updateStaff( 1, "Olli Opettaja", "opettaja", "salis", false));
    }

}