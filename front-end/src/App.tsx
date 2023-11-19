import { useState } from 'react'
import LoginButton from './components/login_button';
import './App.css'
import HelloButton from './components/HelloButton';
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
       <div className="App">
      {/* Brug HelloButton komponenten her */}
  
    <LoginButton/>
    </div>
      
    </>
  )
}

export default App
