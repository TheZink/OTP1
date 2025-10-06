package com.attendace.View;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.Action;

import javafx.beans.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class AdminInterfaceController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminPage.fxml"));
        primaryStage.setTitle("Admin Interface");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    Handler handler = new DefaultHandler();
    Map<String, Object> object = new HashMap<>();

    @FXML
    Button viewStaff, createStaff, modifyStaff, deleteStaff;

    @FXML
    Button viewStudent, createStudent, modifyStudent, deleteStudent;

    @FXML
    Button viewCourse, createCourse, modifyCourse, deleteCourse;

    @FXML
    Button viewAtten, modifyAtten;

    @FXML
    Button saveButton, cancelButton;

    @FXML
    private ListView<String> listView;

    // -- VIEW HANDLERS -- 
    
    @FXML
    private void handleViewStaff(ActionEvent event) {

        listView.getItems().clear();

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




}
