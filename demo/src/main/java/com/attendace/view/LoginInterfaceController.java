package com.attendace.view;

import com.attendace.controller.UserController;
import com.attendace.engine.MainEngine;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import com.attendace.localisation.Translator;

public class LoginInterfaceController extends Application {
    Logger log = Logger.getLogger(LoginInterfaceController.class.getName());
    MainEngine engine = new MainEngine();
    UserController userController = new UserController();

    @FXML
    private TextField emailfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Text usernametext;

    @FXML
    private Text passwordtext;

    @FXML
    private Text failedtext;

    @FXML
    private Button loginbutton;

    @FXML
    private MenuButton languagemenu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/ProjectLoginPage.fxml"))
        );
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void translatepage() {
        usernametext.setText(Translator.getString("login.username"));
        passwordtext.setText(Translator.getString("login.password"));
        failedtext.setText(Translator.getString("login.failed"));
        loginbutton.setText(Translator.getString("login.button"));
        languagemenu.setText(Translator.getString("login.languagemenu"));

        ObservableList<MenuItem> items  = languagemenu.getItems();
        for (MenuItem item : items) {
            item.setText(Translator.getString("login." + item.getId()));
        }

        if (Translator.getLocale().toString().equals("fa_IR")) {
            emailfield.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);
            passwordfield.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);

            usernametext.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
            passwordtext.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
            failedtext.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        } else {
            emailfield.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
            passwordfield.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);

            usernametext.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            passwordtext.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            failedtext.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        }
    }

    @FXML
    public void langeng() {
        Translator.setLocale("en", "US");
        translatepage();
    }

    @FXML
    public void langfin() {
        Translator.setLocale("fi", "FI");
        translatepage();
    }
    @FXML
    public void langjp() {
        Translator.setLocale("ja", "JP");
        translatepage();
    }

    @FXML
    public void langir() {
        Translator.setLocale("fa", "IR");
        translatepage();
    }
    public void attemptLogin() throws IOException {
        String username = emailfield.getText();
        String password = passwordfield.getText();

        String status = userController.loginUser(username, password);
        log.info(status);

        if (!status.equals("false")) {
            emailfield.getScene().getWindow().hide();
            engine.runEngine(username, status);
        } else {
            log.info("Failed to login");
        }
    }
}
