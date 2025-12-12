package com.attendace.view;

import com.attendace.model.UserModel;
import com.attendace.view.Classes.AttendanceContainer;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

/**
 * Controller for displaying a list of user attendance entries.
 * Populates the attendance grid with user attendance containers.
 */
public class AttendanceListController {
    /** The grid pane that holds attendance containers. */
    @FXML
    private GridPane attendancegrid;

    /**
     * Fills the attendance grid with attendance containers for each user.
     *
     * @param users the list of users to display attendance for
     * @throws IOException if loading an attendance container fails
     */
    public void fillattendance(List<UserModel> users) throws IOException {
        int column = 0;
        int row = 0;

        for (UserModel user : users) {
            AttendanceContainer attendanceContainer = null;
            try {
                attendanceContainer = new AttendanceContainer(user);
            } catch (IOException e) {
                throw new IOException(e);
            }
            if (column == 2) {
                column = 0;
                row++;
            }

            attendancegrid.add(attendanceContainer.getNode(), column++, row);
        }
    }
}
