import React from 'react';

const InputField = (props) => {
  const placeholder = props.placeholder;
  const name = props.name;
  const type = props.type;
  const onChange = props.onChange;

  return (
    <input
      onChange={onChange}
      type={type}
      placeholder={placeholder}
      name={name}
    ></input>
  );
};

export default InputField;
