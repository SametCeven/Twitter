import { createRoot } from 'react-dom/client'
import { BrowserRouter } from "react-router-dom"
import './main.css'
import App from './App.jsx'
import GlobalContextProvider from './context/GlobalContext.jsx'

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <GlobalContextProvider>
      <App />
    </GlobalContextProvider>
  </BrowserRouter>
  
)
