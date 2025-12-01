package com.attendace.view;

import com.attendace.controller.CourseController;
import com.attendace.controller.DegreeController;
import com.attendace.controller.StaffController;
import com.attendace.controller.UserController;
import com.attendace.utils.CryptoUtils;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminCreationInterfaceController {
    private Logger log = Logger.getLogger(AdminCreationInterfaceController.class.getName());

    private CourseController courseController = new CourseController();
    private CryptoUtils crypto = new CryptoUtils();

    // For Staff interface
    @FXML
    private TextField namefield;

    @FXML
    private TextField rolefield;

    @FXML
    private TextField passwordfield;

    @FXML
    private CheckBox isAdmin;

    // For student interface
    @FXML
    private TextField studentName;

    @FXML
    private TextField studentId;

    @FXML
    private TextField studentDegree;

    @FXML
    private TextField studentPasswField;

    // For course interface
    @FXML
    private TextField courseName;

    @FXML
    private TextField courseTopic;

    @FXML
    private TextField courseDesc;

    @FXML
    private TextField minCourseAttend;

    @FXML
    private TextField maxCourseAttend;

    @FXML
    private TextField courseAttendCode;

    @FXML
    private CheckBox courseActive;

    @FXML
    private CheckBox attendCourseActive;

    // For degree interface
    @FXML
    private TextField degreefield;

    @FXML
    private TextField ectsfield;

    // For all interfaces
    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    String regex = "[^\\d]";
    UserController userController = new UserController();
    StaffController staffController = new StaffController();
    DegreeController degreeController = new DegreeController();


    public void initialize() {

        if (studentId != null) {
            studentId.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    studentId.setText(newValue.replaceAll(regex, ""));
                }
            });
        }

        if (minCourseAttend != null && maxCourseAttend != null) {
            minCourseAttend.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    minCourseAttend.setText(newValue.replaceAll(regex, ""));
                }
            });
    
            maxCourseAttend.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    maxCourseAttend.setText(newValue.replaceAll(regex, ""));
                }
            });
        }
    }

    // -- Staff creation handler --
    @FXML
    private void handleSaveStaff(ActionEvent event) {
        log.info("handleSaveStaff");

        String username = namefield.getText();
        String role = rolefield.getText();
        boolean admin = isAdmin.isSelected();
        String password = crypto.hash(passwordfield.getText());

        staffController.createStaff(username, role, admin, password);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // -- Student creation handler --

    @FXML
    private void handleSaveStudent(ActionEvent event) {
        log.info("handleSaveStudent");

        String username = studentName.getText();
        int id = Integer.parseInt(studentId.getText());
        String degree = studentDegree.getText();
        String password = crypto.hash(studentPasswField.getText());

        userController.createUser(id, username, password, degree);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }
    // -- Course creation handler --

    @FXML
    private void handleSaveCourse(ActionEvent event) {
        log.info("handleSaveCourse");

        int minAttendance = minCourseAttend.getText().isEmpty() ? 0 : Integer.parseInt(minCourseAttend.getText());
        int maxAttendance = maxCourseAttend.getText().isEmpty() ? 0 : Integer.parseInt(maxCourseAttend.getText());

        String coursename = courseName.getText();
        String coursetopic = courseTopic.getText();
        String coursedesc = courseDesc.getText();
        boolean attendcourseactive = attendCourseActive.isSelected();
        String coursecode = courseAttendCode.getText();
        boolean courseactive = courseActive.isSelected();

        courseController.createCourse(coursename, coursetopic, coursedesc, attendcourseactive, coursecode, minAttendance, maxAttendance, courseactive);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // -- Degree creation handler --

    @FXML
    private void handleSaveDegree(ActionEvent event) {
        String degreeName = degreefield.getText();
        int ects = ectsfield.getText().isEmpty() ? 0 : Integer.parseInt(ectsfield.getText());
        degreeController.createDegree(degreeName, ects);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        log.info("handleSaveCancel");
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
