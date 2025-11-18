package com.attendace.controller;

import com.attendace.model.DegreeModel;

public class DegreeController {
    private DegreeModel degreeModel;


    public void addDegree(String name, int ects) {
        degreeModel = new DegreeModel(name, ects);
    }
    public DegreeModel getDegree() {
        return degreeModel;
    }
}
