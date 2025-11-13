package com.attendace.View;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.Utils.CryptoUtils;

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

    CryptoUtils crypto = new CryptoUtils();

    @FXML
    Text softwareTitle;

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
    Button saveButton, cancelButton, deleteButton, modifyButton, closeButton, refreshButton;

    @FXML
    private TableView<ObservableList<String>> tableView;

    private String viewing = null;

    // -- VIEW BUTTON HANDLERS --

    public void TranslatePage() {
        softwareTitle.setText(Translator.getString("main.softwareTitle"));
        viewStaff.setText(Translator.getString("admin.viewstaff"));
        createStaff.setText(Translator.getString("admin.createstaff"));

        viewStudent.setText(Translator.getString("admin.viewstudent"));
        createStudent.setText(Translator.getString("admin.createstudent"));

        viewCourse.setText(Translator.getString("admin.viewcourse"));
        createCourse.setText(Translator.getString("admin.createcourse"));

        viewAtten.setText(Translator.getString("admin.viewattendance"));
        
        viewDegree.setText(Translator.getString("admin.viewdegree"));
        createDegree.setText(Translator.getString("admin.createdegree"));

        deleteButton.setText(Translator.getString("admin.deletebutton"));
        modifyButton.setText(Translator.getString("admin.modifybutton"));
        refreshButton.setText(Translator.getString("admin.refreshbutton"));
        closeButton.setText(Translator.getString("admin.closebutton"));

    }

    public void refreshPage(ActionEvent event){
        if(viewing.equals("student")) {handleViewStudent(event);}
        else if(viewing.equals("staff")) {handleViewStaff(event);}
        else if(viewing.equals("courses")) {handleViewCourses(event);}
        else if(viewing.equals("attendance")) {handleViewAttendance(event);}
        else if(viewing.equals("degree")) {handleViewAttendance(event);}
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

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminStaffCreation.fxml"));
            Parent root = fxmlLoader.load();
            
            handleViewStaff(event);
            
            ((TextField) root.lookup("#namefield")).setPromptText(Translator.getString("createstaff.name"));
            ((TextField) root.lookup("#rolefield")).setPromptText(Translator.getString("createstaff.role"));
            ((TextField) root.lookup("#passwordfield")).setPromptText(Translator.getString("createstaff.password"));
            
            ((CheckBox) root.lookup("#isAdmin")).setText(Translator.getString("createstaff.isadmin"));
            ((CheckBox) root.lookup("#useractive")).setText(Translator.getString("useractive"));
            
            ((Button) root.lookup("#saveButton")).setText(Translator.getString("create"));
            ((Button) root.lookup("#cancelButton")).setText(Translator.getString("cancel"));
            
            Stage stage = new Stage();
            stage.setTitle(Translator.getString("admin.createstaff"));
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
            
            ((TextField) root.lookup("#studentPasswField")).setPromptText(Translator.getString("createstudent.password"));
            ((TextField) root.lookup("#studentId")).setPromptText(Translator.getString("createstudent.studentid"));
            ((TextField) root.lookup("#studentName")).setPromptText(Translator.getString("createstudent.name"));
            ((TextField) root.lookup("#studentDegree")).setPromptText(Translator.getString("createstudent.studentdegree"));
            
            ((CheckBox) root.lookup("#useractive")).setText(Translator.getString("useractive"));
        
            ((Button) root.lookup("#saveButton")).setText(Translator.getString("create"));
            ((Button) root.lookup("#cancelButton")).setText(Translator.getString("cancel"));

            Stage stage = new Stage();
            stage.setTitle(Translator.getString("admin.createstudent"));
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

            ((TextField) root.lookup("#courseName")).setPromptText(Translator.getString("createcourse.coursename"));
            ((TextField) root.lookup("#courseTopic")).setPromptText(Translator.getString("createcourse.topic"));
            ((TextField) root.lookup("#courseDesc")).setPromptText(Translator.getString("createcourse.description"));
            ((TextField) root.lookup("#minCourseAttend")).setPromptText(Translator.getString("createcourse.minattend"));
            ((TextField) root.lookup("#maxCourseAttend")).setPromptText(Translator.getString("createcourse.maxattend"));
            ((TextField) root.lookup("#courseAttendCode")).setPromptText(Translator.getString("createcourse.codeattend"));

            ((CheckBox) root.lookup("#courseActive")).setText(Translator.getString("createcourse.coursactive"));
            ((CheckBox) root.lookup("#attendCourseActive")).setText(Translator.getString("createcourse.isattend"));

            ((Button) root.lookup("#saveButton")).setText(Translator.getString("create"));
            ((Button) root.lookup("#cancelButton")).setText(Translator.getString("cancel"));

            Stage stage = new Stage();
            stage.setTitle(Translator.getString("admin.createcourse"));
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
            
            ((TextField) root.lookup("#degreefield")).setPromptText(Translator.getString("createdegree.name"));
            ((TextField) root.lookup("#ectsfield")).setPromptText(Translator.getString("createdegree.ects"));

            ((Button) root.lookup("#saveButton")).setText(Translator.getString("create"));
            ((Button) root.lookup("#cancelButton")).setText(Translator.getString("cancel"));

            Stage stage = new Stage();
            stage.setTitle(Translator.getString("admin.createdegree"));
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
            new Alert(Alert.AlertType.INFORMATION, Translator.getString("deletion.noselect"));
            return;
        }

        // Promt user for confirmation
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle(Translator.getString("deletion.confirmtitle"));
        confirm.setHeaderText(null);
        confirm.setContentText(Translator.getString("deletion.confirm"));
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
            new Alert(Alert.AlertType.ERROR, Translator.getString("deletion.errorfetch") + 
            " " + e.getMessage());
            return;
        }
        
        Map<String, Object> object = new HashMap<>();
        object.put("value", id);
        object.put("label", "id");

        if (viewing.equals("student")) {
            Request request = new Request(RequestDao.USERS, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals("staff")) {
            Request request = new Request(RequestDao.STAFF, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals("courses")) {
            Request request = new Request(RequestDao.COURSE, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals("attendance")) {
            Request request = new Request(RequestDao.ATTENDANCE, RequestType.REMOVEDATA, object);
            handler.handle(request);
        }

        else if (viewing.equals("degree")){
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION, Translator.getString("modif.noselect"));
            alert.showAndWait();
            return;
        }

        try {
            rowId = Integer.parseInt(selectedRow.get(0).trim());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, Translator.getString("modif.errorfetch" + 
            " " + e.getMessage()));
            return;
        }

        // Open correct dialog-window
        try {
            String fxmlPath;

            if (viewing.equals("student")) { fxmlPath = "/fxml/AdminStudentCreation.fxml";}
            else if (viewing.equals("staff")) { fxmlPath = "/fxml/AdminStaffCreation.fxml"; }
            else if (viewing.equals("courses")) { fxmlPath = "/fxml/AdminCourseCreation.fxml"; }
            else if (viewing.equals("degree")) { fxmlPath = "/fxml/AdminDegreeCreation.fxml"; }
            else { new Alert(Alert.AlertType.INFORMATION, "Edit is not possible for this entry"); return; }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            if (viewing.equals("student")){
                ((TextField) root.lookup("#studentName")).setText(selectedRow.get(1));
                ((TextField) root.lookup("#studentId")).setText(selectedRow.get(2));
                ((TextField) root.lookup("#studentDegree")).setText(selectedRow.get(3));

                ((Button) root.lookup("#saveButton")).setText(Translator.getString("admin.modifybutton"));
                ((Button) root.lookup("#cancelButton")).setText(Translator.getString("admin.cancelbutton"));

                TextField passField = (TextField) root.lookup("#studentPasswField");
                passField.setPromptText(Translator.getString("modif.password"));
            }

            else if(viewing.equals("staff")) {
                ((TextField) root.lookup("#namefield")).setText(selectedRow.get(1));
                ((TextField) root.lookup("#rolefield")).setText(selectedRow.get(2));

                ((Button) root.lookup("#saveButton")).setText(Translator.getString("admin.modifybutton"));
                ((Button) root.lookup("#cancelButton")).setText(Translator.getString("admin.cancelbutton"));

                TextField passField = (TextField) root.lookup("#passwordfield");
                passField.setPromptText(Translator.getString("modif.password"));

                CheckBox adminCheck = (javafx.scene.control.CheckBox) root.lookup("#isAdmin");
                adminCheck.setText(Translator.getString("createstaff.isadmin"));

                // Check selected row 'isAdmin' boolean
                if (selectedRow.get(3).equals("true")){
                    adminCheck.setSelected(true);
                } else {
                    adminCheck.setSelected(false);
                }
            }

            else if(viewing.equals("courses")) {
                ((TextField) root.lookup("#courseName")).setText(selectedRow.get(1));
                ((TextField) root.lookup("#courseTopic")).setText(selectedRow.get(2));
                ((TextField) root.lookup("#courseDesc")).setText(selectedRow.get(3));
                ((TextField) root.lookup("#minCourseAttend")).setText(selectedRow.get(6));
                ((TextField) root.lookup("#maxCourseAttend")).setText(selectedRow.get(7));

                ((Button) root.lookup("#saveButton")).setText(Translator.getString("admin.modifybutton"));
                ((Button) root.lookup("#cancelButton")).setText(Translator.getString("admin.cancelbutton"));

                TextField attendCode = (TextField) root.lookup("#courseAttendCode");

                if (attendCode != null) {
                    String attendVal = (selectedRow != null && selectedRow.size() > 5) ? selectedRow.get(5) : null;
                    if (attendVal != null) {
                        attendVal = attendVal.trim();
                    }
                    if (attendVal != null && !attendVal.isBlank() && !attendVal.equalsIgnoreCase("null")) {
                        attendCode.setText(attendVal);
                    } else {
                        attendCode.clear();
                        attendCode.setPromptText(Translator.getString("createcourse.attendcode"));
                    }
                } else {
                    System.out.println("Warning: courseAttendCode lookup returned null");
                }

                CheckBox courseActive =  ((CheckBox) root.lookup("#courseActive"));
                courseActive.setText(Translator.getString("createcourse.coursactive"));

                CheckBox attendAvaible = ((CheckBox) root.lookup("#attendCourseActive"));
                attendAvaible.setText(Translator.getString("createcourse.isattend"));


                if (selectedRow.get(4).equalsIgnoreCase("true")){
                    attendAvaible.setSelected(true);
                } else {
                    attendAvaible.setSelected(false);
                }
                
                if (selectedRow.get(8).equalsIgnoreCase("true")){
                    courseActive.setSelected(true);
                } else {
                    courseActive.setSelected(false);
                }
            }

            else if(viewing.equals("degree")){
                ((TextField) root.lookup("#degreefield")).setText(selectedRow.get(1));
                ((TextField) root.lookup("#ectsfield")).setText(selectedRow.get(2));

                ((Button) root.lookup("#saveButton")).setText(Translator.getString("admin.modifybutton"));
                ((Button) root.lookup("#cancelButton")).setText(Translator.getString("admin.cancelbutton"));
            }

            // Replace SaveButton action to update data and pass to the DAO
            Button save = (Button) root.lookup("#saveButton");

            save.setOnAction( ev -> {
                Map<String, Object> object = new HashMap<>();
                object.put("id", rowId);

                if(viewing.equals("student")) {
                    object.put("name", ((TextField) root.lookup("#studentName")).getText());
                    object.put("degree", ((TextField) root.lookup("#studentDegree")).getText());

                    TextField passField = (TextField) root.lookup("#studentPasswField");
                    
                    // If password is inputed, hash it and store it. Otherwise store only null-value. Null-value wont change password
                    if (passField != null){
                        String newPass = crypto.hash(passField.getText());
                        object.put("password", newPass);
                    } else {
                        object.put("password", null);
                    }
                    
                }
                
                else if(viewing.equals("staff")) {
                    object.put("name", ((TextField) root.lookup("#namefield")).getText());
                    object.put("role", ((TextField) root.lookup("#rolefield")).getText());
                    
                    TextField passField = (TextField) root.lookup("#passwordfield");
                    CheckBox adminCheck = (CheckBox) root.lookup("#isAdmin");
                    System.out.println(passField);
                    
                    // If password is inputed, hash it and store it. Otherwise store only null-value. Null-value wont change password
                    if (passField.getText() != null && !passField.getText().isBlank()){
                        String newPass = crypto.hash(passField.getText());
                        object.put("password", newPass);
                    } else {
                        object.put("password", null);
                    }

                    // If Administrator checkbox is selected, store true-value
                    if(adminCheck.isSelected()){
                        object.put("isAdmin", true);
                    } else {
                        object.put("isAdmin", false);
                    }
                }

                else if(viewing.equals("courses")){
                    object.put("courseName", ((TextField) root.lookup("#courseName")).getText());
                    object.put("courseTopic", ((TextField) root.lookup("#courseTopic")).getText());
                    object.put("courseDesc", ((TextField) root.lookup("#courseDesc")).getText());
                    object.put("attendMin", Integer.parseInt(((TextField) root.lookup("#minCourseAttend")).getText()));
                    object.put("attendMax", Integer.parseInt(((TextField) root.lookup("#maxCourseAttend")).getText()));

                    CheckBox attendAvaible = ((CheckBox) root.lookup("#attendCourseActive"));
                    CheckBox courseActive =  ((CheckBox) root.lookup("#courseActive"));
                    TextField attendCode = ((TextField) root.lookup("#courseAttendCode"));
                    System.out.println(attendCode.getText().getClass());

                    if(attendAvaible.isSelected()){
                        object.put("attendAvaib", true);
                    } else {
                        object.put("attendAvaib", false);
                    }

                    if(courseActive.isSelected()){
                        object.put("courseActive", true);
                    } else {
                        object.put("courseActive", false);
                    }

                    if (attendCode.getText().equals("null") || attendCode.getText().isBlank()){
                        object.put("attendKey", "");
                    } else {
                        object.put("attendKey", ((TextField) root.lookup("#attendKey")).getText());
                    }
                }

                else if(viewing.equals("degree")){
                    object.put("degreeName", ((TextField) root.lookup("#degreefield")).getText());
                    object.put("degreeEcts", Integer.parseInt(((TextField) root.lookup("#ectsfield")).getText()));
                }

                Request request = null;

                // Select correct request type from viewing-variable
                if (viewing.equals("student")) { request = new Request(RequestDao.USERS, RequestType.UPDATEDATA, object); }
                else if (viewing.equals("staff")) { request = new Request(RequestDao.STAFF, RequestType.UPDATEDATA, object); }
                else if (viewing.equals("courses")) { request = new Request(RequestDao.COURSE, RequestType.UPDATEDATA, object); }
                else if (viewing.equals("degree")) { request = new Request(RequestDao.DEGREE, RequestType.UPDATEDATA, object); }
                else if (viewing.equals("attendance")) { request = new Request(RequestDao.ATTENDANCE, RequestType.UPDATEDATA, object); }

                handler.handle(request);
                Stage s = (Stage) save.getScene().getWindow();
                s.close();
            });

            Stage stage = new Stage();
            stage.setTitle(Translator.getString("modif.title") + " " + viewing);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            refreshPage(event);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
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
