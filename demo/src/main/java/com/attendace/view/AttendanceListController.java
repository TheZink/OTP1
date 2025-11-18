package com.attendace.view;

import com.attendace.model.UserModel;
import com.attendace.view.Classes.AttendanceContainer;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceListController {
    @FXML
    private GridPane attendancegrid;

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
