package com.attendace.Controller;

import com.attendace.Model.UserModel;
import com.attendace.dao.handlers.Dao_user;
import com.attendace.dao.handlers.DefaultHandler;
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
    void loginUser() {
        userController.loginUser("Jukka Junitti", "Salasana12345");

    }

    @Test
    void createUser() {
         userController.createUser(123, "Jaakko", "testi", "insinööri");

    }
}