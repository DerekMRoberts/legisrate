package com.grouptwo.legisrate.controller;

import com.grouptwo.legisrate.data.LegislationDao;
import com.grouptwo.legisrate.data.ReviewDao;
import com.grouptwo.legisrate.exception.InvalidLegislationException;
import com.grouptwo.legisrate.exception.InvalidReviewException;
import com.grouptwo.legisrate.model.Legislation;
import com.grouptwo.legisrate.model.Review;

import java.util.List;
import javax.validation.Valid;

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

    /**
     * Constructs the LegislationController class
     * @param legislationDao the legislation data-access-object class
     * @param reviewDao the review data-access-object class
     */
    public LegislationController(LegislationDao legislationDao, ReviewDao reviewDao) {
        this.legislationDao = legislationDao;
        this.reviewDao = reviewDao;
    }

    /**
     * Creates a REST endpoint for adding new legislation to the database
     * @return the added legislation and the http 201 Created status code
     */
    @PostMapping("/legislation")
    @ResponseStatus(HttpStatus.CREATED)
    public Legislation addLegislation(@Valid @RequestBody Legislation legislation) {
        return legislationDao.add(legislation);
    }

    /**
     * Creates a REST endpoint for adding a new review to the database
     * @return the added review and the http 201 Created status code
     */
    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public Review addReview(@Valid @RequestBody Review review) throws InvalidReviewException {
        if (legislationDao.getLegislation(review.getLegislationID()) == null ||
                review.getRating() < 1 || review.getRating() > 5)
            throw new InvalidReviewException(review.toString());
        else
            return reviewDao.add(review);
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
     * Creates a REST endpoint for getting a specified legislation
     * @return the specified legislation if successful, otherwise the http 404 Not Found status code
     */
    @GetMapping("/legislation/{legislationID}")
    public ResponseEntity<Legislation> getLegislation(@PathVariable int legislationID) {
        if (legislationDao.getLegislation(legislationID) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(legislationDao.getLegislation(legislationID));
    }

    /**
     * Creates a REST endpoint for getting a specified review
     * @return the specified review if successful, otherwise the http 404 Not Found status code
     */
    @GetMapping("/review/{legislationID}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable int legislationID) {
        if (reviewDao.getReviewsByLegislationID(legislationID) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(reviewDao.getReviewsByLegislationID(legislationID));
    }

    /**
     * Creates a REST endpoint for updating a specified legislation
     * @return the updated legislation if successful, otherwise the http 404 Not Found status code
     */
    @PutMapping("/legislation/{legislationID}")
    public ResponseEntity<Legislation> updateLegislation(@PathVariable int legislationID, @Valid @RequestBody Legislation legislation) throws InvalidLegislationException {
        if (legislationID != legislation.getLegislationID())
            throw new InvalidLegislationException(legislation.toString());
        if (!legislationDao.update(legislation))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(legislationDao.getLegislation(legislationID));
    }

    /**
     * Creates a REST endpoint for updating a specified review
     * @return the updated review if successful, otherwise the http 404 Not Found status code
     */
    @PutMapping("/review/{reviewID}")
    public ResponseEntity<Review> updateReview(@PathVariable int reviewID, @Valid @RequestBody Review review) throws InvalidReviewException {
        if (reviewID != review.getReviewID() || legislationDao.getLegislation(review.getLegislationID()) == null || review.getRating() < 1 || review.getRating() > 5)
            throw new InvalidReviewException(review.toString());
        if (!reviewDao.update(review))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(reviewDao.getReview(reviewID));
    }

    /**
     * Creates a REST endpoint for deleting a specified legislation
     * @return the http 204 No Content status code if successful, otherwise the http 404 Not Found status code
     */
    @DeleteMapping("/legislation/{legislationID}")
    public ResponseEntity<Error> deleteLegislation(@PathVariable int legislationID) {
        if (!legislationDao.delete(legislationID))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Creates a REST endpoint for deleting a specified review
     * @return the http 204 No Content status code if successful, otherwise the http 404 Not Found status code
     */
    @DeleteMapping("/review/{reviewID}")
    public ResponseEntity<Error> deleteReview(@PathVariable int reviewID) {
        if (reviewDao.delete(reviewID))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
