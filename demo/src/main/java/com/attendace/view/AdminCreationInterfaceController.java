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

/**
 * Controller for the admin creation interface.
 * Handles creation of staff, students, courses, and degrees via the UI.
 */
public class AdminCreationInterfaceController {
    /** Logger for this controller. */
    private Logger log = Logger.getLogger(AdminCreationInterfaceController.class.getName());

    /** Controller for course-related operations. */
    private CourseController courseController = new CourseController();
    /** Utility for cryptographic operations. */
    private CryptoUtils crypto = new CryptoUtils();

    // Staff interface fields
    @FXML private TextField namefield;

    @FXML private TextField rolefield;

    @FXML private TextField passwordfield;

    @FXML private CheckBox isAdmin;

    // Student interface fields

    @FXML private TextField studentName;

    @FXML private TextField studentId;

    @FXML private TextField studentDegree;

    @FXML private TextField studentPasswField;

    // Course interface fields

    @FXML private TextField courseName;

    @FXML private TextField courseTopic;

    @FXML private TextField courseDesc;

    @FXML private TextField minCourseAttend;

    @FXML private TextField maxCourseAttend;

    @FXML private TextField courseAttendCode;

    @FXML private CheckBox courseActive;

    @FXML private CheckBox attendCourseActive;

    // Degree interface fields

    @FXML private TextField degreefield;

    @FXML private TextField ectsfield;

    // Common interface fields

    @FXML private Button saveButton;

    @FXML private Button cancelButton;

    /** Regular expression for non-digit characters. */
    String regex = "[^\\d]";
    /** Controller for user-related operations. */
    UserController userController = new UserController();
    /** Controller for staff-related operations. */
    StaffController staffController = new StaffController();
    /** Controller for degree-related operations. */
    DegreeController degreeController = new DegreeController();

    /**
     * Initializes the controller and sets up input validation for numeric fields.
     */
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
    /**
     * Handles saving a new staff member.
     * @param event the action event triggered by the save button
     */
    @FXML
    private void handleSaveStaff(ActionEvent event) {
        log.info("handleSaveStaff");

        String username = namefield.getText();
        String role = rolefield.getText();
        boolean admin = isAdmin.isSelected();
        String password = passwordfield.getText();

        //HARDCODED CHANGE
        String lang = "fi";

        staffController.createStaff(username, role, admin, password, lang);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // -- Student creation handler --
    /**
     * Handles saving a new student.
     * @param event the action event triggered by the save button
     */
    @FXML
    private void handleSaveStudent(ActionEvent event) {
        log.info("handleSaveStudent");

        String username = studentName.getText();
        int id = Integer.parseInt(studentId.getText());
        String degree = studentDegree.getText();
        String password = studentPasswField.getText();

        //HARDCODED CHANGE
        String lang = "fi";

        userController.createUser(id, username, password, degree, lang);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }
    // -- Course creation handler --
    /**
     * Handles saving a new course.
     * @param event the action event triggered by the save button
     */
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

    /**
     * Handles saving a new degree.
     * @param event the action event triggered by the save button
     */
    @FXML
    private void handleSaveDegree(ActionEvent event) {
        String degreeName = degreefield.getText();
        int ects = ectsfield.getText().isEmpty() ? 0 : Integer.parseInt(ectsfield.getText());
        degreeController.createDegree(degreeName, ects);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    /**
     * Handles cancelling the current operation and closes the window.
     * @param event the action event triggered by the cancel button
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        log.info("handleSaveCancel");
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
