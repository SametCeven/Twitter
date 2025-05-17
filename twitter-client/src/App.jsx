import { Route, Switch } from "react-router-dom/cjs/react-router-dom.min";
import MainPage from "./pages/MainPage";
import SideNavBar from "./layout/SideNavBar";
import Login from "./components/Login";
import AllUsers from "./components/AllUsers";
import AllTweets from "./components/AllTweets";


export default function App() {

  return (
    <div className="flex h-screen overflow-hidden">

      <SideNavBar></SideNavBar>

      <div className="overflow-y-auto w-screen">
        <Switch>
          <Route path="/" exact>
            <MainPage></MainPage>
          </Route>
          <Route path="/login">
            <Login></Login>
          </Route>
          <Route path="/allusers">
            <AllUsers></AllUsers>
          </Route>
          <Route path="/alltweets">
            <AllTweets></AllTweets>
          </Route>
        </Switch>
      </div>

    </div>

  )
}
