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
     
    </>
  )
}

export default App
