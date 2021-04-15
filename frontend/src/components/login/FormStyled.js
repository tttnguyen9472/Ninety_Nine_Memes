import React, { useEffect, useState } from 'react';
import './FormStyled.css';
import Fetch from '../fetch/Fetch';
import { useHistory } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

const FormStyled = () => {
  let history = useHistory();
  const dispatch = useDispatch();
  const errormessage = useSelector(state => state.userReducer.errormessage);
  const userName = useSelector(state => state.userReducer.username);
  const password = useSelector(state => state.userReducer.password);
  const [submitType, setSubmitType] = useState(null);
  const [submitted, setSubmitted] = useState(false);

  function handleUsernameChange(event) {
    dispatch({ type: 'CHANGE_USERNAME', username: event.target.value.trim() });
  }

  function handlePasswordChange(event) {
    dispatch({ type: 'CHANGE_PASSWORD', password: event.target.value.trim() });
  }

  function handleSubmit(event) {
    event.preventDefault();

    if (userName === '' && password === '') {
      return dispatch({ type: 'MISSING_PASSWORD_AND_USERNAME' });
    } else if (userName === '') {
      return dispatch({ type: 'MISSING_USERNAME' });
    } else if (password === '') {
      return dispatch({ type: 'MISSING_PASSWORD' });
    } else if (password.length < 8) {
      return dispatch({ type: 'PASSWORD_UNDER_8_CHARACTERS' });
    }

    setSubmitted(true);
  }

  useEffect(() => {
    let body = { username: userName, password: password };
    if (submitType === 'login') {
      Fetch('POST', '/login', body)
        .then(response => {
          localStorage.setItem('token', response.token);
          dispatch({ type: 'CLEAR_FIELDS' });
          return history.push('/feed');
        })
        .catch(err => {
          dispatch({ type: 'CLEAR_FIELDS' });
          return dispatch({
            type: 'BACKEND_ERROR',
            errormessage: err.toString(),
          });
        });
    } else if (submitType === 'register') {
      Fetch('POST', '/register', body)
        .then(response => {
          dispatch({ type: 'CLEAR_FIELDS' });
          return history.push('/login');
        })
        .catch(err => {
          dispatch({ type: 'CLEAR_FIELDS' });
          return dispatch({
            type: 'BACKEND_ERROR',
            errormessage: err.toString(),
          });
        });
    }

    setSubmitType(false);
  }, [submitted === true]);

  return (
    <div className="login-form-container">
      <div className="flex-wrap">
        <fieldset>
          <form
            autoComplete="off"
            onSubmit={handleSubmit}
            className="LoginRegisterForm"
          >
            <input
              type="radio"
              name="rg"
              id="sign-in"
              onChange={() => {
                setSubmitType('login');
                dispatch({ type: 'CLEAR_FIELDS' });
              }}
              checked={submitType === 'login'}
            />
            <input
              type="radio"
              name="rg"
              id="sign-up"
              onChange={() => {
                setSubmitType('register');
                dispatch({ type: 'CLEAR_FIELDS' });
              }}
              checked={submitType === 'register'}
            />

            <label htmlFor="sign-in">Login</label>
            <label htmlFor="sign-up">Register</label>

            <input
              className="sign-up sign-in reset"
              name="username"
              id="username"
              type="text"
              onChange={handleUsernameChange}
              placeholder="Username"
              value={userName}
            />
            <input
              className="sign-up sign-in"
              name="password"
              id="password"
              type="password"
              onChange={handlePasswordChange}
              placeholder="Password"
              value={password}
            />

            {submitType && <button type="submit"></button>}

            <p>
              {errormessage && (
                <span className="login-errormessage">{errormessage}</span>
              )}
            </p>
          </form>
        </fieldset>
      </div>
    </div>
  );
};

export default FormStyled;
