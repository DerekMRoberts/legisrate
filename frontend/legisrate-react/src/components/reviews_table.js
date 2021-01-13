import React, { Component } from 'react';
import { MDBDataTable } from 'mdbreact';
import { view } from '@risingstack/react-easy-state'

const SERVICE_URL = "http://localhost:3000/api"

class ReviewsTable extends Component {
    render() {
        const data = {
            columns: this.getColumns(),
            rows: this.getRows(SERVICE_URL + '/review/')
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

    getColumns(){
        const reviewsTableColumns = [
            { label: 'Review', field: 'UserComment', sort: 'asc', width: 60 },
            { label: 'Rating', field: 'Rating', sort: 'asc', width: 3 }
        ]
        return reviewsTableColumns
    }

    getRows(reviewsData) {
        // Handle null case before reviews data is loaded
        if ( reviewsData == null || typeof(reviewsData) == 'undefined') {
            return []
        }

        // Handle case with no reviews
        if ( !( reviewsData.length > 0 ) ) { return [] }

        return reviewsData.map((object)=> {
            const [ reviewYear, reviewMonth, _ ] = this.getTimeInfo(object.reviewTime)
            console.log(_)
            return {
                asin: object.asin,
                reviewMonth: reviewMonth,
                reviewYear: reviewYear,
                reviewerID: object.reviewerID,
                reviewerName: object.reviewerName,
                score: object.overall,
                summary: object.summary
            }
        })
    }


    handleEditModalClose = (event) => {
        console.log('Closing Edit Modal')
        this.setState({showEditModal: false})
      }
    
    handleEditModalOpen = (event) => {
        console.log('Opening Edit Modal')
        if (event) event.preventDefault();
        const contactId = event.target.value;
        console.log(`Editing contact id ${contactId}`)
    
        //submit a GET request to the /contact/{contactId} endpoint
        //the response should come back with the associated contact's json
        fetch(SERVICE_URL + '/review/' + legislationId)
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
          this.setState({editReviewData : data, showEditModal: true})
        })
        .catch((error) => {
          console.error('Error:', error)
        })
      }
    
    handleEditFormChange = (event) => {
        const inputName = event.target.name;
        const inputValue = event.target.value;
        const contactInfo = this.state.editReviewData;
    
        console.log(`Something change in ${inputName} : ${inputValue}`)
    
        if(contactInfo.hasOwnProperty(inputName)) {
          contactInfo[inputName] = inputValue;
          this.setState({editContactData : contactInfo})
        }
      }
    
    
    handleEditFormSubmit = (event) => {
        if(event) event.preventDefault();
        let contactId = event.target.value;
        console.log(`Submitting edit for contact id ${ReviewId}`)
        console.log(this.state.editContactData)
    
        let validationErrors = this.validateContact(this.state.editReviewData)
        if(!validationErrors.isValid) {
          console.log('Edited contact is invalid. Reporting errors')
          this.setState({editFormErrors : validationErrors})
          return
        }
    
        fetch(SERVICE_URL + '/contact/' + contactId, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.state.editContactData),
        })
        .then(response => response.json())
        .then(data => {
          console.log('Success:', data);
          this.setState({showEditModal : false, editFormErrors : validationErrors})
          this.loadContactData();
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    }
}

export default view(ReviewsTable)