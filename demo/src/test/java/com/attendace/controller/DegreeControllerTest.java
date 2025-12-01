package com.attendace.controller;

import com.attendace.model.DegreeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeControllerTest {
    private DegreeController degreeController;

    @BeforeEach
    void setup() {
        degreeController = new DegreeController();
    }

    @Test
    void addDegree() {
        assertFalse(degreeController.createDegree("Software engineering", 240));
    }
}