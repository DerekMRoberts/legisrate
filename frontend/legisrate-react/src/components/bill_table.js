import React, { Component } from 'react';
import {MDBBtn, MDBDataTable} from 'mdbreact';
import { view } from '@risingstack/react-easy-state'
import {NavLink} from 'react-router-dom'
import { Container, Row, Col } from "react-bootstrap";
import ReviewModal from './reviews_modal'

const SERVICE_URL = "http://localhost:3000/api"

class BillTable extends Component {
    constructor() {
        super();
        this.state = {
            showAddModal: false,
            loading: false,
            legislationData: [{
            }],
            reviewData: [{
                
            }]
        }

    }

    fetchLegislation() {
        console.log("Table is now mounted.")
        this.setState({ loading: true })
        console.log("Loading legislation data")
        fetch(SERVICE_URL + "/legislation")
            .then(response => response.json())
            .then(result => {
                this.setState({legislationData: result, loading: false})
            })
    }

    componentDidMount() {
        this.fetchLegislation()
        this.loadReviewData
    }

    render() {
        const data = {
            columns: this.getColumns(),
            rows: this.getRows(this.state.legislationData)
        }
        return (

            <
                MDBDataTable
                striped
                bordered
                hover
                scrollX
                responsive
                maxHeight="70vh"
                data={data}
            />,

            <Container fluid>
                <ReviewModal 
                show={this.state.showEditModal}
                handleSubmit={this.handleAddFormSubmit}
                handleChange={this.handleAddFormChange}
                handleClose={this.handleAddModalClose}
                reviewData={this.state.addReviewData}
                reviewErrors={this.state.addFormErrors}/>
            </Container>
        
        )
    }

    activeToString(active) {
        if(active) {
            return "Yes"
        } else {
            return "No"
        }
    }

    getColumns() {
        const legislationTableColumns = [
            { label: 'Title', field: 'title', sort: 'asc', width: 70 },
            { label: 'Summary', field: 'summary', sort: 'asc', width: 250 },
            { label: 'Enacted', field: 'active', sort: 'disabled', width: 25 },
            { label: '', field: 'reviewModal', sort: 'disabled', width: 10 },
            { label: '', field: 'reviewTable', sort: 'disabled', width: 10 },
        ]
        return legislationTableColumns
    }

    getRows(legislationData) {
        // Handle null case before reviews data is loaded
        console.log(this.state.legislationData)
        if ( legislationData == null || typeof( legislationData) == 'undefined') {
            return [{
                title: "Didn't Work",
                summary: "Nothing in this.legislationData",
                active: true,
                reviewModal: <MDBBtn color="blue-grey" outline size="sm">Leave a Review</MDBBtn>,
                reviewTable: <MDBBtn color="blue-grey" outline size="sm">View Reviews</MDBBtn>
            }]
        }

        // Handle case with no reviews
        if ( !( legislationData.length > 0 ) ) { return [] }

        return legislationData.map((object)=> {
            return {
                title: object.title,
                summary: object.summary,
                active: this.activeToString(object.active),
                reviewModal: <NavLink activeClassName="modal" to={{
                    pathname: '/components/reviews_modal',
                    aboutProps: {reviewId: object.reviewId}
                }}>
                    <MDBBtn color="blue-grey" outline size="sm">Leave a Review</MDBBtn>
                    </NavLink>,

                reviewTable: <NavLink activeClassName="active" to={{
                    pathname:'/reviews',
                    aboutProps: {legislationID: object.legislationID}
                }}>
                                <MDBBtn color="blue-grey" outline size="sm">View Reviews</MDBBtn>
                             </NavLink>

            }
        })
    }

    showModal = () => {
        this.setState({ show: true })
    }

    handleAddModalClose = (event) => {
        console.log('Closing Add Modal')
        this.setState({showAddModal: false})
    }

    handleAddModalOpen = (event) => {
        console.log('Opening Add Modal')
        if (event) event.preventDefault();
        const reviewId = event.target.value;
        console.log(`Adding review id ${reviewId}`)
    
        //submit a GET request to the /legislation/{legislationId} endpoint
        //the response should come back with the associated legislation's json
        fetch(SERVICE_URL + '/review/' + reviewId)
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
          this.setState({AddReviewData : data, showAddModal: true})
        })
        .catch((error) => {
          console.error('Error:', error)
        })
    }

    loadReviewData() {
        this.setState({loading: true})
        console.log("Loading review data")
        fetch(SERVICE_URL + '/review')
        .then(data => data.json())
        .then(data => this.setState(
          {reviewData: data, loading: false}
        ))
      }
}

export default view(BillTable)