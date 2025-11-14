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
        assertEquals("User" ,userController.loginUser("Jaakko", "testi"));
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
    @Test
    void updateUser() {
        assertTrue(userController.updateUser("Pekka Pouta", 1, "Tähtitiede", "Salasana12345"));
    }
}