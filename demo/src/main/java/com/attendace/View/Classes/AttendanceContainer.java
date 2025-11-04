package com.attendace.View.Classes;

import com.attendace.Model.UserModel;
import com.attendace.localisation.Translator;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AttendanceContainer {
    private Node node;

    public AttendanceContainer(UserModel user) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/AttendanceContainer.fxml"))
        );

        MenuButton attendancemenu = (MenuButton) root.lookup("#attendancemenu");
        attendancemenu.setText(Translator.getString("attendance.menubutton"));

        ObservableList<MenuItem> items  = attendancemenu.getItems();
        for (MenuItem item : items) {
            item.setText(Translator.getString("attendance." + item.getId()));
        }

        Text attendancenametext = (Text) root.lookup("#attendancename");
        attendancenametext.setText(String.valueOf(user.getName()));

        Text attendancedegreetext = (Text) root.lookup("#attendancedegree");
        attendancedegreetext.setText(String.valueOf(user.getUserDegree()));

        Text attendanceidtext = (Text) root.lookup("#attendanceid");
        attendanceidtext.setText(String.valueOf(user.getStudentId()));

        this.node = root;
    }

    public Node getNode() {
        return node;
    }

    public Button getMenu() {
        return (Button) node.lookup("#attendancemenu");
    }
}
