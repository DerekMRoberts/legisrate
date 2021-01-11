package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.TestApplicationConfiguration;
import com.grouptwo.legisrate.model.Legislation;
import com.grouptwo.legisrate.model.Review;
import com.grouptwo.legisrate.model.User;
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
    private final UserDao userDao;
    private int legislationID;
    private int userID;


    /**
     * Constructs the ReviewDaoDBTest class
     * @param legislationDao a LegislationDao object
     * @param reviewDao a ReviewDao object
     * @param userDao a UserDao object
     */
    @Autowired
    public ReviewDaoDBTest(LegislationDao legislationDao, ReviewDao reviewDao, UserDao userDao) {
        this.legislationDao = legislationDao;
        this.reviewDao = reviewDao;
        this.userDao = userDao;
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

        List<User> allUsers = userDao.getAllUsers();
        for (User user : allUsers) {
            userDao.delete(user.getUserID());
        }

        Legislation legislation = new Legislation();
        legislation.setTitle("Title");
        legislation.setActive(true);
        legislation.setSummary("Summary");
//        legislation.setSponsor("Sponsor");
//        legislation.setPdfUrl("http://www.pdf.url");
        legislation = legislationDao.add(legislation);
        legislationID = legislation.getLegislationID();

        User user = new User();
        user.setUsername("Username");
        user.setState("IL");
//        user.setEmail("email@email.com");
//        user.setPassword("########");
        user = userDao.add(user);
        userID = user.getUserID();
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
        review.setUserID(userID);
        review.setComments("Comments");
        review.setRating(1);

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
        review1.setUserID(userID);
        review1.setComments("Comments1");
        review1.setRating(1);

        Review review2 = new Review();
        review2.setLegislationID(legislationID);
        review2.setUserID(userID);
        review2.setComments("Comments2");
        review2.setRating(2);

        Review review3 = new Review();
        review3.setLegislationID(legislationID);
        review3.setUserID(userID);
        review3.setComments("Comments3");
        review3.setRating(3);

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
        review.setUserID(userID);
        review.setComments("Comments");
        review.setRating(4);

        review = reviewDao.add(review);
        Review fromDatabase = reviewDao.getReview(review.getReviewID());

        review.setComments("ChangedComments");
        review.setRating(5);

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
        review.setUserID(userID);
        review.setComments("Comments");
        review.setRating(1);

        review = reviewDao.add(review);

        reviewDao.delete(review.getReviewID());
        assertNull(reviewDao.getReview(review.getReviewID()));
    }

}
