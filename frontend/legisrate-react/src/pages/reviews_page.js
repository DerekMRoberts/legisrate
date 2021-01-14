import React from "react"
import ReviewsTable from "../components/reviews_table";

class ReviewPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            legislationID: props.location.aboutProps.legislationID
        }
    }
    render() {
        return (
            <div id="home_page" className="App-page">
                <ReviewsTable legislationID={this.state.legislationID}/>
            </div>
        )
    }
}

export default ReviewPage