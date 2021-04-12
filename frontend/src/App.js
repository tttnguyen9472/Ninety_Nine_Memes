import React, { useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import RegisterPage from './components/register/RegisterPage';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/register'>
          <RegisterPage></RegisterPage>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
