async function Fetch(method, endpoint, body) {
  const settings = {
    method: method,
    headers: {
      'Content-Type': 'application/json',
    },
  };

  if (localStorage.getItem('token')) {
    settings.headers.token = JSON.stringify(localStorage.getItem('token'));
    console.log(settings.headers.token);
  }

  if (body) {
    settings.body = JSON.stringify(body);
  }

  let call = null;

  try {
    call = await fetch(`${process.env.REACT_APP_PORT}${endpoint}`, settings);
  } catch (err) {
    throw 'Fetch Error';
  }

  if (call.status === 404) {
    throw 'The server is not responding';
  }

  const result = await call.json();
  console.log(call);

  if (!call.ok) {
    throw result.message;
  }

  return result;
}

export default Fetch;
