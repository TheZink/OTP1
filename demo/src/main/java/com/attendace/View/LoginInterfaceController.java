package com.attendace.View;

import com.attendace.Controller.UserController;
import com.attendace.Engine.MainEngine;
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
import com.attendace.localisation.Translator;

public class LoginInterfaceController extends Application {
    //MainInterfaceController mainInterfaceController = new MainInterfaceController();
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

    public void AttemptLogin() throws IOException {
        String username = emailfield.getText();
        String password = passwordfield.getText();

        String status = userController.loginUser(username, password);
        System.out.println(status);

        if (!status.equals("false")) {
            emailfield.getScene().getWindow().hide();
            engine.runEngine(username, status);
        } else {
            System.out.println("Failed to login");
        }
    }
}
