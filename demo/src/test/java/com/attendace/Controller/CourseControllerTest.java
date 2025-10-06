package com.attendace.Controller;

import com.attendace.Model.CourseModel;
import com.attendace.dao.handlers.Dao_course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    @Test
    void getAllCourses() {
        ArrayList x = courseController.getAllCourses();
        //ArrayList x1 = x.get(0);
        System.out.println(x.get(0));
        //System.out.println(courseController.getAllCourses());
    }
    @Test
    void getStaffsCourses() {
        System.out.println(courseController.getStaffsCourses(1));
    }
}