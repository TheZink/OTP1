package com.attendace.Engine;

import com.attendace.Controller.CourseController;
import com.attendace.Controller.UserController;
import com.attendace.Model.UserModel;
import com.attendace.View.MainInterfaceController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainEngine {
    MainInterfaceController mainInterfaceController;
    CourseController courseController;
    UserController userController;

    public MainEngine() {

    }

    public void runEngine(String username) throws IOException {
        courseController = new CourseController();
        userController = new UserController();

        ArrayList<String> fetcheduser = userController.getUser(username);

        if (fetcheduser.size() == 0) {
            throw new IOException("User not found");
        }

        UserModel user = new UserModel(
                Integer.parseInt(fetcheduser.get(2)),
                fetcheduser.get(1),
                fetcheduser.get(3)
        );

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

            try {
                mainInterfaceController.fillcourses(courses);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            mainInterfaceController.fillprofiledata(user);
        });
    }
}
