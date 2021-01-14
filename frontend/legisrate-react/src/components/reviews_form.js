import React, { Component } from "react";
import {Form, Button} from 'react-bootstrap'

class ReviewsForm extends Component {
    render() {
        let {reviewData, reviewErrors, handleSubmit, handleChange} = this.props;
        return (
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId='newReview'>
                    <Form.Label>New Review:</Form.Label>
                    <Form.Control 
                    type='text' 
                    placeholder='Add review' 
                    name='review'
                    value={reviewData.review}
                    onChange={handleChange}
                    isInvalid={!!reviewErrors.review}/>
                    <Form.Control.Feedback type="invalid">
                        {reviewErrors.review}
                    </Form.Control.Feedback>
                </Form.Group>

                <Button variant='primary' type='submit'>
                    Submit
                </Button>
            </Form>
        )
    }
}

export default ReviewsForm