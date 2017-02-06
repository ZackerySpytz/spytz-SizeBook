package com.example.spytz_sizebook;

/**
 * Created by spytz on 2017-02-03.
 */

/**
 * This class holds all of the data associated with a particular person
 * that has been added as a record to the SizeBook app.
 */
public class PersonRecord {

    private String name;
    private String recordDate = ""; // The day when the dimensions were taken
    private double neck;
    private double bust;
    private double chest;
    private double waist;
    private double hip;
    private double inseam;
    private String comment = "";

    /**
     * Constructor.
     * @param name
     */
    public PersonRecord(String name) {
        this.name = name;
    }

    /**
     * Return the string representation of the PersonRecord object,
     * mainly for the way it is formatted in the ListView.
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

    // Getters and Setters.
    public double getBust() {
        return bust;
    }

    public void setBust(double bust) {
        this.bust = bust;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getInseam() {
        return inseam;
    }

    public void setInseam(double inseam) {
        this.inseam = inseam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }
}
