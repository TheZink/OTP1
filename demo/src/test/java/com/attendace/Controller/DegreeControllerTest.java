package com.attendace.Controller;

import com.attendace.Model.DegreeModel;
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
        degreeController.addDegree("Software engineering", 240);
        DegreeModel degree = degreeController.getDegree();

        assertEquals("Software engineering", degree.getName());
        assertEquals(240, degree.getEcts());
    }
}