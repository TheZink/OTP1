package com.attendace.model;

/**
 * Represents a staff member in the system, including their ID, name, role, admin status, and language preference.
 */
public class StaffModel {

    /** The unique identifier for the staff member. */
    private int staffId;

    /** The name of the staff member. */
    private String name;

    /** The role of the staff member (e.g., teacher, assistant). */
    private String staffRole;

    /** Indicates whether the staff member has admin privileges. */
    private boolean admin;

    /** The language preference of the staff member. */
    private String lang;

    /**
     * Constructs a new StaffModel with the specified details.
     *
     * @param staffId   the unique identifier for the staff member
     * @param name      the name of the staff member
     * @param staffRole the role of the staff member
     * @param admin     whether the staff member has admin privileges
     * @param lang      the language preference of the staff member
     */
    public StaffModel(int staffId, String name, String staffRole, boolean admin, String lang) {
        this.staffId = staffId;
        this.name = name;
        this.staffRole = staffRole;
        this.admin = admin;
        this.lang = lang;
    }

    /**
     * Gets the unique identifier for the staff member.
     *
     * @return the staff ID
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * Gets the name of the staff member.
     *
     * @return the staff member's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the role of the staff member.
     *
     * @return the staff member's role
     */
    public String getStaffRole() {
        return staffRole;
    }

    /**
     * Checks if the staff member has admin privileges.
     *
     * @return true if the staff member is an admin, false otherwise
     */
    public boolean getAdminStatus() {
        return admin;
    }
}