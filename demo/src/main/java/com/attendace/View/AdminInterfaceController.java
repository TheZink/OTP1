package com.attendace.View;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.attendace.localisation.Translator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminInterfaceController {

    // For testing only
    // @Override
    // public void start(Stage primaryStage) throws Exception {
    //     Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminPage.fxml"));
    //     primaryStage.setTitle("Admin Interface");
    //     primaryStage.setScene(new Scene(root));
    //     primaryStage.show();
    // }

    Handler handler = new DefaultHandler();
    Map<String, Object> object = new HashMap<>();

    @FXML
    Button viewStaff, createStaff, modifyStaff;

    @FXML
    Button viewStudent, createStudent, modifyStudent;

    @FXML
    Button viewCourse, createCourse, modifyCourse;

    @FXML
    Button viewAtten, modifyAtten;

    @FXML
    Button saveButton, cancelButton, deleteButton, modifyButton;

    @FXML
    private ListView<String> listView;

    private String viewing = null;

    // -- VIEW BUTTON HANDLERS --

    public void TranslatePage() {
        viewStaff.setText(Translator.getString("admin.viewstaff"));
        createStaff.setText(Translator.getString("admin.createstaff"));

        viewStudent.setText(Translator.getString("admin.viewstudent"));
        createStudent.setText(Translator.getString("admin.createstudent"));

        viewCourse.setText(Translator.getString("admin.viewcourse"));
        createCourse.setText(Translator.getString("admin.createcourse"));

        viewAtten.setText(Translator.getString("admin.viewattendance"));

        saveButton.setText(Translator.getString("admin.savebutton"));
        cancelButton.setText(Translator.getString("admin.cancelbutton"));
        deleteButton.setText(Translator.getString("admin.deletebutton"));
        modifyButton.setText(Translator.getString("admin.modifybutton"));
    }
    
    @FXML
    private void handleViewStaff(ActionEvent event) {

        listView.getItems().clear();

        viewing = "staff";

        Request request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        ObservableList<String> items = FXCollections.observableArrayList();
        String displayColumn = String.format("%s\t %s\t %s\t %s",
                            "ID", "Name", "Role","Is admin","Created at");
        items.add(displayColumn);

        for (ArrayList<String> row : data) {
            String displayUser = String.format("%s\t %s\t %s\t %s", 
                                row.get(0), row.get(1), row.get(2), row.get(3));
            items.add(displayUser);

        listView.setItems(items);
        }
    }

    @FXML
    private void handleViewStudent(ActionEvent event) {

        listView.getItems().clear();

        viewing = "student";

        Request request = new Request(RequestDao.USERS, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        ObservableList<String> items = FXCollections.observableArrayList();
        String displayColumn = String.format("%s\t %s\t %s\t %s",
                            "ID", "Name", "Student ID","Degree","Created at");
        items.add(displayColumn);

        for (ArrayList<String> row : data) {
            String displayUser = String.format("%s\t %s\t %s\t %s", 
                                row.get(0), row.get(1), row.get(2), row.get(3));
            items.add(displayUser);

        listView.setItems(items);
        }
    }

    @FXML
    private void handleViewCourses(ActionEvent event) {

        listView.getItems().clear();

        viewing = "courses";

        Request request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        ObservableList<String> items = FXCollections.observableArrayList();
        String displayColumn = String.format("%s\t %s\t %s\t %s\t %s\t %s",
                            "ID", "Course name", "Course topic","Course description", "Course is active", "Course created");
        items.add(displayColumn);

        for (ArrayList<String> row : data) {
            String displayUser = String.format("%s\t %s\t %s\t %s\t %s\t %s", 
                                row.get(0), row.get(1), row.get(2), row.get(3), row.get(8), row.get(9));
            items.add(displayUser);

        listView.setItems(items);
        }
    }

    @FXML
    private void handleViewAttendance(ActionEvent event){

        listView.getItems().clear();

        viewing = "attendance";

        Request request = new Request(RequestDao.ATTENDANCE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        ObservableList<String> items = FXCollections.observableArrayList();
        String displayColumn = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s",
                            "ID", "Course ID", "Student ID", "Staff ID", "Attendance handled", "Attendance current", "Attendance created");
        items.add(displayColumn);

        for (ArrayList<String> row : data) {
            String displayUser = String.format("%s\t %s\t %s\t %s\t %s\t %s\t %s", 
                                row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6));
            items.add(displayUser);

        listView.setItems(items);
        }
    }

    // -- CREATION BUTTON HANDLERS --

    @FXML
    private void handleCreateStaff(ActionEvent event){
        System.out.println("'Create staff' button pressed");

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminStaffCreation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Staff");
            stage.setScene(new Scene(root));
            stage.show();

            handleViewStaff(event);

            TextField namefield = (TextField) root.lookup("#namefield");
            namefield.setPromptText(Translator.getString("createstaff.name"));

            TextField rolefield = (TextField) root.lookup("#rolefield");
            rolefield.setPromptText(Translator.getString("createstaff.role"));

            TextField passwordfield = (TextField) root.lookup("#passwordfield");
            passwordfield.setPromptText(Translator.getString("createstaff.password"));

            Text isadmin = (Text) root.lookup("#isAdmin");
            isadmin.setText(Translator.getString("createstaff.isadmin"));

            Text useractive = (Text) root.lookup("#useractive");
            useractive.setText(Translator.getString("useractive"));

            Button saveButton = (Button) root.lookup("#saveButton");
            saveButton.setText(Translator.getString("create"));

            Button cancelButton = (Button) root.lookup("#cancelButton");
            cancelButton.setText(Translator.getString("cancel"));

        } catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    private void handleCreateStudent(ActionEvent event){
        System.out.println("'Create student' button pressed");

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminStudentCreation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create student");
            stage.setScene(new Scene(root));
            stage.show();

            handleViewStudent(event);

            TextField passfield = (TextField) root.lookup("#studentPasswField");
            passfield.setPromptText(Translator.getString("createstudent.password"));

            TextField idfield = (TextField) root.lookup("#studentId");
            idfield.setPromptText(Translator.getString("createstudent.studentid"));

            TextField namefield = (TextField) root.lookup("#studentName");
            namefield.setPromptText(Translator.getString("createstudent.name"));

            TextField degreefield = (TextField) root.lookup("#studentDegree");
            degreefield.setPromptText(Translator.getString("createstudent.studentdegree"));

            Text useractive = (Text) root.lookup("#useractive");
            useractive.setText(Translator.getString("useractive"));

            Button saveButton = (Button) root.lookup("#saveButton");
            saveButton.setText(Translator.getString("create"));

            Button cancelButton = (Button) root.lookup("#cancelButton");
            cancelButton.setText(Translator.getString("cancel"));

        } catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    private void handleCreateCourse(ActionEvent event){
        System.out.println("'Create course' pressed");

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminCourseCreation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create course");
            stage.setScene(new Scene(root));
            stage.show();

            handleViewCourses(event);

            TextField namefield = (TextField) root.lookup("#courseName");
            namefield.setPromptText(Translator.getString("createcourse.coursename"));

            TextField topicfield = (TextField) root.lookup("#courseTopic");
            topicfield.setPromptText(Translator.getString("createcourse.topic"));

            TextField descriptionfield = (TextField) root.lookup("#courseDesc");
            descriptionfield.setPromptText(Translator.getString("createcourse.description"));

            TextField minattend = (TextField) root.lookup("#minCourseAttend");
            minattend.setPromptText(Translator.getString("createcourse.minattend"));

            TextField maxattend = (TextField) root.lookup("#maxCourseAttend");
            maxattend.setPromptText(Translator.getString("createcourse.maxattend"));

            TextField codeattend =  (TextField) root.lookup("#courseAttendCode");
            codeattend.setPromptText(Translator.getString("createcourse.codeattend"));

            Text coursactive = (Text) root.lookup("#courseActive");
            coursactive.setText(Translator.getString("createcourse.coursactive"));

            Text isattend = (Text) root.lookup("#attendCourseActive");
            isattend.setText(Translator.getString("createcourse.isattend"));

            Button saveButton = (Button) root.lookup("#saveButton");
            saveButton.setText(Translator.getString("create"));

            Button cancelButton = (Button) root.lookup("#cancelButton");
            cancelButton.setText(Translator.getString("cancel"));
        } catch (Exception e){
            e.getMessage();
        }
    }

        // -- DELETE HANDLER --

    @FXML
    private void handleDelete(ActionEvent event){
        System.out.println("'Delete' button pressed");

        if (viewing.equals("student")) {
            System.out.println("Viewing students. Delete entry");
            String item = listView.getSelectionModel().getSelectedItem();
    
            if (item == null) return; // Nothing selected
            if (item.startsWith("ID")) return; // First row selected

            String[] parts = item.trim().split("\\s+"); // Fetch selected row
            int id = Integer.parseInt(parts[0]); // Fetch row id
    
            Map<String, Object> object = new HashMap<>();
            object.put("value", id);
            object.put("label", "id");
    
            Request request = new Request(RequestDao.USERS, RequestType.REMOVEDATA, object);
            handler.handle(request);
    
            handleViewStudent(event);
        }

        else if (viewing.equals("staff")) {
            System.out.println("Viewing students. Delete entry");
            String item = listView.getSelectionModel().getSelectedItem();
    
            if (item == null) return; // Nothing selected
            if (item.startsWith("ID")) return; // First row selected

            String[] parts = item.trim().split("\\s+"); // Fetch selected row
            int id = Integer.parseInt(parts[0]); // Fetch row id
    
            Map<String, Object> object = new HashMap<>();
            object.put("value", id);
            object.put("label", "id");
    
            Request request = new Request(RequestDao.STAFF, RequestType.REMOVEDATA, object);
            handler.handle(request);
    
            handleViewStaff(event);
        }

        else if (viewing.equals("courses")) {
            System.out.println("Viewing courses. Delete entry");
            String item = listView.getSelectionModel().getSelectedItem();
    
            if (item == null) return; // Nothing selected
            if (item.startsWith("ID")) return; // First row selected

            String[] parts = item.trim().split("\\s+"); // Fetch selected row
            int id = Integer.parseInt(parts[0]); // Fetch row id
    
            Map<String, Object> object = new HashMap<>();
            object.put("value", id);
            object.put("label", "id");
    
            Request request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, object);
            handler.handle(request);
    
            handleViewCourses(event);
        }

        else if (viewing.equals("attendance")) {
            System.out.println("Viewing attendance. Delete entry");
            String item = listView.getSelectionModel().getSelectedItem();
    
            if (item == null) return; // Nothing selected
            if (item.startsWith("ID")) return; // First row selected

            String[] parts = item.trim().split("\\s+"); // Fetch selected row
            int id = Integer.parseInt(parts[0]); // Fetch row id
    
            Map<String, Object> object = new HashMap<>();
            object.put("value", id);
            object.put("label", "id");
    
            Request request = new Request(RequestDao.ATTENDANCE, RequestType.REMOVEDATA, object);
            handler.handle(request);
    
            handleViewAttendance(event);
        }
    }
}
