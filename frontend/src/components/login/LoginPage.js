import React from 'react';
import LoginBlock from './LoginBlock'
import Header from '../register/Header'
import './LoginPage.css'

const LoginPage = () => {

  return (
    <div className='login-page-container'>
      <Header/>
      <LoginBlock />
    </div>
  );
}

export default LoginPage;
