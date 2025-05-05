import { Route, Switch } from "react-router-dom/cjs/react-router-dom.min";
import LoginPage from "./components/Login";
import MainPage from "./pages/MainPage";

export default function App() {

  return (
    <div>
      <Switch>
        <Route path="/" exact>
          <MainPage></MainPage>
        </Route>
        <Route path="/login">
          <LoginPage></LoginPage>
        </Route>
        
      </Switch>

      
    </div>
    
  )
}
