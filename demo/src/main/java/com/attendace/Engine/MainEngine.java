package com.attendace.Engine;

import com.attendace.Controller.CourseController;
import com.attendace.Controller.StaffController;
import com.attendace.Controller.UserController;
import com.attendace.Model.StaffModel;
import com.attendace.Model.UserModel;
import com.attendace.View.MainInterfaceController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.attendace.localisation.Translator;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainEngine {
    MainInterfaceController mainInterfaceController;
    CourseController courseController;
    UserController userController;
    StaffController staffController;

    public MainEngine() {

    }

    public void runEngine(String username, String status) throws IOException {
        courseController = new CourseController();
        userController = new UserController();
        staffController = new StaffController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectInterface.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle(Translator.getString("main.frontpageTitle"));
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        Platform.runLater(() -> {
            mainInterfaceController = loader.getController();
            mainInterfaceController.translatepage();

            if (status.equals("Staff")) {

                ArrayList<String> fetcheduser = staffController.getStaff(username);

                StaffModel user = new StaffModel(
                        Integer.parseInt(fetcheduser.get(0)),
                        fetcheduser.get(1),
                        fetcheduser.get(2),
                        fetcheduser.get(3)
                );
                mainInterfaceController.renderAdminButton(user);

                ArrayList<ArrayList<Object>> courses = courseController.getAllCourses();

                System.out.println("Olenko admin?"+ user.getAdminStatus());

                try {
                    mainInterfaceController.fillcourses(courses);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                mainInterfaceController.fillprofiledatastaff(user);

            } else if (status.equals("User")) {
                ArrayList<String> fetcheduser = userController.getUser(username);
                mainInterfaceController.disableAdminButton();

                UserModel user = new UserModel(
                        Integer.parseInt(fetcheduser.get(2)),
                        fetcheduser.get(1),
                        fetcheduser.get(3)
                );

                ArrayList<ArrayList<Object>> courses = courseController.getAllCourses();

                try {
                    mainInterfaceController.fillcourses(courses);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                mainInterfaceController.fillprofiledata(user);
            }
        });
    }
}
