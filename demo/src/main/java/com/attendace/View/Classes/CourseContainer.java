package com.attendace.View.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class CourseContainer {
    private Node node;

    public CourseContainer() throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/CourseContainer.fxml"))
        );

        this.node = root;
    }

    public Node getNode() {
        return node;
    }
}
