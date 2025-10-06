package com.attendace.View.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CourseContainer {
    private Node node;

    public CourseContainer(ArrayList<Object> course) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/CourseContainer.fxml"))
        );

        Text coursenametext = (Text) root.lookup("#coursename");
        coursenametext.setText(String.valueOf(course.get(1)));

        Text courseteachertext = (Text) root.lookup("#courseteacher");
        courseteachertext.setText("Opettaja: " + "NULL");

        for (Object obj : course) {
            System.out.println(obj);
        }

        this.node = root;
    }

    public Node getNode() {
        return node;
    }
}
