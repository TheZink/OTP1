package com.attendace.Model;

public class StaffModel {


    private int staffId;
    private String name;
    private String staffRole;
    private String admin;


    public StaffModel(int staffId, String name, String staffRole, String admin) {
        this.staffId = staffId;
        this.name = name;
        this.staffRole = staffRole;
        this.admin = admin;

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

    public String getAdminStatus() {
        return admin;
    }
}
