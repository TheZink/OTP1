package com.attendace.model;

public class DegreeModel {
    private String degreeName;
    private int ects;

    public DegreeModel(String degreeName, int ects) {
        this.degreeName = degreeName;
        this.ects = ects;
    }
    public String getName() {
        return degreeName;
    }
    public int getEcts() {
        return ects;
    }
}
