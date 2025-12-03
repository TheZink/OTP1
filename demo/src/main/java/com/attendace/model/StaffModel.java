package com.attendace.model;

public class StaffModel {


    private int staffId;
    private String name;
    private String staffRole;
    private boolean admin;
    private String lang;


    public StaffModel(int staffId, String name, String staffRole, boolean admin, String lang) {
        this.staffId = staffId;
        this.name = name;
        this.staffRole = staffRole;
        this.admin = admin;
        this.lang = lang;

    }
    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public boolean getAdminStatus() {
        return admin;
    }
}
