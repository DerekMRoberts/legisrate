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
 * Review
 * A model for Review objects
 */
public class Review {

    private int reviewID;
    @NotNull(message = "Legislation ID must not be empty.")
    private int legislationID;
    @NotNull(message = "User ID must not be empty.")
    private String comments;
    private int rating;
    @NotBlank(message = "Username must not be empty.")
    @Size(max = 30, message="Username must be fewer than 30 characters.")
    private String username;
    @Size(min = 2, max = 2, message="State must be 2 characters.")
    private String state;

    /**
     * Gets the review ID
     * @return the review ID
     */
    public int getReviewID() {
        return reviewID;
    }

    /**
     * Sets the review ID
     * @param reviewID the review ID
     */
    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

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
     * Gets the review comments
     * @return the review comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the review comments
     * @param comments the review comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the review rating
     * @return the review rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the review rating
     * @param rating the review rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Determines whether two Review objects are equal
     * @param o the object to be compared with the current object
     * @return a boolean indicating whether the two objects are equal
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Review review = (Review) object;
        return reviewID == review.reviewID && legislationID == review.legislationID && rating == review.rating && java.util.Objects.equals(comments, review.comments) && java.util.Objects.equals(username, review.username) && java.util.Objects.equals(state, review.state);
    }

    /**
     * Overrides the hashCode() method
     * @return the object as a hash
     */
    public int hashCode() {
        return Objects.hash(super.hashCode(), reviewID, legislationID, comments, rating, username, state);
    }

    /**
     * Overrides the toString() method
     * @return the object as a String
     */
    @java.lang.Override
    public java.lang.String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", legislationID=" + legislationID +
                ", comments='" + comments + '\'' +
                ", rating=" + rating +
                ", username='" + username + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}
