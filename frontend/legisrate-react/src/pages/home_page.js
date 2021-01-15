import React from "react"
import BillTable from "../components/bill_table"

function HomePage() {
    return (
        <div id="home_page" className="App-page">
            <br/>
            <h1>LegisRate</h1>
            <br/>
            <BillTable/>
        </div>
    )
}

export default HomePage
