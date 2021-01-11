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
 * UserDaoDBTest
 * Tests the UserDaoDB class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class UserDaoDBTest {

    private final LegislationDao legislationDao;
    private final ReviewDao reviewDao;
    private final UserDao userDao;

    /**
     * Constructs the UserDaoDBTest class
     * @param legislationDao a LegislationDao object
     * @param reviewDao a ReviewDao object
     * @param userDao a UserDao object
     */
    @Autowired
    public UserDaoDBTest(LegislationDao legislationDao, ReviewDao reviewDao, UserDao userDao) {
        this.legislationDao = legislationDao;
        this.reviewDao = reviewDao;
        this.userDao = userDao;
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

        List<User> allUsers = userDao.getAllUsers();
        for (User user : allUsers) {
            userDao.delete(user.getUserID());
        }
    }

    /**
     * Tests the add() and getUser() methods by adding a user to the database,
     * retrieving the user from the database, and asserting that the added and retrieved
     * users must be equal
     */
    @Test
    void testAddGetUser() {
        User user = new User();
        user.setUsername("Username");
        user.setState("IL");
//        user.setEmail("email@email.com");
//        user.setPassword("########");

        user = userDao.add(user);
        User fromDatabase = userDao.getUser(user.getUserID());

        assertEquals(user, fromDatabase);
    }

    /**
     * Tests the getAllUsers() method by adding three users to the database and
     * asserting that the method must return all three users
     */
    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("Username1");
        user1.setState("IL");
//        user1.setEmail("email1@email.com");
//        user1.setPassword("#######1");

        User user2 = new User();
        user2.setUsername("Username2");
        user2.setState("NY");
//        user2.setEmail("email2@email.com");
//        user2.setPassword("#######2");

        User user3 = new User();
        user3.setUsername("Username3");
        user3.setState("KY");
//        user3.setEmail("email3@email.com");
//        user3.setPassword("#######3");

        user1 = userDao.add(user1);
        user2 = userDao.add(user2);
        user3 = userDao.add(user3);

        List<User> allUsers = userDao.getAllUsers();
        assertEquals(3, allUsers.size());
        assertTrue(allUsers.contains(user1));
        assertTrue(allUsers.contains(user2));
        assertTrue(allUsers.contains(user3));
    }

    /**
     * Tests the update() method by adding a user, copying the user, updating the
     * user, and asserting that the updated and copied users must not be equal
     */
    @Test
    void testUpdate() {
        User user = new User();
        user.setUsername("Username");
        user.setState("IL");
//        user.setEmail("email@email.com");
//        user.setPassword("########");

        user = userDao.add(user);
        User fromDatabase = userDao.getUser(user.getUserID());

        user.setUsername("ChangedUsername");
        user.setState("AK");
//        user.setEmail("changedemail@email.com");
//        user.setPassword("#######4");

        userDao.update(user);

        assertNotEquals(user, fromDatabase);
    }

    /**
     * Tests the delete() method by adding a user, deleting the user, then asserting
     * that the deleted user must be null
     */
    @Test
    void testDelete() {
        User user = new User();
        user.setUsername("Username");
        user.setState("IL");
//        user.setEmail("email@email.com");
//        user.setPassword("########");

        user = userDao.add(user);

        userDao.delete(user.getUserID());
        assertNull(userDao.getUser(user.getUserID()));
    }

}
