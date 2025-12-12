package com.attendace.model;

/**
 * Represents an academic degree with a name and ECTS credits.
 */
public class DegreeModel {

    /** The name of the degree. */
    private String degreeName;

    /** The number of ECTS credits for the degree. */
    private int ects;

    /**
     * Constructs a new DegreeModel with the specified degree name and ECTS credits.
     *
     * @param degreeName the name of the degree
     * @param ects the number of ECTS credits
     */
    public DegreeModel(String degreeName, int ects) {
        this.degreeName = degreeName;
        this.ects = ects;
    }

    /**
     * Gets the name of the degree.
     *
     * @return the degree name
     */
    public String getName() {
        return degreeName;
    }

    /**
     * Gets the number of ECTS credits for the degree.
     *
     * @return the ECTS credits
     */
    public int getEcts() {
        return ects;
    }
}