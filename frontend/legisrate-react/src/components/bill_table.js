import React, { Component } from 'react';
import {MDBBtn, MDBDataTable} from 'mdbreact';
import { view } from '@risingstack/react-easy-state'
import {NavLink} from 'react-router-dom'

const SERVICE_URL = "http://localhost:3000/api"

class BillTable extends Component {
    constructor() {
        super();
        this.state = {
            loading: false,
            legislationData: [{
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
            />
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
                reviewModal: <MDBBtn color="blue-grey" outline size="sm">Leave a Review</MDBBtn>,
                reviewTable: <NavLink activeClassName="active" to={{
                    pathname:'/reviews',
                    aboutProps: {legislationID: object.legislationID}
                }}>
                                <MDBBtn color="blue-grey" outline size="sm">View Reviews</MDBBtn>
                             </NavLink>

            }
        })
    }
}

export default view(BillTable)