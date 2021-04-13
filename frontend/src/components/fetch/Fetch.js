async function Fetch(method, endpoint, body) {
  const settings = {
    method: method,
    headers: { 'Content-Type': 'application/json' },
  };

  if (localStorage.getItem('token')) {
    settings.headers.token = JSON.stringify(
      JSON.parse(atob(localStorage.getItem('token').split('.')[1]))
    );
  }

  if (body) {
    settings.body = JSON.stringify(body);
  }

  const call = null;

  try {
    call = await fetch(`${process.env.REACT_APP_PORT}${endpoint}`, settings);
  } catch (err) {
    throw new Error('The server is not responding');
  }

  const result = await call.json();

  console.log(call);

  if (call.status === 404) {
    throw new Error('The server is not responding');
  } else if (!call.ok) {
    throw new Error(result);
  }
  return result;
}

export default Fetch;
