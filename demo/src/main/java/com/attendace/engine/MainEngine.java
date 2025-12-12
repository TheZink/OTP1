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

/**
 * Main engine class responsible for initializing controllers, loading the main interface,
 * and handling user or staff session logic.
 */
public class MainEngine {
    /** Controller for the main interface. */
    MainInterfaceController mainInterfaceController;

    /** Controller for course-related operations. */
    CourseController courseController;

    /** Controller for user-related operations. */
    UserController userController;

    /** Controller for staff-related operations. */
    StaffController staffController;

    /** Controller for user-course relationships. */
    UserCourseController userCourseController;

    /**
     * Constructs a new MainEngine instance.
     * Required empty constructor.
     */
    public MainEngine() {
        // Required empty constructor
    }

    /**
     * Runs the main engine logic, initializes controllers, loads the main interface,
     * and sets up the session for the given user or staff.
     *
     * @param username the username of the logged-in user or staff
     * @param status   the status indicating "Staff" or "User"
     * @throws IOException if the FXML file cannot be loaded
     */
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

                System.out.println(fetcheduser);
                StaffModel user = new StaffModel(
                        Integer.parseInt(fetcheduser.get(0)),
                        fetcheduser.get(1),
                        fetcheduser.get(2),
                        Boolean.parseBoolean(fetcheduser.get(3)),
                        fetcheduser.get(6)
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
                        fetcheduser.get(3),
                        fetcheduser.get(6)
                );

                List<ArrayList<Object>> courses = courseController.getAllCourses();
                List<CourseModel> attendingCourses = userCourseController.getUserCoursesById(user.getId());

                for(CourseModel c : attendingCourses) {
                    user.addCourse(c);
                }
                mainInterfaceController.fillParticipatingCourses(user);

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