import React from 'react';
import { useHistory } from 'react-router-dom';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from 'react-router-dom';
import './App.css';
import Login from './components/login/FormStyled';
import Feed from './components/feed/Feed';
import MemeCreator from './components/memeCreator/MemeCreator';
import Header from './components/header/Header';

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
          <Route path="/">
            {localStorage.getItem('token') ? (
              <Redirect to="/feed" />
            ) : (
              <Login />
            )}
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
