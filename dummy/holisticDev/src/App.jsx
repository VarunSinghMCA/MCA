import { useEffect, useState } from 'react'
import './App.css'

function App() {
  // const [count, setCount] = useState(0);

  const name = 'Holistic Dev'

  const [value, setValue] = useState('');

  useEffect(() => {
    console.log('App mounted');
  }, [value]);

  return (
    <>
      <form onSubmit={(e) => {
        e.preventDefault();
        console.log('Form submitted with value:', value);
      }}>
        <h1>Hello {name}!</h1>
        <input
          type="text"
          value={value}
          onChange={(e) => setValue(e.target.value)}
          placeholder="Type something..."
        />
        <p>You typed: {value}</p>

        <button type="submit">Submit</button>
        

      </form>

    </>
  )
}

export default App
