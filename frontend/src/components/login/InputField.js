import React from 'react';
import './InputField.css';

const InputField = (props) => {

    const placeholder = props.placeholder;
    const name = props.name;
    const type= props.type;
    const onChange = props.onChange;
    
    return(
        <input onChange={onChange} className='inputfield' type={type} placeholder={placeholder} name={name}></input>
    )
}

export default InputField;