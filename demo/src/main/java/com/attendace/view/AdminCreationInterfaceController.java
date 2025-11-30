package com.attendace.view;



import com.attendace.controller.CourseController;
import com.attendace.controller.UserController;
import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.utils.CryptoUtils;
import java.util.HashMap;
import java.util.Map;
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
    private Handler handler = new DefaultHandler();

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
        log.info("handleSaveDegree");
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
    private void handleCancel(ActionEvent event) {
        log.info("handleSaveCancel");
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
