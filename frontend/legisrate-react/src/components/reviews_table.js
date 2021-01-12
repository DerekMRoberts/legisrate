import React, { Component } from 'react';
import { MDBDataTable } from 'mdbreact';
import { view } from '@risingstack/react-easy-state'
 
import reviewStore from '../stores/review_store' 

class ReviewsTable extends Component {
    render() {
        const data = {
            columns: this.getColumns(),
            rows: this.getRows(reviewStore.reviews)
        }
        return (
            <
                MDBDataTable
                striped
                bordered
                hover
                scrollX
                responsive
                maxHeight="50vh"
                data={data}
            />          
        )
    }
    
    getColumns() {
        const reviewsTableColumns = [
            { label: 'Legislation Title', field: 'LegislationTitle', sort: 'asc', width: 75 },
            { label: 'Bill Summary', field: 'Summary', sort: 'asc', width: 3 },
            { label: 'Enacted', field: 'Enacted', sort: 'asc', width: 5 },
            { label: 'Avg Rating', field: 'reviewerID', sort: 'asc', width: 50 }
        ]
        return reviewsTableColumns
    }

    //getRows()

    //getTimeInfo()
}

export default view(ReviewsTable)