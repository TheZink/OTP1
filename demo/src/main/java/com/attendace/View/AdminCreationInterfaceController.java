package com.attendace.View;

import java.util.HashMap;
import java.util.Map;

import com.attendace.Utils.CryptoUtils;
import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminCreationInterfaceController {

    CryptoUtils crypto = new CryptoUtils();
    Handler handler = new DefaultHandler();

    // For Staff interface
    @FXML
    private TextField namefield, rolefield, passwordfield;
    @FXML
    private CheckBox isAdmin;

    // For student interface
    @FXML
    private TextField studentName, studentId, studentDegree, studentPasswField;

    // For course interface
    @FXML 
    private TextField courseName, courseTopic, courseDesc, minCourseAttend, maxCourseAttend, courseAttendCode;
    @FXML
    private CheckBox courseActive, attendCourseActive;

    // For degree interface
    @FXML
    private TextField degreefield, ectsfield;

    // For all interfaces
    @FXML
    private Button saveButton, cancelButton;

    public void initialize() {
        if (studentId != null) {
            studentId.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    studentId.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

        if (minCourseAttend != null && maxCourseAttend != null){
            minCourseAttend.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    minCourseAttend.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
    
            maxCourseAttend.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    maxCourseAttend.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
    }

    // -- Staff creation handler --
    
    @FXML
    private void handleSaveStaff(ActionEvent event){

        System.out.println("'Create' button pressed");
        Map<String, Object> object = new HashMap<>();

        object.put("username", namefield.getText());
        object.put("role", rolefield.getText());
        object.put("isAdmin", isAdmin.isSelected());
        object.put("password", crypto.hash(passwordfield.getText()));

        Request request = new Request(RequestDao.STAFF, RequestType.SETDATA, object);
        handler.handle(request);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // -- Student creation handler --

    @FXML
    private void handleSaveStudent(ActionEvent event){
        System.out.println("'Create' button pressed");
        Map<String, Object> object = new HashMap<>();

        object.put("username", studentName.getText());
        object.put("student_id", Integer.parseInt(studentId.getText()));
        object.put("degree", studentDegree.getText());
        object.put("password", crypto.hash(studentPasswField.getText()));

        Request request = new Request(RequestDao.USERS, RequestType.SETDATA, object);
        handler.handle(request);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }

    // -- Course creation handler --

    @FXML
    private void handleSaveCourse(ActionEvent event){
        System.out.println("'Create' button pressed");
        Map<String, Object> object = new HashMap<>();

        int minAttendance = minCourseAttend.getText().isEmpty() ? 0 : Integer.parseInt(minCourseAttend.getText());
        int maxAttendance = maxCourseAttend.getText().isEmpty() ? 0 : Integer.parseInt(maxCourseAttend.getText());

        object.put("course_name", courseName.getText());
        object.put("course_topic", courseTopic.getText());
        object.put("course_desc", courseDesc.getText());
        object.put("attendance_avaible", attendCourseActive.isSelected());
        object.put("attendance_key", courseAttendCode.getText());
        object.put("min_attendance", minAttendance);
        object.put("max_attendance", maxAttendance);
        object.put("course_active", courseActive.isSelected());

        Request request = new Request(RequestDao.COURSE, RequestType.SETDATA, object);
        handler.handle(request);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // -- Degree creation handler --

    @FXML
    private void handleSaveDegree(ActionEvent event){
        System.out.println("'Create' button pressed");
        Map<String, Object> object = new HashMap<>();

        String degreeName = degreefield.getText();
        int ects = ectsfield.getText().isEmpty() ? 0 : Integer.parseInt(ectsfield.getText());

        object.put("degreeName", degreeName);
        object.put("degreeEcts", ects);

        Request request = new Request(RequestDao.DEGREE, RequestType.SETDATA, object);
        handler.handle(request);

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void handleCancel(ActionEvent event){
        System.out.println("'Cancel' button pressed");
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
