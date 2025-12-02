package com.attendace.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCourseControllerTest {

    UserCourseController userCourseController;

    @BeforeEach
    void setUp() {
        userCourseController = new UserCourseController();
    }

    @Test
    void createUserCourse() {
        assertTrue(userCourseController.createUserCourse(5, 2));

    }

    @Test
    void getUserCoursesById() {
        userCourseController.getUserCoursesById(5);
    }
}