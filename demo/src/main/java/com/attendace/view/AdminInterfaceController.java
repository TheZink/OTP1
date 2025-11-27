package com.attendace.view;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.utils.CryptoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.attendace.localisation.Translator;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminInterfaceController {
    Logger log = Logger.getLogger(AdminInterfaceController.class.getName());

    // FXML lookup keys
    private static final String LK_NAMEFIELD = "#namefield";
    private static final String LK_ROLEFIELD = "#rolefield";
    private static final String LK_PASSWDFIELD = "#passwordfield";
    private static final String LK_ISADMIN = "#isAdmin";
    private static final String LK_USERACTIVE = "#useractive";
    private static final String LK_SAVEBUTTON = "#saveButton";
    private static final String LK_CANCELBUTTON = "#cancelButton";
    private static final String LK_STUDENT_PASSW = "#studentPasswField";
    private static final String LK_STUDENT_ID = "#studentId";
    private static final String LK_STUDENT_NAME = "#studentName";
    private static final String LK_STUDENT_DEGREE = "#studentDegree";
    private static final String LK_COURSE_NAME = "#courseName";
    private static final String LK_COURSE_TOPIC = "#courseTopic";
    private static final String LK_COURSE_DESC = "#courseDesc";
    private static final String LK_MIN_COURSE_ATTEND = "#minCourseAttend";
    private static final String LK_MAX_COURSE_ATTEND = "#maxCourseAttend";
    private static final String LK_COURSE_ATTEND_CODE = "#courseAttendCode";
    private static final String LK_COURSE_ACTIVE = "#courseActive";
    private static final String LK_ATTEND_COURSE_ACTIVE = "#attendCourseActive";
    private static final String LK_DEGREE_FIELD = "#degreefield";
    private static final String LK_ECTS_FIELD = "#ectsfield";
    private static final String LK_ATTEND_KEY = "#attendKey";

    // Translation keys
    private static final String T_MAIN_SOFTWARE_TITLE = "main.softwareTitle";
    private static final String T_ADMIN_VIEW_STAFF = "admin.viewstaff";
    private static final String T_ADMIN_CREATE_STAFF = "admin.createstaff";
    private static final String T_ADMIN_VIEW_STUDENT = "admin.viewstudent";
    private static final String T_ADMIN_CREATE_STUDENT = "admin.createstudent";
    private static final String T_ADMIN_VIEW_COURSE = "admin.viewcourse";
    private static final String T_ADMIN_CREATE_COURSE = "admin.createcourse";
    private static final String T_ADMIN_VIEW_ATTEN = "admin.viewattendance";
    private static final String T_ADMIN_VIEW_DEGREE = "admin.viewdegree";
    private static final String T_ADMIN_CREATE_DEGREE = "admin.createdegree";
    private static final String T_ADMIN_DELETE_BUTTON = "admin.deletebutton";
    private static final String T_ADMIN_MODIFY_BUTTON = "admin.modifybutton";
    private static final String T_ADMIN_REFRESH_BUTTON = "admin.refreshbutton";
    private static final String T_ADMIN_CLOSE_BUTTON = "admin.closebutton";
    private static final String T_ADMIN_CANCEL_BUTTON = "admin.cancelbutton";

    private static final String T_CREATE = "create";
    private static final String T_CANCEL = "cancel";

    private static final String T_CREATESTAFF_NAME = "createstaff.name";
    private static final String T_CREATESTAFF_ROLE = "createstaff.role";
    private static final String T_CREATESTAFF_PASSWORD = "createstaff.password";
    private static final String T_CREATESTAFF_ISADMIN = "createstaff.isadmin";

    private static final String T_USER_ACTIVE = "useractive";

    private static final String T_CREATESTUDENT_PASSWORD = "createstudent.password";
    private static final String T_CREATESTUDENT_STUDENTID = "createstudent.studentid";
    private static final String T_CREATESTUDENT_NAME = "createstudent.name";
    private static final String T_CREATESTUDENT_DEGREE = "createstudent.studentdegree";

    private static final String T_CREATECOURSE_NAME = "createcourse.coursename";
    private static final String T_CREATECOURSE_TOPIC = "createcourse.topic";
    private static final String T_CREATECOURSE_DESC = "createcourse.description";
    private static final String T_CREATECOURSE_MINATTEND = "createcourse.minattend";
    private static final String T_CREATECOURSE_MAXATTEND = "createcourse.maxattend";
    private static final String T_CREATECOURSE_CODEATTEND = "createcourse.codeattend";
    private static final String T_CREATECOURSE_COURSACTIVE = "createcourse.coursactive";
    private static final String T_CREATECOURSE_ISATTEND = "createcourse.isattend";
    private static final String T_CREATECOURSE_ATTENDCODE = "createcourse.attendcode";

    private static final String T_CREATEDGREE_NAME = "createdegree.name";
    private static final String T_CREATEDGREE_ECTS = "createdegree.ects";

    private static final String T_DELETE_NOSELECT = "deletion.noselect";
    private static final String T_DELETION_CONFIRM_TITLE = "deletion.confirmtitle";
    private static final String T_DELETION_CONFIRM = "deletion.confirm";
    private static final String T_DELETION_ERRORFETCH = "deletion.errorfetch";

    private static final String T_MODIF_NOSELECT = "modif.noselect";
    private static final String T_MODIF_ERRORFETCH = "modif.errorfetch";
    private static final String T_MODIF_PASSWORD = "modif.password";
    private static final String T_MODIF_TITLE = "modif.title";

    private static final String T_TABLEVIEW_ID = "tableview.id";
    private static final String T_TABLEVIEW_NAME = "tableview.name";
    private static final String T_TABLEVIEW_TIME = "tableview.time";

    // Misc
    private static final String M_STUDENT = "student";
    private static final String M_STAFF = "staff";
    private static final String M_COURSES = "courses";
    private static final String M_ATTENDANCE = "attendance";
    private static final String M_DEGREE = "degree";

    private static final String M_PASSWORD = "password";

    Handler handler = new DefaultHandler();
    Map<String, Object> object = new HashMap<>();

    CryptoUtils crypto = new CryptoUtils();

    @FXML
    private Text softwareTitle;

    @FXML
    private Button viewStaff;

    @FXML
    private Button createStaff;

    @FXML
    private Button modifyStaff;

    @FXML
    private Button viewStudent;

    @FXML
    private Button createStudent;

    @FXML
    private Button modifyStudent;

    @FXML
    private Button viewCourse;

    @FXML
    private Button createCourse;

    @FXML
    private Button modifyCourse;

    @FXML
    private Button viewDegree;

    @FXML
    private Button createDegree;



    @FXML
    private Button viewAtten;

    @FXML
    private Button modifyAtten;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<ObservableList<String>> tableView;

    private String viewing = null;

    // -- VIEW BUTTON HANDLERS --

    public void translatePage() {

        softwareTitle.setText(Translator.getString(T_MAIN_SOFTWARE_TITLE));
        viewStaff.setText(Translator.getString(T_ADMIN_VIEW_STAFF));
        createStaff.setText(Translator.getString(T_ADMIN_CREATE_STAFF));

        viewStudent.setText(Translator.getString(T_ADMIN_VIEW_STUDENT));
        createStudent.setText(Translator.getString(T_ADMIN_CREATE_STUDENT));

        viewCourse.setText(Translator.getString(T_ADMIN_VIEW_COURSE));
        createCourse.setText(Translator.getString(T_ADMIN_CREATE_COURSE));

        viewAtten.setText(Translator.getString(T_ADMIN_VIEW_ATTEN));

        viewDegree.setText(Translator.getString(T_ADMIN_VIEW_DEGREE));
        createDegree.setText(Translator.getString(T_ADMIN_CREATE_DEGREE));

        deleteButton.setText(Translator.getString(T_ADMIN_DELETE_BUTTON));
        modifyButton.setText(Translator.getString(T_ADMIN_MODIFY_BUTTON));
        refreshButton.setText(Translator.getString(T_ADMIN_REFRESH_BUTTON));
        closeButton.setText(Translator.getString(T_ADMIN_CLOSE_BUTTON));

    }

    public void refreshPage(ActionEvent event){
        if(viewing.equals(M_STUDENT)) {handleViewStudent(event);}
        else if(viewing.equals(M_STAFF)) {handleViewStaff(event);}
        else if(viewing.equals(M_COURSES)) {handleViewCourses(event);}
        else if(viewing.equals(M_ATTENDANCE)) {handleViewAttendance(event);}
        else if(viewing.equals(M_DEGREE)) {handleViewAttendance(event);}
    }

    @FXML
    private void handleViewStaff(ActionEvent event) {

        viewing = M_STAFF;

        Request request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        // Remove unwanted elements
        for (ArrayList<String> index : data){
            index.remove(4);
        }

        String[] headers = new String[] {Translator.getString(T_TABLEVIEW_ID), Translator.getString(T_TABLEVIEW_NAME), Translator.getString("tableview.role"), Translator.getString("tableview.admin"), Translator.getString(T_TABLEVIEW_TIME)};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewStudent(ActionEvent event) {

        viewing = M_STUDENT;

        Request request = new Request(RequestDao.USERS, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        // Remove unwanted elements
        for (ArrayList<String> index : data){
            index.remove(4);
        }

        String[] headers = new String[] {Translator.getString(T_TABLEVIEW_ID), Translator.getString(T_TABLEVIEW_NAME), Translator.getString("tableview.studId"), Translator.getString("tableview.degree"), Translator.getString(T_TABLEVIEW_TIME)};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewCourses(ActionEvent event) {

        viewing = M_COURSES;

        Request request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        String[] headers = new String[] {Translator.getString(T_TABLEVIEW_ID), Translator.getString(T_TABLEVIEW_NAME), Translator.getString("tableview.topic"), Translator.getString("tableview.desc"), Translator.getString("tableview.attenAv"), Translator.getString("tableview.attenKey"), Translator.getString("tableview.minAtt"), Translator.getString("tableview.maxAtt"), Translator.getString("tableview.active"), Translator.getString(T_TABLEVIEW_TIME)};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewAttendance(ActionEvent event){

        viewing = M_ATTENDANCE;

        Request request = new Request(RequestDao.ATTENDANCE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        String[] headers = new String[] {Translator.getString(T_TABLEVIEW_ID), Translator.getString("tableview.courId"), Translator.getString("tableview.studId"), Translator.getString("tableview.staffId"), Translator.getString("tableview.handl"), Translator.getString("tableview.curr"), Translator.getString(T_TABLEVIEW_TIME)};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewDegrees(ActionEvent event) {
        viewing = M_DEGREE;

        Request request = new Request(RequestDao.DEGREE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        String[] headers = new String[] {Translator.getString(T_TABLEVIEW_ID), Translator.getString(T_TABLEVIEW_NAME), Translator.getString("tableview.ects")};
        tablaViewFormatter(tableView, data, headers);
    }

    // -- CREATION BUTTON HANDLERS --

    @FXML
    private void handleCreateStaff(ActionEvent event){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminStaffCreation.fxml"));
            Parent root = fxmlLoader.load();

            handleViewStaff(event);

            ((TextField) root.lookup(LK_NAMEFIELD)).setPromptText(Translator.getString(T_CREATESTAFF_NAME));
            ((TextField) root.lookup(LK_ROLEFIELD)).setPromptText(Translator.getString(T_CREATESTAFF_ROLE));
            ((TextField) root.lookup(LK_PASSWDFIELD)).setPromptText(Translator.getString(T_CREATESTAFF_PASSWORD));

            ((CheckBox) root.lookup(LK_ISADMIN)).setText(Translator.getString(T_CREATESTAFF_ISADMIN));
            ((CheckBox) root.lookup(LK_USERACTIVE)).setText(Translator.getString(T_USER_ACTIVE));

            ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_CREATE));
            ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_CANCEL));

            Stage stage = new Stage();
            stage.setTitle(Translator.getString(T_ADMIN_CREATE_STAFF));
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e){
            e.getMessage();
        }

        refreshPage(event);
    }

    @FXML
    private void handleCreateStudent(ActionEvent event){

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminStudentCreation.fxml"));
            Parent root = fxmlLoader.load();

            handleViewStudent(event);

            ((TextField) root.lookup(LK_STUDENT_PASSW)).setPromptText(Translator.getString(T_CREATESTUDENT_PASSWORD));
            ((TextField) root.lookup(LK_STUDENT_ID)).setPromptText(Translator.getString(T_CREATESTUDENT_STUDENTID));
            ((TextField) root.lookup(LK_STUDENT_NAME)).setPromptText(Translator.getString(T_CREATESTUDENT_NAME));
            ((TextField) root.lookup(LK_STUDENT_DEGREE)).setPromptText(Translator.getString(T_CREATESTUDENT_DEGREE));

            ((CheckBox) root.lookup(LK_USERACTIVE)).setText(Translator.getString(T_USER_ACTIVE));

            ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_CREATE));
            ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_CANCEL));

            Stage stage = new Stage();
            stage.setTitle(Translator.getString(T_ADMIN_CREATE_STUDENT));
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e){
            e.getMessage();
        }

        refreshPage(event);
    }

    @FXML
    private void handleCreateCourse(ActionEvent event){

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminCourseCreation.fxml"));
            Parent root = fxmlLoader.load();

            handleViewCourses(event);

            ((TextField) root.lookup(LK_COURSE_NAME)).setPromptText(Translator.getString(T_CREATECOURSE_NAME));
            ((TextField) root.lookup(LK_COURSE_TOPIC)).setPromptText(Translator.getString(T_CREATECOURSE_TOPIC));
            ((TextField) root.lookup(LK_COURSE_DESC)).setPromptText(Translator.getString(T_CREATECOURSE_DESC));
            ((TextField) root.lookup(LK_MIN_COURSE_ATTEND)).setPromptText(Translator.getString(T_CREATECOURSE_MINATTEND));
            ((TextField) root.lookup(LK_MAX_COURSE_ATTEND)).setPromptText(Translator.getString(T_CREATECOURSE_MAXATTEND));
            ((TextField) root.lookup(LK_COURSE_ATTEND_CODE)).setPromptText(Translator.getString(T_CREATECOURSE_CODEATTEND));

            ((CheckBox) root.lookup(LK_COURSE_ACTIVE)).setText(Translator.getString(T_CREATECOURSE_COURSACTIVE));
            ((CheckBox) root.lookup(LK_ATTEND_COURSE_ACTIVE)).setText(Translator.getString(T_CREATECOURSE_ISATTEND));

            ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_CREATE));
            ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_CANCEL));

            Stage stage = new Stage();
            stage.setTitle(Translator.getString(T_ADMIN_CREATE_COURSE));
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e){
            e.getMessage();
        }

        refreshPage(event);
    }

    @FXML
    private void handleCreateDegree(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminDegreeCreation.fxml"));
            Parent root = fxmlLoader.load();

            ((TextField) root.lookup(LK_DEGREE_FIELD)).setPromptText(Translator.getString(T_CREATEDGREE_NAME));
            ((TextField) root.lookup(LK_ECTS_FIELD)).setPromptText(Translator.getString(T_CREATEDGREE_ECTS));

            ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_CREATE));
            ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_CANCEL));

            Stage stage = new Stage();
            stage.setTitle(Translator.getString(T_ADMIN_CREATE_DEGREE));
            stage.setScene(new Scene(root));
            stage.showAndWait();

            handleViewDegrees(event);

        } catch (Exception e) {
            e.getMessage();
        }

        refreshPage(event);
    }

    // -- DELETE HANDLER --

    @FXML
    private void handleDelete(ActionEvent event){
        ObservableList<String> selectedRow = tableView.getSelectionModel().getSelectedItem();

        if (selectedRow == null || selectedRow.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, Translator.getString(T_DELETE_NOSELECT));
            return;
        }

        // Promt user for confirmation
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle(Translator.getString(T_DELETION_CONFIRM_TITLE));
        confirm.setHeaderText(null);
        confirm.setContentText(Translator.getString(T_DELETION_CONFIRM));
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        ButtonType choice = confirm.showAndWait().orElse(ButtonType.NO);
        if (choice != ButtonType.YES){
            return;
        }

        String idColumn = selectedRow.get(0).trim();
        int id;

        try {
            id = Integer.parseInt(idColumn);
        } catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR, Translator.getString(T_DELETION_ERRORFETCH) +
                    " " + e.getMessage());
            return;
        }

        object = new HashMap<>();
        object.put("value", id);
        object.put("label", "id");

        if (viewing.equals(M_STUDENT)) {
            Request request = new Request(RequestDao.USERS, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals(M_STAFF)) {
            Request request = new Request(RequestDao.STAFF, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals(M_COURSES)) {
            Request request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals(M_ATTENDANCE)) {
            Request request = new Request(RequestDao.ATTENDANCE, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals(M_DEGREE)){
            Request request = new Request(RequestDao.DEGREE, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        refreshPage(event);
    }

    //  --MODIFY HANDLER--

    public void handleModify(ActionEvent event){
        ObservableList<String> selectedRow = tableView.getSelectionModel().getSelectedItem();
        int rowId;

        // Throw error, if row is not selected
        if (selectedRow == null || selectedRow.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, Translator.getString(T_MODIF_NOSELECT));
            alert.showAndWait();
            return;
        }

        try {
            rowId = Integer.parseInt(selectedRow.get(0).trim());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, Translator.getString(T_MODIF_ERRORFETCH +
                    " " + e.getMessage()));
            return;
        }

        // Open correct dialog-window
        try {
            String fxmlPath;

            String adminStudentCreationFxml = "/fxml/AdminStudentCreation.fxml";
            String adminStaffCreationFxml = "/fxml/AdminStaffCreation.fxml";
            String adminCourseCreationFxml = "/fxml/AdminCourseCreation.fxml";
            String adminDegreeCreationFxml = "/fxml/AdminDegreeCreation.fxml";

            if (viewing.equals(M_STUDENT)) { fxmlPath = adminStudentCreationFxml;}
            else if (viewing.equals(M_STAFF)) { fxmlPath = adminStaffCreationFxml; }
            else if (viewing.equals(M_COURSES)) { fxmlPath = adminCourseCreationFxml; }
            else if (viewing.equals(M_DEGREE)) { fxmlPath = adminDegreeCreationFxml; }
            else { new Alert(Alert.AlertType.INFORMATION, "Edit is not possible for this entry"); return; }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            switch (viewing) {
                case M_STUDENT -> {
                    ((TextField) root.lookup(LK_STUDENT_NAME)).setText(selectedRow.get(1));
                    ((TextField) root.lookup(LK_STUDENT_ID)).setText(selectedRow.get(2));
                    ((TextField) root.lookup(LK_STUDENT_DEGREE)).setText(selectedRow.get(3));

                    ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_ADMIN_MODIFY_BUTTON));
                    ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_ADMIN_CANCEL_BUTTON));

                    TextField passField = (TextField) root.lookup(LK_STUDENT_PASSW);
                    passField.setPromptText(Translator.getString(T_MODIF_PASSWORD));
                }
                case M_STAFF -> {
                    ((TextField) root.lookup(LK_NAMEFIELD)).setText(selectedRow.get(1));
                    ((TextField) root.lookup(LK_ROLEFIELD)).setText(selectedRow.get(2));

                    ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_ADMIN_MODIFY_BUTTON));
                    ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_ADMIN_CANCEL_BUTTON));

                    TextField passField = (TextField) root.lookup(LK_PASSWDFIELD);
                    passField.setPromptText(Translator.getString(T_MODIF_PASSWORD));

                    CheckBox adminCheck = (CheckBox) root.lookup(LK_ISADMIN);
                    adminCheck.setText(Translator.getString(T_CREATESTAFF_ISADMIN));

                    adminCheck.setSelected(selectedRow.get(3).equals("true"));

                }
                case M_COURSES -> {
                    ((TextField) root.lookup(LK_COURSE_NAME)).setText(selectedRow.get(1));
                    ((TextField) root.lookup(LK_COURSE_TOPIC)).setText(selectedRow.get(2));
                    ((TextField) root.lookup(LK_COURSE_DESC)).setText(selectedRow.get(3));
                    ((TextField) root.lookup(LK_MIN_COURSE_ATTEND)).setText(selectedRow.get(6));
                    ((TextField) root.lookup(LK_MAX_COURSE_ATTEND)).setText(selectedRow.get(7));

                    ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_ADMIN_MODIFY_BUTTON));
                    ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_ADMIN_CANCEL_BUTTON));

                    TextField attendCode = (TextField) root.lookup(LK_COURSE_ATTEND_CODE);

                    if (attendCode != null) {
                        String attendVal = (selectedRow != null && selectedRow.size() > 5) ? selectedRow.get(5) : null;

                        if (attendVal != null) {
                            attendVal = attendVal.trim();
                        }
                        if (attendVal != null && !attendVal.isBlank() && !attendVal.equalsIgnoreCase("null")) {
                            attendCode.setText(attendVal);
                        } else {
                            attendCode.clear();
                            attendCode.setPromptText(Translator.getString(T_CREATECOURSE_ATTENDCODE));
                        }
                    } else {
                        log.info("Warning: courseAttendCode lookup returned null");
                    }

                    CheckBox courseActive = ((CheckBox) root.lookup(LK_COURSE_ACTIVE));
                    courseActive.setText(Translator.getString(T_CREATECOURSE_COURSACTIVE));

                    CheckBox attendAvaible = ((CheckBox) root.lookup(LK_ATTEND_COURSE_ACTIVE));
                    attendAvaible.setText(Translator.getString(T_CREATECOURSE_ISATTEND));


                    attendAvaible.setSelected(selectedRow.get(4).equalsIgnoreCase("true"));

                    courseActive.setSelected(selectedRow.get(8).equalsIgnoreCase("true"));
                }
                case M_DEGREE -> {
                    ((TextField) root.lookup(LK_DEGREE_FIELD)).setText(selectedRow.get(1));
                    ((TextField) root.lookup(LK_ECTS_FIELD)).setText(selectedRow.get(2));

                    ((Button) root.lookup(LK_SAVEBUTTON)).setText(Translator.getString(T_ADMIN_MODIFY_BUTTON));
                    ((Button) root.lookup(LK_CANCELBUTTON)).setText(Translator.getString(T_ADMIN_CANCEL_BUTTON));
                }

                default -> throw new IllegalStateException("Unexpected value: " + viewing);
            }

            // Replace SaveButton action to update data and pass to the DAO
            Button save = (Button) root.lookup(LK_SAVEBUTTON);

            save.setOnAction( ev -> {
                object = new HashMap<>();
                object.put("id", rowId);

                if(viewing.equals(M_STUDENT)) {
                    object.put("name", ((TextField) root.lookup(LK_STUDENT_NAME)).getText());
                    object.put(M_DEGREE, ((TextField) root.lookup(LK_STUDENT_DEGREE)).getText());

                    TextField passField = (TextField) root.lookup(LK_STUDENT_PASSW);

                    // If password is inputed, hash it and store it. Otherwise store only null-value. Null-value wont change password
                    if (passField != null){
                        String newPass = crypto.hash(passField.getText());
                        object.put(M_PASSWORD, newPass);
                    } else {
                        object.put(M_PASSWORD, null);
                    }

                }

                else if(viewing.equals(M_STAFF)) {
                    object.put("staff_name", ((TextField) root.lookup(LK_NAMEFIELD)).getText());
                    object.put("staff_role", ((TextField) root.lookup(LK_ROLEFIELD)).getText());

                    TextField passField = (TextField) root.lookup(LK_PASSWDFIELD);
                    CheckBox adminCheck = (CheckBox) root.lookup(LK_ISADMIN);
                    log.info(passField.getText());

                    // If password is inputed, hash it and store it. Otherwise store only null-value. Null-value wont change password
                    if (passField.getText() != null && !passField.getText().isBlank()){
                        String newPass = crypto.hash(passField.getText());
                        object.put(M_PASSWORD, newPass);
                    } else {
                        object.put(M_PASSWORD, null);
                    }

                    // If Administrator checkbox is selected, store true-value
                    object.put("staff_admin", adminCheck.isSelected());
                }

                else if(viewing.equals(M_COURSES)){
                    object.put("courseName", ((TextField) root.lookup(LK_COURSE_NAME)).getText());
                    object.put("courseTopic", ((TextField) root.lookup(LK_COURSE_TOPIC)).getText());
                    object.put("courseDesc", ((TextField) root.lookup(LK_COURSE_DESC)).getText());
                    object.put("attendMin", Integer.parseInt(((TextField) root.lookup(LK_MIN_COURSE_ATTEND)).getText()));
                    object.put("attendMax", Integer.parseInt(((TextField) root.lookup(LK_MAX_COURSE_ATTEND)).getText()));

                    CheckBox attendAvaible = ((CheckBox) root.lookup(LK_ATTEND_COURSE_ACTIVE));
                    CheckBox courseActive =  ((CheckBox) root.lookup(LK_COURSE_ACTIVE));
                    TextField attendCode = ((TextField) root.lookup(LK_COURSE_ATTEND_CODE));
                    log.info(String.valueOf(attendCode.getText().getClass()));

                    object.put("attendAvaib", attendAvaible.isSelected());
                    object.put("courseActive", courseActive.isSelected());

                    if (attendCode.getText().equals("null") || attendCode.getText().isBlank()){
                        object.put("attendKey", "");
                    } else {
                        object.put("attendKey", ((TextField) root.lookup(LK_ATTEND_KEY)).getText());
                    }
                }

                else if(viewing.equals(M_DEGREE)){
                    object.put("degreeName", ((TextField) root.lookup(LK_DEGREE_FIELD)).getText());
                    object.put("degreeEcts", Integer.parseInt(((TextField) root.lookup(LK_ECTS_FIELD)).getText()));
                }

                Request request = null;

                // Select correct request type from viewing-variable
                if (viewing.equals(M_STUDENT)) { request = new Request(RequestDao.USERS, RequestType.UPDATEDATA, object); }
                else if (viewing.equals(M_STAFF)) { request = new Request(RequestDao.STAFF, RequestType.UPDATEDATA, object); }
                else if (viewing.equals(M_COURSES)) { request = new Request(RequestDao.COURSE, RequestType.UPDATEDATA, object); }
                else if (viewing.equals(M_DEGREE)) { request = new Request(RequestDao.DEGREE, RequestType.UPDATEDATA, object); }
                else if (viewing.equals(M_ATTENDANCE)) { request = new Request(RequestDao.ATTENDANCE, RequestType.UPDATEDATA, object); }

                handler.handle(request);
                Stage s = (Stage) save.getScene().getWindow();
                s.close();
            });

            Stage stage = new Stage();
            stage.setTitle(Translator.getString(T_MODIF_TITLE) + " " + viewing);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            refreshPage(event);

        } catch (Exception e){
            e.printStackTrace();
            log.info(String.valueOf(e.getCause()));
        }
    }

    public void handleRefresh(ActionEvent event) {
        refreshPage(event);
    }

    public void handleClose(ActionEvent event){
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // TableView formatter

    private void tablaViewFormatter(TableView<ObservableList<String>> table, ArrayList<ArrayList<String>> data, String[] headers){
        table.getColumns().clear(); // Clear Tableview, when method is called

        int columns = 0;

        if (headers != null){
            columns = headers.length;
        } else if (!data.isEmpty()) {
            columns = data.get(0).size();
        }

        // Set TableView columns title
        for (int i = 0; i < columns; i++){
            int columnIndex = i;
            String columnTitle = (headers != null && i < headers.length) ? headers[i] : ("Column " + (i + 1)); // If title is empty, use default name
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(columnTitle);

            col.setCellValueFactory(cellData -> {
                ObservableList<String> row = cellData.getValue();
                String value = (columnIndex < row.size()) ? row.get(columnIndex) : ""; // If column value is empty, use empty value
                return new ReadOnlyStringWrapper(value);
            });

            table.getColumns().add(col);
        }

        // Set TableView columns value
        ObservableList<ObservableList<String>> rows = FXCollections.observableArrayList();
        for (ArrayList<String> row : data){
            rows.add(FXCollections.observableArrayList(row));
        }

        table.setItems(rows);
    }
}