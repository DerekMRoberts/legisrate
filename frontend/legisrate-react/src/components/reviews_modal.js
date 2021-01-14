import React, { Component } from "react";
import { Form, Button, Modal } from "react-bootstrap";
import ReviewForm from './reviews_form'


class ReviewModal extends Component {
    render() {
        let {reviewData, reviewErrors, handleSubmit, handleChange, show, handleClose} = this.props
        return (
            <Modal show={show} onHide={handleClose} animation={false}>
                <Modal.Dialog>
                    <Modal.Header closeButton>
                        <Modal.Title># {reviewData.reviewId}</Modal.Title>
                    </Modal.Header>
                    
                    <Modal.Body> 
                        <Form>
                            <Form.Group controlId='newReview'>
                                <Form.Label>New Review:</Form.Label>
                                <Form.Control type='text' 
                                placeholder='Add review' 
                                name='review'
                                value={reviewData.review}
                                onChange={handleChange}
                                isInvalid={!!reviewErrors.review}/>
                                <Form.Control.Feedback type="invalid">
                                    {reviewErrors.review}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    
                    <Modal.Footer>
                        <Button variant='secondary' onClick={handleClose}>Close</Button>
                        <Button variant='primary' onClick={handleSubmit} value={reviewData.reviewId}>Submit Review</Button>
                    </Modal.Footer>
                </Modal.Dialog>
            </Modal>
        )
    }
}

export default ReviewModal