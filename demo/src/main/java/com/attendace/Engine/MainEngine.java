package com.attendace.Engine;

import com.attendace.Controller.CourseController;
import com.attendace.View.MainInterfaceController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import java.util.Objects;

public class MainEngine {
    MainInterfaceController mainInterfaceController;
    CourseController courseController;

    public MainEngine() {

    }

    public void runEngine() throws IOException {
        courseController = new CourseController();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectInterface.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Interface");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        Platform.runLater(() -> {
            mainInterfaceController = loader.getController();

            ArrayList<ArrayList<Object>> courses = courseController.getAllCourses();
            System.out.println("Hello World");

            for (ArrayList<Object> course : courses) {
                System.out.println(course.get(0));
            }

            try {
                mainInterfaceController.fillcourses(courses);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
