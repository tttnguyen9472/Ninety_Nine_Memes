import React, { useEffect, useState } from 'react';
import './FormStyled.css';
import Fetch from '../fetch/Fetch';
import { useHistory } from 'react-router-dom';
//import InputField from "./InputField";
import {useDispatch, useSelector} from "react-redux";

const FormStyled = () => {

  let history = useHistory();
  const dispatch = useDispatch();
  const errormessage = useSelector(state => state.userReducer.errormessageLogin);
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [submitType, setSubmitType] = useState(null);

  function handleSubmit(event) {
    event.preventDefault();
    setUserName(event.target.username.value);
    setPassword(event.target.password.value);
    if(event.target.rg[0].value === 'on'){
      setSubmitType('login');
    }
    else{
      setSubmitType('register');
    }
    if (password === '' || userName === '') {
      console.log(password, userName)
    }
    console.log(password, userName);
  }

  useEffect(() => {
      let body = { username: userName, password: password };
      if(submitType === 'login'){
        Fetch('POST', '/login', body)
        .then(response => {
          localStorage.setItem('token', response.token);
          history.push('/login');
          return dispatch({type: 'CLEAR_FIELDS'});
        })
        .catch(err => {
          return dispatch({type: 'LOGIN_BACKEND_ERROR', errormessage: err.toString()});
        });
      }
      else if (submitType === 'register'){
        Fetch('POST', '/register', body)
        .then(response => {
          history.push('/login');
          return dispatch({type: 'CLEAR_FIELDS'});
        })
        .catch(err => {
          return dispatch({type: 'REGISTER_ERROR', errormessage: err.toString()});
        });
      }
      
  }, [submitType]);

  return (
    <div className="login-form-container">
      <div className="flex-wrap">
    <fieldset>
        <form autoComplete="off" onSubmit={handleSubmit} className="LoginRegisterForm">
            <input type="radio" name="rg" id="sign-in" checked/>
            <input type="radio" name="rg" id="sign-up" />      

            <label htmlFor="sign-in">Login</label>
            <label htmlFor="sign-up">Register</label> 

            <input className="sign-up sign-in reset" name='username' id='username' type="text" placeholder="Username" />
            <input className="sign-up sign-in" name='password' id='password' type="password" placeholder="Password" />
            
            <button type="submit"></button>       

            <p>{errormessage && <span className="login-errormessage">{errormessage}</span>}</p>
        </form>
    </fieldset>
</div>
    </div>
  );
};

export default FormStyled;
