package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.TestApplicationConfiguration;
import com.grouptwo.legisrate.model.Legislation;
import com.grouptwo.legisrate.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * ReviewDaoDBTest
 * Tests the ReviewDaoDB class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class ReviewDaoDBTest {

    private final LegislationDao legislationDao;
    private final ReviewDao reviewDao;
    private int legislationID;
    private int userID;


    /**
     * Constructs the ReviewDaoDBTest class
     * @param legislationDao a LegislationDao object
     * @param reviewDao a ReviewDao object
     */
    @Autowired
    public ReviewDaoDBTest(LegislationDao legislationDao, ReviewDao reviewDao) {
        this.legislationDao = legislationDao;
        this.reviewDao = reviewDao;
    }

    /**
     * Deletes all data from the database and adds a legislation and a user to the database before each test
     */
    @BeforeEach
    void setUp() {
        List<Review> allReviews = reviewDao.getAllReviews();
        for (Review review : allReviews) {
            reviewDao.delete(review.getReviewID());
        }

        List<Legislation> allLegislation = legislationDao.getAllLegislation();
        for (Legislation legislation : allLegislation) {
            legislationDao.delete(legislation.getLegislationID());
        }

        Legislation legislation = new Legislation();
        legislation.setTitle("Title");
        legislation.setActive(true);
        legislation.setSummary("Summary");
        legislation = legislationDao.add(legislation);
        legislationID = legislation.getLegislationID();
    }

    /**
     * Tests the add() and getReview() methods by adding a review to the database,
     * retrieving the review from the database, and asserting that the added and retrieved
     * reviews must be equal
     */
    @Test
    void testAddGetReview() {
        Review review = new Review();
        review.setLegislationID(legislationID);
        review.setComments("Comments");
        review.setRating(1);
        review.setUsername("Username");
        review.setState("NY");

        review = reviewDao.add(review);
        Review fromDatabase = reviewDao.getReview(review.getReviewID());

        assertEquals(review, fromDatabase);
    }

    /**
     * Tests the getAllReviews() method by adding three reviews to the database and
     * asserting that the method must return all three reviews
     */
    @Test
    void testGetAllReviews() {
        Review review1 = new Review();
        review1.setLegislationID(legislationID);
        review1.setComments("Comments1");
        review1.setRating(1);
        review1.setUsername("Username1");
        review1.setState("NY");

        Review review2 = new Review();
        review2.setLegislationID(legislationID);
        review2.setComments("Comments2");
        review2.setRating(2);
        review2.setUsername("Username2");
        review2.setState("IL");

        Review review3 = new Review();
        review3.setLegislationID(legislationID);
        review3.setComments("Comments3");
        review3.setRating(3);
        review3.setUsername("Username3");
        review3.setState("KY");

        review1 = reviewDao.add(review1);
        review2 = reviewDao.add(review2);
        review3 = reviewDao.add(review3);

        List<Review> allReviews = reviewDao.getAllReviews();
        assertEquals(3, allReviews.size());
        assertTrue(allReviews.contains(review1));
        assertTrue(allReviews.contains(review2));
        assertTrue(allReviews.contains(review3));
    }

    /**
     * Tests the update() method by adding a review, copying the review, updating the
     * review, and asserting that the updated and copied reviews must not be equal
     */
    @Test
    void testUpdate() {
        Review review = new Review();
        review.setLegislationID(legislationID);
        review.setComments("Comments");
        review.setRating(4);
        review.setUsername("Username");
        review.setState("NY");

        review = reviewDao.add(review);
        Review fromDatabase = reviewDao.getReview(review.getReviewID());

        review.setComments("ChangedComments");
        review.setRating(5);
        review.setUsername("Username");
        review.setState("NY");

        reviewDao.update(review);

        assertNotEquals(review, fromDatabase);
    }

    /**
     * Tests the delete() method by adding a review, deleting the review, then asserting
     * that the deleted review must be null
     */
    @Test
    void testDelete() {
        Review review = new Review();
        review.setLegislationID(legislationID);
        review.setComments("Comments");
        review.setRating(1);
        review.setUsername("Username");
        review.setState("NY");

        review = reviewDao.add(review);

        reviewDao.delete(review.getReviewID());
        assertNull(reviewDao.getReview(review.getReviewID()));
    }

}
