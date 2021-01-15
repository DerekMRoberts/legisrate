import React, { Component } from "react"
import {Switch, Route} from "react-router-dom"
import './App.css'
import NavBar from "./components/nav_bar"
import HomePage from './pages/home_page'
import AboutPage from './pages/about_page'
import AddReviewPage from './pages/add_review_page'
import ReviewPage from './pages/reviews_page'


class App extends Component {
    render() {
        return (
            <div className="App">
                <NavBar />
                <main>
                    <Switch>
                        <Route exact path='/' component={HomePage} />
                        <Route path='/home' component={HomePage} />
                        <Route path='/reviews' component={ReviewPage}/>
                        <Route path='/about' component={AboutPage} />
                        <Route path='/review' component={AddReviewPage} />
                    </Switch>
                </main>
            </div>
        )
    }
}

export default App