package com.attendace.engine;

import com.attendace.controller.CourseController;
import com.attendace.controller.StaffController;
import com.attendace.controller.UserController;
import com.attendace.controller.UserCourseController;
import com.attendace.model.CourseModel;
import com.attendace.model.StaffModel;
import com.attendace.model.UserModel;
import com.attendace.view.MainInterfaceController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.attendace.localisation.Translator;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainEngine {
    MainInterfaceController mainInterfaceController;
    CourseController courseController;
    UserController userController;
    StaffController staffController;
    UserCourseController userCourseController;

    public MainEngine() {
        //Required empty constructor
    }

    public void runEngine(String username, String status) throws IOException {
        courseController = new CourseController();
        userController = new UserController();
        staffController = new StaffController();
        userCourseController = new UserCourseController();

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

                List<String> fetcheduser = staffController.getStaff(username);

                StaffModel user = new StaffModel(
                        Integer.parseInt(fetcheduser.get(0)),
                        fetcheduser.get(1),
                        fetcheduser.get(2),
                        Boolean.parseBoolean(fetcheduser.get(3))
                );
                mainInterfaceController.checkAdminStatus(user);
                List<ArrayList<Object>> courses = courseController.getAllCourses();

                try {
                    mainInterfaceController.fillcourses(courses);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                mainInterfaceController.fillprofiledatastaff(user);

            } else if (status.equals("User")) {
                List<String> fetcheduser = userController.getUser(username);
                mainInterfaceController.disableAdminButton();

                UserModel user = new UserModel(
                        Integer.parseInt(fetcheduser.get(0)),
                        Integer.parseInt(fetcheduser.get(2)),
                        fetcheduser.get(1),
                        fetcheduser.get(3)
                );

                List<ArrayList<Object>> courses = courseController.getAllCourses();
                List<CourseModel> attendingCourses = userCourseController.getUserCoursesById(user.getId());

                for(CourseModel c : attendingCourses) {
                    user.addCourse(c);
                }

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
