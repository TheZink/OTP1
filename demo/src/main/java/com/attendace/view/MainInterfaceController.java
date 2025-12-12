package com.attendace.view;

import com.attendace.model.CourseModel;
import com.attendace.model.StaffModel;
import com.attendace.model.UserModel;
import com.attendace.view.Classes.CourseContainer;
import com.attendace.localisation.Translator;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.text.TextAlignment.RIGHT;

/**
 * Controller for the main user interface.
 * Handles profile and course views, localization, admin access, and dynamic UI updates.
 */
public class MainInterfaceController {
    /** Controller for the admin interface. */
    private AdminInterfaceController admininterfaceController;

    /** Indicates if the current user has admin privileges. */
    private boolean admin;

    @FXML
    Text softwareTitle;

    @FXML
    private GridPane coursegrid;

    @FXML
    private Button profilebutton;

    @FXML
    private Button coursesbutton;

    @FXML
    private GridPane profiletab;

    @FXML
    private ScrollPane coursestab;

    @FXML
    private Text usernamelabel;

    @FXML
    private Text degreelabel;

    @FXML
    private Text rolelabel;

    @FXML
    private Text statuslabel;

    @FXML
    private Text notEnrolled;

    @FXML
    private GridPane participatingcoursesgrid;

    @FXML
    private Text participatingIn;

    @FXML
    private Text studentidlabel;

    @FXML
    private Button adminpanel;

    /**
     * Translates the UI elements based on the current locale.
     */
    public void translatepage() {
        softwareTitle.setText(Translator.getString("main.softwareTitle"));
        profilebutton.setText(Translator.getString("main.profileButton"));
        coursesbutton.setText(Translator.getString("main.coursesButton"));
        adminpanel.setText(Translator.getString("main.adminButton"));

        statuslabel.setText(Translator.getString("student"));
        rolelabel.setText(Translator.getString("profile.roleLabel"));
        participatingIn.setText(Translator.getString("profile.participatingIn"));

        if (Translator.getLocale().toString().equals("fa_IR")) {
            statuslabel.setTextAlignment(RIGHT);
            rolelabel.setTextAlignment(RIGHT);
            participatingIn.setTextAlignment(RIGHT);
            studentidlabel.setTextAlignment(RIGHT);
            usernamelabel.setTextAlignment(RIGHT);
            degreelabel.setTextAlignment(RIGHT);

            participatingcoursesgrid.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);
        } else {
            participatingcoursesgrid.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);

            statuslabel.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            rolelabel.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            participatingIn.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            studentidlabel.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            usernamelabel.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            degreelabel.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        }
    }

    /**
     * Checks if the given staff member has admin status and updates the UI accordingly.
     * @param staff the staff model to check
     */
    public void checkAdminStatus(StaffModel staff) {
        if(staff.getAdminStatus()) {
            enableAdminButton();
            admin = true;

        } else if(!staff.getAdminStatus()) {
            disableAdminButton();
            admin = false;
        }
    }

    /**
     * Fills the participating courses grid for the given user.
     * @param user the user whose courses to display
     */
    public void fillParticipatingCourses(UserModel user) {

        //profile.userCoursesEmpty
        if(user.getUserCourses().isEmpty()) {
            notEnrolled.setText(Translator.getString("profile.userCoursesEmpty"));

        } else {
            ListView<String> list = new ListView();
            participatingcoursesgrid.add(list, 0, 0);
            for(CourseModel course : user.getUserCourses()) {
                String courseName =  course.getCourseName();
                list.getItems().add(courseName);

        }


        }
    }

    /**
     * Hides the admin panel button.
     */
    public void disableAdminButton() {
        adminpanel.setVisible(false);
    }

    /**
     * Shows the admin panel button.
     */
    public void enableAdminButton() {
        adminpanel.setVisible(true);
    }

    /**
     * Returns whether the current user has admin privileges.
     * @return true if admin, false otherwise
     */
    public boolean getAdminStatus() {
        return admin;
    }

    /**
     * Shows the profile tab with a fade transition.
     */
    public void showprofile() {
        profilebutton.setDisable(true);
        coursesbutton.setDisable(true);

        profiletab.setVisible(true);

        FadeTransition fadecourses = new FadeTransition(Duration.millis(200), coursestab);
        fadecourses.setToValue(0.0);

        FadeTransition fadeprofile = new FadeTransition(Duration.millis(500), profiletab);
        fadeprofile.setToValue(1.0);


        fadeprofile.setOnFinished(e -> {
            profilebutton.setDisable(false);
            coursesbutton.setDisable(false);
            coursestab.setVisible(false);
        });

        fadeprofile.play();
        fadecourses.play();
    }

    /**
     * Shows the courses tab with a fade transition.
     */
    public void showcourses() {
        profilebutton.setDisable(true);
        coursesbutton.setDisable(true);

        coursestab.setVisible(true);

        FadeTransition fadecourses = new FadeTransition(Duration.millis(500), coursestab);
        fadecourses.setToValue(1.0);

        FadeTransition fadeprofile = new FadeTransition(Duration.millis(200), profiletab);
        fadeprofile.setToValue(0.0);


        fadecourses.setOnFinished(e -> {
            profilebutton.setDisable(false);
            coursesbutton.setDisable(false);
            profiletab.setVisible(false);
        });

        fadeprofile.play();
        fadecourses.play();
    }

    /**
     * Fills the profile tab with the given user's data.
     * @param user the user whose data to display
     */
    public void fillprofiledata(UserModel user) {


        usernamelabel.setText(user.getName());
        degreelabel.setText(user.getUserDegree());
        studentidlabel.setText(String.valueOf(user.getStudentId()));
    }

    /**
     * Fills the profile tab with the given staff member's data.
     * @param user the staff member whose data to display
     */
    public void fillprofiledatastaff(StaffModel user) {

        statuslabel.setText(Translator.getString("staff.status"));
        rolelabel.setText(Translator.getString("staff.role"));

        usernamelabel.setText(user.getName());
        degreelabel.setText(user.getStaffRole());
        studentidlabel.setText("");
    }

    /**
     * Creates and displays the attendance list window for the given course.
     * @param course the course data
     * @throws IOException if loading the attendance list fails
     */
    private void createattendancelist(ArrayList<Object> course) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AttendanceList.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Attendance list for " + course.get(1));
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        ArrayList<UserModel> users = new ArrayList<>();
        //users.add(new UserModel(25012, "Matti Meikäläinen", "Software Engineer"));

        Platform.runLater(() -> {
            AttendanceListController attendanceListController = loader.getController();
            try {
                attendanceListController.fillattendance(users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Fills the courses grid with the given list of courses.
     * @param courses the list of courses to display
     * @throws IOException if loading a course container fails
     */
    public void fillcourses(List<ArrayList<Object>> courses) throws IOException {
        int column = 0;
        int row = 0;

        for (ArrayList<Object> course : courses) {
            CourseContainer courseContainer = null;
            try {
                courseContainer = new CourseContainer(course);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (column == 2) {
                column = 0;
                row++;
            }

            coursegrid.add(courseContainer.getNode(), column++, row);

            Button attendancebutton = courseContainer.getAttendaceButton();
            attendancebutton.setOnAction(event -> {
                try {
                    createattendancelist(course);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * Handles the admin panel button action and opens the admin interface.
     */
    public void handleAdminButton() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminPage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(Translator.getString("admin.title"));
            stage.setScene(new Scene(root));
            stage.show();

            Platform.runLater(() -> {
                admininterfaceController = fxmlLoader.getController();
                admininterfaceController.translatePage();
            });
        } catch (Exception e){
            e.getMessage();
        }
    }
}


