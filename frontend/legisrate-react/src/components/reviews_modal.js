import React, { Component } from "react";
import { Form, Button, Modal } from "react-bootstrap";
import ReviewForm from './reviews_form'


class ReviewModal extends Component {
    render() {
        let {reviewData, contactErrors, handleSubmit, handleChange, show, handleClose} = this.props
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
                                pattern="[a-z]{300}"
                                isInvalid={!!contactErrors.review}/>
                                <Form.Control.Feedback type="invalid">
                                  {contactErrors.review}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Form>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant='secondary' onClick={handleClose}>Close</Button>
                        <Button variant='primary' onClick={handleSubmit} value={reviewData.ReviewId}>Save changes</Button>
                    </Modal.Footer>
                </Modal.Dialog>
            </Modal>
        )
    }
}
export default ReviewModal