package com.attendace.controller;

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
        assertEquals("User" ,userController.loginUser("Jukka Junitti", "12345"));
    }

    @Test
    void createUser() {
        assertTrue(userController.createUser(124,
                "useri",
                "lol",
                "insinööri", "fi"));
    }

    @Test
    void updateUser() {
        assertTrue(userController.updateUser("USER", 1, "Tähtitiede", "godmode", ""));
    }
}