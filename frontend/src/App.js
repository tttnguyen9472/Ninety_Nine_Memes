import React, { useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './App.css';
import Login from './components/login/FormStyled';
import Feed from './components/feed/Feed';
import MemeCreator from './components/memeCreator/MemeCreator';

function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/feed">
            <Feed />
          </Route>
          <Route path="/creator">
            <MemeCreator />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
