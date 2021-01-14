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
 * LegislationDaoDBTest
 * Tests the LegislationDaoDB class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class LegislationDaoDBTest {

    private final LegislationDao legislationDao;
    private final ReviewDao reviewDao;

    /**
     * Constructs the LegislationDaoDBTest class
     * @param legislationDao a LegislationDao object
     * @param reviewDao a ReviewDao object
     */
    @Autowired
    public LegislationDaoDBTest(LegislationDao legislationDao, ReviewDao reviewDao) {
        this.legislationDao = legislationDao;
        this.reviewDao = reviewDao;
    }

    /**
     * Deletes all data from the database before each test
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

    }

    /**
     * Tests the add() and getLegislation() methods by adding legislation to the database,
     * retrieving the legislation from the database, and asserting that the added and retrieved
     * legislation must be equal
     */
    @Test
    void testAddGetLegislation() {
        Legislation legislation = new Legislation();
        legislation.setTitle("Title");
        legislation.setActive(true);
        legislation.setSummary("Summary");
        legislation.setAvgRating(1.0);

        legislation = legislationDao.add(legislation);
        Legislation fromDatabase = legislationDao.getLegislation(legislation.getLegislationID());

        assertEquals(legislation, fromDatabase);
    }

    /**
     * Tests the getAllLegislation() method by adding three Legislation objects to the database and
     * asserting that the method must return all three Legislation objects
     */
    @Test
    void testGetAllLegislation() {
        Legislation legislation1 = new Legislation();
        legislation1.setTitle("Title1");
        legislation1.setActive(true);
        legislation1.setSummary("Summary1");

        Legislation legislation2 = new Legislation();
        legislation2.setTitle("Title2");
        legislation2.setActive(false);
        legislation2.setSummary("Summary2");

        Legislation legislation3 = new Legislation();
        legislation3.setTitle("Title3");
        legislation3.setActive(true);
        legislation3.setSummary("Summary3");

        legislation1 = legislationDao.add(legislation1);
        legislation2 = legislationDao.add(legislation2);
        legislation3 = legislationDao.add(legislation3);

        List<Legislation> allLegislation = legislationDao.getAllLegislation();
        assertEquals(3, allLegislation.size());
        assertTrue(allLegislation.contains(legislation1));
        assertTrue(allLegislation.contains(legislation2));
        assertTrue(allLegislation.contains(legislation3));
    }

    /**
     * Tests the update() method by adding legislation, copying the legislation, updating the
     * legislation, and asserting that the updated and copied legislation must not be equal
     */
    @Test
    void testUpdate() {
        Legislation legislation = new Legislation();
        legislation.setTitle("Title");
        legislation.setActive(true);
        legislation.setSummary("Summary");

        legislation = legislationDao.add(legislation);
        Legislation fromDatabase = legislationDao.getLegislation(legislation.getLegislationID());

        legislation.setTitle("ChangedTitle");
        legislation.setActive(false);
        legislation.setSummary("ChangedSummary");

        legislationDao.update(legislation);

        assertNotEquals(legislation, fromDatabase);
    }

    /**
     * Tests the delete() method by adding legislation, deleting the legislation, then asserting
     * that the deleted legislation must be null
     */
    @Test
    void testDelete() {
        Legislation legislation = new Legislation();
        legislation.setTitle("Title");
        legislation.setActive(true);
        legislation.setSummary("Summary");

        legislation = legislationDao.add(legislation);

        legislationDao.delete(legislation.getLegislationID());
        assertNull(legislationDao.getLegislation(legislation.getLegislationID()));
    }

}
