package com.attendace.Model;

public class StaffModel {


    private int staffId;
    private String name;
    private String staffRole;


    public StaffModel(int staffId, String name, String staffRole) {
        this.staffId = staffId;
        this.name = name;
        this.staffRole = staffRole;

    }
    public int getStudentId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getUserDegree() {
        return staffRole;
    }
}
