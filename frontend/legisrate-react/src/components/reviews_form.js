import React, {Component} from 'react';
import { Form, Button } from 'react-bootstrap'
import AddReviewPage from "../pages/reviews_page"

const SERVICE_URL = "http://localhost:3000/api"


class ReviewForm extends Component {
    constructor(props) {
        super(props);
        console.log(props)
        this.state = {
            legislationID: props.legislationID,
            username: '',
            state: '',
            rating: '',
            comments: ''
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleInput = this.handleInput.bind(this)
    }

    handleInput = (event) => {
        const target = event.target
        this.setState({
            [target.name]: target.value
        })
    }

    handleSubmit = (event) => {
        console.log(this.state)
        event.preventDefault()
        const postReview = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                username: this.state.username,
                state: this.state.state,
                rating:this.state.rating,
                comments: this.state.comments
            })
        }

        fetch(SERVICE_URL + "/review/" + this.state.legislationID, postReview)
    };


    render(){
        return (
            <Form>
                <Form.Group controlId="reviewUsername">
                    <Form.Label>Username:</Form.Label>
                    <Form.Control type="text" placeholder="Username" name="username"/>
                </Form.Group>
                <Form.Group controlId="reviewState">
                    <Form.Label>State</Form.Label>
                    <Form.Control as="select">
                        <option>AL</option>
                        <option>AK</option>
                        <option>AZ</option>
                        <option>AR</option>
                        <option>CA</option>
                        <option>CO</option>
                        <option>CT</option>
                        <option>DE</option>
                        <option>FL</option>
                        <option>GA</option>
                        <option>HI</option>
                        <option>ID</option>
                        <option>IL</option>
                        <option>IN</option>
                        <option>IA</option>
                        <option>KS</option>
                        <option>KY</option>
                        <option>LA</option>
                        <option>ME</option>
                        <option>MD</option>
                        <option>MA</option>
                        <option>MI</option>
                        <option>MN</option>
                        <option>MS</option>
                        <option>MO</option>
                        <option>MT</option>
                        <option>NE</option>
                        <option>NV</option>
                        <option>NH</option>
                        <option>NJ</option>
                        <option>NM</option>
                        <option>NY</option>
                        <option>NC</option>
                        <option>ND</option>
                        <option>OH</option>
                        <option>OK</option>
                        <option>OR</option>
                        <option>PA</option>
                        <option>RI</option>
                        <option>SC</option>
                        <option>SD</option>
                        <option>TN</option>
                        <option>TX</option>
                        <option>UT</option>
                        <option>VT</option>
                        <option>VA</option>
                        <option>WA</option>
                        <option>WV</option>
                        <option>WI</option>
                        <option>WY</option>
                    </Form.Control>
                </Form.Group>
                <Form.Group controlId="reviewRating">
                    <Form.Label>Rating</Form.Label>
                    <Form.Control as="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </Form.Control>
                </Form.Group>
                <Form.Group controlId="reviewComments">
                    <Form.Label>Add Review:</Form.Label>
                    <Form.Control as="textarea" rows={3} placeholder="Add Review" name="comments"/>
                </Form.Group>
                <Button type="submit">
                    Submit
                </Button>
            </Form>
        )
    }
}

export default ReviewForm