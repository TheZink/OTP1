package com.attendace.Controller;

import com.attendace.Model.DegreeModel;

public class DegreeController {
    private DegreeModel degreeModel;

    //add dao later
    public DegreeController() {

    }
    public void addDegree(String name, int ects) {
        degreeModel = new DegreeModel(name, ects);
        System.out.println("created a new degree");
    }
    public DegreeModel getDegree() {
        return degreeModel;
    }
}
