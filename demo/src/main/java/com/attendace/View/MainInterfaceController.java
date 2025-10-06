package com.attendace.View;

import com.attendace.Controller.CourseController;
import com.attendace.Engine.MainEngine;
import com.attendace.Model.StaffModel;
import com.attendace.Model.UserModel;
import com.attendace.View.Classes.CourseContainer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainInterfaceController {
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
    private GridPane participatingcoursesgrid;

    @FXML
    private Text studentidlabel;


    public void showprofile() throws IOException {
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

    public void showcourses() throws IOException {
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

    public void fillprofiledata(UserModel user) {
        statuslabel.setText("Student");
        rolelabel.setText("Degree In");

        usernamelabel.setText(user.getName());
        degreelabel.setText(user.getUserDegree());
        studentidlabel.setText(String.valueOf(user.getStudentId()));
    }

    public void fillprofiledatastaff(StaffModel user) {
        statuslabel.setText("Staff");
        rolelabel.setText("Role");

        usernamelabel.setText(user.getName());
        degreelabel.setText(user.getStaffRole());
        studentidlabel.setText("");
    }

    private void createattendancelist(ArrayList<Object> course) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AttendanceList.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Attendance list for " + course.get(1));
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        ArrayList<UserModel> users = new ArrayList<>();
        users.add(new UserModel(25012, "Matti Meikäläinen", "Software Engineer"));

        Platform.runLater(() -> {
            AttendanceListController attendanceListController = loader.getController();
            try {
                attendanceListController.fillattendance(users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void fillcourses(ArrayList<ArrayList<Object>> courses) throws IOException {
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
}
