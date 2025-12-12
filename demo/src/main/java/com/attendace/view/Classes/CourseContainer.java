package com.attendace.view.Classes;

import com.attendace.localisation.Translator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Represents a UI container for displaying course information.
 * Loads and initializes the course FXML view with localized strings and course data.
 */
public class CourseContainer {
    /** The root node of the course container. */
    private Node node;

    /**
     * Constructs a CourseContainer with the given course data.
     * Loads the FXML layout, sets localized text, and populates course information.
     *
     * @param course a list containing course data; expects the course name at index 1
     * @throws IOException if the FXML file cannot be loaded
     */

    public CourseContainer(List<Object> course) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/CourseContainer.fxml"))
        );

        Button coursebutton = (Button) root.lookup("#attendancebutton");
        coursebutton.setText(Translator.getString("course.button"));

        Text coursenametext = (Text) root.lookup("#coursename");
        coursenametext.setText(String.valueOf(course.get(1)));

        Text courseteachertext = (Text) root.lookup("#courseteacher");
        courseteachertext.setText("Opettaja: " + "NULL");

        if (Translator.getLocale().toString().equals("fa_IR")) {
            coursenametext.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
            courseteachertext.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        } else {
            coursenametext.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
            courseteachertext.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        }

        this.node = root;
    }

    /**
     * Returns the root node of this course container.
     *
     * @return the root Node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Returns the attendance button from the container.
     *
     * @return the attendance Button
     */
    public Button getAttendaceButton() {
        return (Button) node.lookup("#attendancebutton");
    }
}
