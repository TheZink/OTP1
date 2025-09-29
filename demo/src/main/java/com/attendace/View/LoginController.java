package com.attendace.View;

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

public class LoginController extends Application {
    MainPageController mainPageController = new MainPageController();

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
        String email = emailfield.getText();
        String password = passwordfield.getText();

        emailfield.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/ProjectInterface.fxml"))
        );
        Stage stage = new Stage();
        stage.setTitle("Interface");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        mainPageController.initialize();
    }
}
