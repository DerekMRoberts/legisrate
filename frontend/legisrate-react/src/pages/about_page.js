import React from 'react'

function AboutPage() {
    const developerName = 'Rosalinda P.'
    const developerName2 = 'Derek R.'
    const developerName3 = 'John Michael R.'
    const developerName4 = 'Abdulrasaq S'
    const developerName5 = 'Russell T.'
    return (
        <div id="about_page" className="App-page">
            <h1>Our Project</h1>
            <p>
                We're creating a simple React application.
            </p>
            <h2>Team</h2>
            <p>
                Contributing Developers: {developerName}
            </p>
            <p>
                {developerName2}
            </p>
            <p>
                {developerName3}
            </p>
            <p>
                {developerName4}
            </p>
            <p>
                {developerName5}
            </p>
        </div>
    )
}

export default AboutPage