import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import HelloButton from './components/HelloButton';
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
       <div className="App">
      {/* Brug HelloButton komponenten her */}
      <HelloButton />
    </div>
      
    </>
  )
}

export default App
