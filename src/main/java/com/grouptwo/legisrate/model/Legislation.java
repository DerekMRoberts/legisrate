package com.grouptwo.legisrate.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * Legislation
 * A model for Legislation objects
 */
public class Legislation {

    private int legislationID;
    @NotBlank(message = "Legislation title must not be empty.")
    @Size(max = 45, message = "Legislation title must be fewer than 45 characters")
    private String title;
    @NotBlank(message = "Legislation summary must not be empty.")
    @Size(max = 90, message = "Legislation summary must be fewer than 90 characters")
    private String summary;
    private boolean active;
    private double avgRating;

    /**
     * Gets the legislation ID
     * @return the legislation ID
     */
    public int getLegislationID() {
        return legislationID;
    }

    /**
     * Sets the legislation ID
     * @param legislationID the legislation ID
     */
    public void setLegislationID(int legislationID) {
        this.legislationID = legislationID;
    }

    /**
     * Gets the legislation title
     * @return the legislation title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the legislation title
     * @param title the legislation title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the legislation summary
     * @return the legislation summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the legislation summary
     * @param summary the legislation summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets a boolean indicating whether the legislation is active
     * @return a boolean indicating whether the legislation is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets a boolean indicating whether the legislation is active
     * @param active a boolean indicating whether the legislation is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the average rating of the legislation
     * @return the average rating of the legislation
     */
    public double getAvgRating() {
        return avgRating;
    }

    /**
     * Sets the average rating of the legislation
     * @param avgRating the average rating of the legislation
     */
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    /**
     * Determines whether two Legislation objects are equal
     * @param o the object to be compared with the current object
     * @return a boolean indicating whether the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Legislation that = (Legislation) o;
        return legislationID == that.legislationID && active == that.active && Double.compare(that.avgRating, avgRating) == 0 && Objects.equals(title, that.title) && Objects.equals(summary, that.summary);
    }

    /**
     * Overrides the hashCode() method
     * @return the object as a hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(legislationID, title, summary, active, avgRating);
    }

    /**
     * Overrides the toString() method
     * @return the object as a String
     */
    @Override
    public String toString() {
        return "Legislation{" +
                "legislationID=" + legislationID +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", active=" + active +
                ", avgRating=" + avgRating +
                '}';
    }

}
