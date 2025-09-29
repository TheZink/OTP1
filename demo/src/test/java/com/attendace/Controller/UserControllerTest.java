package com.attendace.Controller;

import com.sun.jna.platform.win32.PsapiUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController();
    }

    @Test
    void loginUser() {
        assertTrue(userController.loginUser("Jaakko", "testi"));
    }

    @Test
    void createUser() {
        assertTrue(userController.createUser(124,
                "testinimi",
                "lol",
                "insinööri"));
    }

    @Test
    void getUser() {

    }
    @Test
    void getAllUsers() {
        System.out.println(userController.getAllUsers());

    }
}