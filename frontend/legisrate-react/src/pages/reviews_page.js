import React, {Component} from "react"
import {MDBDataTable} from "mdbreact";

const SERVICE_URL = "http://localhost:3000/api"

class ReviewsPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            legislationId: props.location.aboutProps.legislationID,
            loading: false,
            reviewData: [{
            }]
        }
    }

    fetchReviews() {
        console.log("Table is now mounted.")
        this.setState({ loading: true })
        console.log("Loading review data")
        console.log(SERVICE_URL + "/review/" + this.state.legislationId)
        fetch(SERVICE_URL + "/review/" + this.state.legislationId)
            .then(response => response.json())
            .then(result => {
                this.setState({reviewData: result, loading: false})
            })
    }

    componentDidMount() {
        this.fetchReviews()
    }

    render() {
        const data = {
            columns: this.getColumns(),
            rows: this.getRows(this.state.reviewData)
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

    getColumns() {
        const reviewTableColumns = [
            { label: 'Username', field: 'username', sort: 'asc', width: 50 },
            { label: 'State', field: 'state', sort: 'asc', width: 75 },
            { label: 'Rating', field: 'rating', sort: 'asc', width: 5 },
            { label: 'Review', field: 'comments', sort: 'asc', width: 20 },
        ]
        return reviewTableColumns
    }

    getUsername(object) {
        if (object.hasOwnProperty("user")) {
            return object["user"].username
        }
    }

    getState(object) {
        if (object.hasOwnProperty("user")) {
            return object["user"].state
        }
    }

    getRows(reviewData) {
        // Handle null case before reviews data is loaded
        if ( reviewData == null || typeof( reviewData) == 'undefined') {
            return []
        }

        // Handle case with no reviews
        if ( !( reviewData.length > 0 ) ) { return [] }

        return reviewData.map((object)=> {
            return {
                username: this.getUsername(object),
                state: this.getState(object),
                rating: object.rating,
                comments: object.comments
            }
        })
    }
}

export default ReviewsPage