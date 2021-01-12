import React from "react"
import { Switch, Route } from "react-router-dom"
import DashboardMenu from "../components/dashboard_menu"
 
function DashboardPage() {
    return (
        <div id="dashboard_page" className="App-page">
            <DashboardMenu/>
                <Switch>
                    <Route exact path='/dashboard' component={() => 'Dashboard Content'}/>
                </Switch>
        </div>
    )
}
 
export default DashboardPage