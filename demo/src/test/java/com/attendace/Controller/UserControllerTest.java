package com.attendace.Controller;

import com.attendace.Model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;
    private UserModel userModel;

    @BeforeEach
    void setup() {
        userController = new UserController();


    }

    @Test
    void createUser() {
         userController.createUser(123, "nimi", "salasana", "engineer");
         UserModel user = userController.getUser();


         assertEquals(123, user.getStudentId());
         assertEquals("nimi", user.getName());
         assertEquals("engineer", user.getUserDegree());

    }
}