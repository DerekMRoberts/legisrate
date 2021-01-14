import React from "react"
import {MDBDataTableV5} from "mdbreact";

const SERVICE_URL = "http://localhost:3000/api"

class ReviewsTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            legislationID: props.legislationID,
            loading: false,
            reviewData: [{
            }]
        }
    }

    fetchReviews() {
        this.setState({ loading: true })
        console.log("Loading review data")
        fetch(SERVICE_URL + "/review/" + this.state.legislationID)
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
            <div className="container-fluid">
                <
                    MDBDataTableV5
                    hover
                    entriesOptions={[5, 10, 15]}
                    entries={5}
                    maxHeight="70vh"
                    data={data}
                    pagingTop
                    searchTop
                    searchBottom={false}
                />
            </div>
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

    parseText(text) {
        let parser = new DOMParser();
        let dom = parser.parseFromString(
            '<!doctype html><body>' + text,
            'text/html');
        return dom.body.textContent;
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
                comments: this.parseText(object.comments)
            }
        })
    }
}

export default ReviewsTable