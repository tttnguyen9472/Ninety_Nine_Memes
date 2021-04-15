import React, { useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './App.css';
import Login from './components/login/FormStyled';
import Feed from './components/feed/Feed';
import MemeCreator from './components/memeCreator/MemeCreator';
import Header from './components/header/Header'

function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/feed">
            <Header />
            <Feed />
          </Route>
          <Route path="/creator">
            <Header />
            <MemeCreator />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
