package com.attendace.View;

import com.attendace.Controller.CourseController;
import com.attendace.View.Classes.CourseContainer;
import com.sun.jna.platform.win32.PsapiUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;

public class MainInterfaceController {
    @FXML
    private GridPane coursegrid;


    public void initialize() throws IOException {
        CourseContainer courseContainer = new CourseContainer();
        coursegrid.add(courseContainer.getNode(), 1, 1);

        CourseContainer courseContainer2 = new CourseContainer();
        coursegrid.add(courseContainer2.getNode(), 1, 2);


    }
}
