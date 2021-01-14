import React from "react"
import ReviewForm from "../components/reviews_form"


class AddReviewPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            legislationID: props.location.aboutProps.legislationID
        }
    }


    render() {
        return (
            <div id="home_page" className="App-page">
                <ReviewForm legislationID={this.state.legislationID}/>
            </div>
        )
    }
}

export default AddReviewPage