package com.attendace.Controller;

import com.attendace.Model.CourseModel;
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
        courseController.createCourse("OTP1", "Software engineering", "Programming", 10, 20);
        CourseModel course = courseController.getCourse();
        assertEquals("OTP1", course.getCourseName());
        assertEquals("Software engineering", course.getCourseTopic());
        assertEquals("Programming", course.getCourseDesc());
        assertEquals(10, course.getMinAttendance());
        assertEquals(20, course.getMaxAttendance());
    }
}