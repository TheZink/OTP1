package com.attendace.view.Classes;

import com.attendace.model.UserModel;
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
import java.util.Objects;

/**
 * Represents a container for displaying attendance information in the UI.
 * Loads and initializes the attendance FXML view with user data and localized strings.
 */
public class AttendanceContainer {
    /** The root node of the attendance container. */
    private Node node;

    /**
     * Constructs an AttendanceContainer for the given user.
     * Loads the FXML layout, sets localized text, and populates user information.
     *
     * @param user the user whose attendance information is displayed
     * @throws IOException if the FXML file cannot be loaded
     */

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

    /**
     * Returns the root node of this attendance container.
     *
     * @return the root Node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Returns the attendance menu button from the container.
     *
     * @return the attendance MenuButton as a Button
     */
    public Button getMenu() {
        return (Button) node.lookup("#attendancemenu");
    }
}
