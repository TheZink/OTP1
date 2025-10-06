package com.attendace.View;

import com.attendace.Controller.UserController;
import com.attendace.Engine.MainEngine;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginInterfaceController extends Application {
    //MainInterfaceController mainInterfaceController = new MainInterfaceController();
    MainEngine engine = new MainEngine();
    UserController userController = new UserController();

    @FXML
    private TextField emailfield;

    @FXML
    private PasswordField passwordfield;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/ProjectLoginPage.fxml"))
        );
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void AttemptLogin() throws IOException {
        String username = emailfield.getText();
        String password = passwordfield.getText();

        if (userController.loginUser(username, password)) {
            emailfield.getScene().getWindow().hide();
            engine.runEngine(username);
        } else {
            System.out.println("Failed to login");
        }
    }
}
