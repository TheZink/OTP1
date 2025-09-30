package com.attendace.Controller;

import com.attendace.Model.CourseModel;
import com.attendace.dao.handlers.Dao_course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {

    private CourseController courseController;

    @BeforeEach
    void setup() {
        courseController = new CourseController();
    }

    @Test
    void createCourse() {

        assertTrue(courseController.createCourse("Testinimicourse",
                "testitipic",
                "testidesc",
                true,
                "erw",
                10,
                20,
                true));
    }
    @Test
    void updateCourse() {


    }
}