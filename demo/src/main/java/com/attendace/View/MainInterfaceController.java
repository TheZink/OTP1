package com.attendace.View;

import com.attendace.Controller.CourseController;
import com.attendace.View.Classes.CourseContainer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.util.ArrayList;

public class MainInterfaceController {
    @FXML
    private GridPane coursegrid;

    public void fillcourses(ArrayList<ArrayList<Object>> courses) throws IOException {

            int column = 0;
            int row = 0;

            for (ArrayList<Object> course : courses) {
                CourseContainer courseContainer = null;
                try {
                    courseContainer = new CourseContainer(course);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (column == 2) {
                    column = 0;
                    row++;
                }

                coursegrid.add(courseContainer.getNode(), column++, row);
            }

    }


}
