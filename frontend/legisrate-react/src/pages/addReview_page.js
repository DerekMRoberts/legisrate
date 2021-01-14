import React from "react"
import ReviewsTable from "../components/reviews_table";

class AddReviewPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            legislationID: props.location.aboutProps.reviewID
        }
    }
    render() {
        return (
            <div id="home_page" className="App-page">
                <AddReviewsTable reviewID={this.state.reviewID}/>
            </div>
        )
    }
}

export default AddReviewPage