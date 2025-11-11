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

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminInterfaceController {

    // For testing only. Extends Application
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
    Button viewDegree, createDegree;

    @FXML
    Button viewAtten, modifyAtten;

    @FXML
    Button saveButton, cancelButton, deleteButton, modifyButton;

    @FXML
    private TableView<ObservableList<String>> tableView;

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
        
        viewDegree.setText(Translator.getString("admin.viewdegree"));
        createDegree.setText(Translator.getString("admin.createdegree"));

        saveButton.setText(Translator.getString("admin.savebutton"));
        cancelButton.setText(Translator.getString("admin.cancelbutton"));
        deleteButton.setText(Translator.getString("admin.deletebutton"));
        modifyButton.setText(Translator.getString("admin.modifybutton"));
    }
    
    @FXML
    private void handleViewStaff(ActionEvent event) {

        viewing = "staff";

        Request request = new Request(RequestDao.STAFF, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        // Remove unwanted elements
        for (ArrayList<String> index : data){
            index.remove(4);
        }

        String[] headers = new String[] {Translator.getString("tableview.id"), Translator.getString("tableview.name"), Translator.getString("tableview.role"), Translator.getString("tableview.admin"), Translator.getString("tableview.time")};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewStudent(ActionEvent event) {

        viewing = "student";

        Request request = new Request(RequestDao.USERS, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        // Remove unwanted elements
        for (ArrayList<String> index : data){
            index.remove(4);
        }
       
        String[] headers = new String[] {Translator.getString("tableview.id"), Translator.getString("tableview.name"), Translator.getString("tableview.studId"), Translator.getString("tableview.degree"), Translator.getString("tableview.time")};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewCourses(ActionEvent event) {

        viewing = "courses";

        Request request = new Request(RequestDao.COURSE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        String[] headers = new String[] {Translator.getString("tableview.id"), Translator.getString("tableview.name"), Translator.getString("tableview.topic"), Translator.getString("tableview.desc"), Translator.getString("tableview.attenAv"), Translator.getString("tableview.attenKey"), Translator.getString("tableview.minAtt"), Translator.getString("tableview.maxAtt"), Translator.getString("tableview.active"), Translator.getString("tableview.time")};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewAttendance(ActionEvent event){

        viewing = "attendance";

        Request request = new Request(RequestDao.ATTENDANCE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        String[] headers = new String[] {Translator.getString("tableview.id"), Translator.getString("tableview.courId"), Translator.getString("tableview.studId"), Translator.getString("tableview.staffId"), Translator.getString("tableview.handl"), Translator.getString("tableview.curr"), Translator.getString("tableview.time")};
        tablaViewFormatter(tableView, data, headers);
    }

    @FXML
    private void handleViewDegrees(ActionEvent event) {
        viewing = "degree";

        Request request = new Request(RequestDao.DEGREE, RequestType.GETALLDATA, object);
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) handler.handle(request);

        String[] headers = new String[] {Translator.getString("tableview.id"), Translator.getString("tableview.name"), Translator.getString("tableview.ects")};
        tablaViewFormatter(tableView, data, headers);
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
            stage.showAndWait();

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
            stage.showAndWait();

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
            stage.showAndWait();

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


    @FXML
    private void handleCreateDegree(ActionEvent event){
        System.out.println("'Create Degree' pressed");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminDegreeCreation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create degree");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            handleViewDegrees(event);
           
        } catch (Exception e) {
            e.getMessage();
        }
    }

        // -- DELETE HANDLER --

    @FXML
    private void handleDelete(ActionEvent event){
        System.out.println("'Delete' button pressed");

        ObservableList<String> selectedRow = tableView.getSelectionModel().getSelectedItem();

        if (selectedRow == null || selectedRow.isEmpty()){
            System.out.println("Nothing selected");
            return;
        }

        // Promt user for confirmation
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm deletion");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure, you want to delete this entry?");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        ButtonType choise = confirm.showAndWait().orElse(ButtonType.NO);
        if (choise != ButtonType.YES){
            System.out.println("Deletion canselled");
            return;
        }


        String idColumn = selectedRow.get(0).trim();
        int id;

        try {
            id = Integer.parseInt(idColumn);
        } catch (NumberFormatException e){
            System.out.println("Selected id is not valid");
            return;
        }
        
        Map<String, Object> object = new HashMap<>();
        object.put("value", id);
        object.put("label", "id");

        if (viewing.equals("student")) {
            System.out.println("Viewing students. Delete entry");
            Request request = new Request(RequestDao.USERS, RequestType.REMOVEDATA, object);
            handler.handle(request);
            handleViewStudent(event);
        }

        else if (viewing.equals("staff")) {
            System.out.println("Viewing staff. Delete entry");
            Request request = new Request(RequestDao.STAFF, RequestType.REMOVEDATA, object);
            handler.handle(request);
            handleViewStaff(event);
        }

        else if (viewing.equals("courses")) {
            System.out.println("Viewing courses. Delete entry");
            Request request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, object);
            handler.handle(request);
            handleViewCourses(event);
        }

        else if (viewing.equals("attendance")) {
            System.out.println("Viewing attendance. Delete entry");
            Request request = new Request(RequestDao.ATTENDANCE, RequestType.REMOVEDATA, object);
            handler.handle(request);
            handleViewAttendance(event);
        }

        else if (viewing.equals("degree")){
            System.out.println("Viewing degree. Delete entry");
            Request request = new Request(RequestDao.DEGREE, RequestType.REMOVEDATA, object);
            handler.handle(request);
            handleViewAttendance(event);
        }
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
