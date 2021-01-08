package com.grouptwo.legisrate.controller;

import com.grouptwo.legisrate.data.LegislationDao;
import com.grouptwo.legisrate.data.ReviewDao;
import com.grouptwo.legisrate.data.UserDao;
import com.grouptwo.legisrate.model.Legislation;
import com.grouptwo.legisrate.model.Review;
import com.grouptwo.legisrate.model.User;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * LegislationController
 * Controls the REST web service interface
 */
@RestController
@RequestMapping("/api")
public class LegislationController {

    private final LegislationDao legislationDao;
    private final ReviewDao reviewDao;
    private final UserDao userDao;

    /**
     * Constructs the LegislationController class
     * @param legislationDao the legislation data-access-object class
     * @param reviewDao the review data-access-object class
     * @param userDao the user data-access-object class
     */
    public LegislationController(LegislationDao legislationDao, ReviewDao reviewDao, UserDao userDao) {
        this.legislationDao = legislationDao;
        this.reviewDao = reviewDao;
        this.userDao = userDao;
    }

    /**
     * Creates a REST endpoint for adding new legislation to the database
     * @return an http 201 CREATED message as well as the created Legislation object
     */
    @PostMapping("/legislation")
    @ResponseStatus(HttpStatus.CREATED)
    public Legislation addLegislation(@RequestBody Legislation legislation) {
        return legislationDao.add(legislation);
    }

    /**
     * Creates a REST endpoint for adding new reviews to the database
     * @return an http 201 CREATED message as well as the created Review object
     */
    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public Review addReview(@RequestBody Review review) {
        return reviewDao.add(review);
    }

    /**
     * Creates a REST endpoint for adding new users to the database
     * @return an http 201 CREATED message as well as the created User object
     */
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return userDao.add(user);
    }

    /**
     * Creates a REST endpoint for getting a list of all legislation
     * @return the list of all legislation
     */
    @GetMapping("/legislation")
    public List<Legislation> getAllLegislation() {
        return legislationDao.getAllLegislation();
    }

    /**
     * Creates a REST endpoint for getting a list of all reviews
     * @return the list of all reviews
     */
    @GetMapping("/review")
    public List<Review> getAllReviews() {
        return reviewDao.getAllReviews();
    }

    /**
     * Creates a REST endpoint for getting a list of all users
     * @return the list of all users
     */
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Creates a REST endpoint for getting a specified legislation
     * @return the specified legislation
     */
    @GetMapping("/legislation/{legislationId}")
    public ResponseEntity getLegislation(@PathVariable int legislationId) {
        if (legislationDao.getLegislation(legislationId) == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(legislationDao.getLegislation(legislationId));
    }

    /**
     * Creates a REST endpoint for getting a specified review
     * @return the specified review
     */
    @GetMapping("/review/{reviewId}")
    public ResponseEntity getReview(@PathVariable int reviewId) {
        if (reviewDao.getReview(reviewId) == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(reviewDao.getReview(reviewId));
    }

    /**
     * Creates a REST endpoint for getting a specified user
     * @return the specified user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity getUser(@PathVariable int userId) {
        if (userDao.getUser(userId) == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(userDao.getUser(userId));
    }

//    @PutMapping("/legislation/{legislationId}")
//    public ResponseEntity updateLegislation(@PathVariable int legislationId, @RequestBody Legislation legislation) {
//        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
//        if (legislationId != legislationDao.getId()) {
//            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//        else if (dao.update(todo)) {
//            response = new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return response;
//    }





    /**
     * Creates a REST endpoint for getting a specified legislation
     * @return the specified legislation
     */
    @DeleteMapping("/legislation/{legislationID}")
    public ResponseEntity deleteLegislation(@PathVariable int legislationID) {
        if (legislationDao.delete(legislationID))
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a REST endpoint for getting a specified review
     * @return the specified review
     */
    @DeleteMapping("/review/{reviewID}")
    public ResponseEntity deleteReview(@PathVariable int reviewID) {
        if (reviewDao.delete(reviewID))
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a REST endpoint for getting a specified user
     * @return the specified user
     */
    @DeleteMapping("/user/{userID}")
    public ResponseEntity deleteUser(@PathVariable int userID) {
        if (userDao.delete(userID))
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
