import React from 'react';
import './HeaderButton.css';

const HeaderButton = (props) => {

    const headerButtonText = props.headerButtonText;

    return(
        <div className="header-button-container">
            <p className="header-button-text">{headerButtonText}</p>
        </div>
    )
}

export default HeaderButton;