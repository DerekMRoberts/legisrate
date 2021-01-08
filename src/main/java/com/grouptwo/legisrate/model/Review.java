package com.grouptwo.legisrate.model;

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
    private int legislationID;
    private int userID;
    private String comments;
    private int rating;

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getLegislationID() {
        return legislationID;
    }

    public void setLegislationID(int legislationID) {
        this.legislationID = legislationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewID == review.reviewID && legislationID == review.legislationID && userID == review.userID && rating == review.rating && Objects.equals(comments, review.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewID, legislationID, userID, comments, rating);
    }

}