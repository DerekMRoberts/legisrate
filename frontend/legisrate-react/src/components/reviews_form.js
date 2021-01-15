import React, {Component} from 'react';
import {Form, Row, Col, Card} from 'react-bootstrap'
import {MDBBtn} from "mdbreact";
import {NavLink} from "react-router-dom";

const SERVICE_URL = "http://localhost:8080/api"


class ReviewForm extends Component {
    constructor(props) {
        super(props);
        console.log(props)
        this.state = {
            legislationID: props.legislationID,
            username: '',
            state: '',
            rating: '1',
            comments: '',
            legislationData: {}
        }
        this.handleClick = this.handleClick.bind(this)
        this.handleText = this.handleText.bind(this)
    }

    handleText = (event) => {
        const target = event.target
        this.setState({
            [target.name]: target.value
        })
    }

    handleState = (event) => {
        this.setState({
            state: event.target.value
        })
    }

    handleRating = (event) => {
        console.log(event.target)
        this.setState({
            rating: event.target.value
        })
    }

    handleClick() {
        console.log(this.state.reviewData)
        const postReview = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                legislationID: this.state.legislationID,
                username: this.state.username,
                state: this.state.state,
                rating: this.state.rating,
                comments: this.state.comments,
            })
        }

        fetch(SERVICE_URL + "/review/", postReview)
            .then(response => response.json())
            .then(data => console.log(data))
    };

    componentDidMount() {
        fetch(SERVICE_URL + "/legislation/" + this.state.legislationID)
            .then(response => response.json())
            .then(result => this.setState({legislationData: result}))
    }


    render(){
        return (
            <div className="container-fluid">
                <br/>
                <Card border="light">
                    <Card.Header>Legislation</Card.Header>
                    <Card.Body>
                        <Card.Title>{this.state.legislationData.title}</Card.Title>
                        <Card.Text>{this.state.legislationData.summary}</Card.Text>
                    </Card.Body>
                </Card>
                <br/>
                <Form>
                    <Form.Group controlId="reviewUsername" as={Row}>
                        <Form.Label column sm={2}>Username:</Form.Label>
                        <Col>
                            <Form.Control type="text" placeholder="Username" name="username" value={this.state.username} onChange={this.handleText} />
                        </Col>
                    </Form.Group>
                    <Form.Group controlId="reviewState" as={Row}>
                        <Form.Label column sm={2}>State</Form.Label>
                        <Col>
                            <Form.Control as="select" value={this.state.state} onChange={this.handleState}>
                                <option value="AL">AL</option>
                                <option value="AK">AK</option>
                                <option value="AZ">AZ</option>
                                <option value="AR">AR</option>
                                <option value="CA">CA</option>
                                <option value="CO">CO</option>
                                <option value="CT">CT</option>
                                <option value="DE">DE</option>
                                <option value="FL">FL</option>
                                <option value="GA">GA</option>
                                <option value="HI">HI</option>
                                <option value="ID">ID</option>
                                <option value="IL">IL</option>
                                <option value="IN">IN</option>
                                <option value="IA">IA</option>
                                <option value="KS">KS</option>
                                <option value="KY">KY</option>
                                <option value="LA">LA</option>
                                <option value="ME">ME</option>
                                <option value="MD">MD</option>
                                <option value="MA">MA</option>
                                <option value="MI">MI</option>
                                <option value="MN">MN</option>
                                <option value="MS">MS</option>
                                <option value="MO">MO</option>
                                <option value="MT">MT</option>
                                <option value="NE">NE</option>
                                <option value="NV">NV</option>
                                <option value="NH">NH</option>
                                <option value="NJ">NJ</option>
                                <option value="NM">NM</option>
                                <option value="NY">NY</option>
                                <option value="NC">NC</option>
                                <option value="ND">ND</option>
                                <option value="OH">OH</option>
                                <option value="OK">OK</option>
                                <option value="OR">OR</option>
                                <option value="PA">PA</option>
                                <option value="RI">RI</option>
                                <option value="SC">SC</option>
                                <option value="SD">SD</option>
                                <option value="TN">TN</option>
                                <option value="TX">TX</option>
                                <option value="UT">UT</option>
                                <option value="VT">VT</option>
                                <option value="VA">VA</option>
                                <option value="WA">WA</option>
                                <option value="WV">WV</option>
                                <option value="WI">WI</option>
                                <option value="WY">WY</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group controlId="reviewRating" as={Row}>
                        <Form.Label column sm={2}>Rating</Form.Label>
                        <Col>
                            <Form.Control as="select" value={this.state.rating} onChange={this.handleRating}>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group controlId="reviewComments" as={Row}>
                        <Form.Label column sm={2}>Add Review:</Form.Label>
                        <Col>
                            <Form.Control as="textarea" rows={3} placeholder="Add Review" name="comments" onChange={this.handleText}/>
                        </Col>
                    </Form.Group>
                    <NavLink activeClassName="active" to={{pathname:'/'}}>
                        <MDBBtn rounded color="black" onClick={this.handleClick}>Submit</MDBBtn>
                    </NavLink>
                </Form>
            </div>
        )
    }
}

export default ReviewForm