import React from "react"
import ReviewsTable from "../components/reviews_table";

class ReviewPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            legislationId: props.location.aboutProps.legislationID
        }
    }
    render() {
        console.log(this.state.legislationId)
        return (
            <div id="home_page" className="App-page">
                <ReviewsTable legislationId={this.state.legislationId}/>
            </div>
        )
    }
}

export default ReviewPage