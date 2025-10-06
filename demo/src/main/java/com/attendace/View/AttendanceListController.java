package com.attendace.View;

import com.attendace.Model.UserModel;
import com.attendace.View.Classes.AttendanceContainer;
import com.attendace.View.Classes.CourseContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class AttendanceListController {
    @FXML
    private GridPane attendancegrid;

    public void fillattendance(ArrayList<UserModel> users) throws IOException {
        int column = 0;
        int row = 0;

        for (UserModel user : users) {
            AttendanceContainer attendanceContainer = null;
            try {
                attendanceContainer = new AttendanceContainer(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (column == 2) {
                column = 0;
                row++;
            }

            attendancegrid.add(attendanceContainer.getNode(), column++, row);
        }
    }
}
