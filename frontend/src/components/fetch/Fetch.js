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

  const call = await fetch(
    `${process.env.REACT_APP_PORT}${endpoint}`,
    settings
  );

  const result = await call.json();

  if (!call.ok) {
    throw new Error(result);
  }
  return result;
}

export default Fetch;
