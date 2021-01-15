import React from 'react'

function AboutPage() {
    const developerName = 'Rosalinda P.'
    const developerName2 = 'Derek R.'
    const developerName3 = 'John Michael R.'
    const developerName4 = 'Abdulrasaq S'
    const developerName5 = 'Russell T.'
    return (
        <div id="about_page" className="App-page">
            <h1>LegisRate Project</h1>
            <div className="container-sm">
                <p>
                    LegisRate is a web application built to elicit engagement
                    from the population in the legislative process in a way
                    that is direct, straight-forward, and intuitive. Users will
                    be able to search for legislation and review it and give it
                    an overall rating based on a 5 point rating scale.
                </p>
            </div>
            <h2>Team</h2>
            <p>
                Contributing Developers:
            </p>
            <p>
                {developerName2} - Team Lead, Front End

            </p>
            <p>
                {developerName} - Front End
            </p>
            <p>
                {developerName3} - Database
            </p>
            <p>
                {developerName4} - Database
            </p>
            <p>
                {developerName5} - Back End
            </p>
        </div>
    )
}

export default AboutPage