package com.attendace.Engine;

import com.attendace.View.MainInterfaceController;
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

import java.util.Objects;

public class MainEngine {
    MainInterfaceController mainInterfaceController = new MainInterfaceController();


    public MainEngine() {

    }

    public void runEngine() throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/ProjectInterface.fxml"))
        );

        Stage stage = new Stage();
        stage.setTitle("Interface");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        mainInterfaceController.initialize();
    }
}
