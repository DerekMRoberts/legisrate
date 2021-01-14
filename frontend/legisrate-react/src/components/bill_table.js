import React, { Component } from 'react';
import {MDBBtn, MDBDataTableV5 } from 'mdbreact';
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
            <div className="container-fluid">
                <
                    MDBDataTableV5
                    hover
                    entriesOptions={[5, 10, 15]}
                    entries={5}
                    maxHeight="70vh"
                    autoWidth
                    data={data}
                    pagingTop
                    searchTop
                    searchBottom={false}
                />
            </div>
        )
    }

    activeToString(active) {
        if(active) {
            return "Yes"
        } else {
            return "No"
        }
    }

    getFixedAvg(object) {
        if (object.hasOwnProperty("avgRating")) {
            return object["avgRating"].toFixed(2)
        }
    }

    parseText(text) {
        let parser = new DOMParser();
        let dom = parser.parseFromString(
            '<!doctype html><body>' + text,
            'text/html');
        return dom.body.textContent;
    }

    getColumns() {
        const legislationTableColumns = [
            { label: 'Title', field: 'title', sort: 'disabled', width: 30},
            { label: 'Summary', field: 'summary', sort: 'disabled', width: 40 },
            { label: 'Avg. Rating', field: 'avgRating', sort: 'asc', width: 10 },
            { label: 'Enacted', field: 'active', sort: 'disabled', width: 10 },
            { label: '', field: 'reviewModal', sort: 'disabled', width: 10 },
            { label: '', field: 'reviewTable', sort: 'disabled', width: 10 },
        ]
        return legislationTableColumns
    }

    getRows(legislationData) {
        // Handle null case before reviews data is loaded
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
                title: this.parseText(object.title),
                summary: this.parseText(object.summary),
                avgRating: this.getFixedAvg(object),
                active: this.activeToString(object.active),
                reviewModal: <MDBBtn color="blue-grey" outline size="sm">Review</MDBBtn>,
                reviewTable: <NavLink activeClassName="active" to={{
                                        pathname:'/reviews',
                                        aboutProps: {legislationID: object.legislationID}}}>
                                <MDBBtn color="blue-grey" outline size="sm">View Reviews</MDBBtn>
                             </NavLink>
            }
        })
    }
}

export default view(BillTable)