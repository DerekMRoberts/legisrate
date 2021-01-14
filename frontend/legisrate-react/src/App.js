import React, { Component } from "react"
import {Switch, Route} from "react-router-dom"
import './App.css'
import NavBar from "./components/nav_bar"
import HomePage from './pages/home_page'
import AboutPage from './pages/about_page'
import ReviewsForm from './components/reviews_form'
import ReviewPage from './pages/reviews_page';


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
                    </Switch>
                </main>
            </div>
        )
    }
}

export default App