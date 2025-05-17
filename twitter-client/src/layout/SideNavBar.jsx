import { useContext } from "react"
import { GlobalContext } from "../context/GlobalContext"
import { Link, useHistory } from "react-router-dom/cjs/react-router-dom.min";

export default function SideNavBar(){

    const {loggedInUser, setLoggedInUser, token, setToken} = useContext(GlobalContext);


    return(
        <nav className="flex flex-col gap-5 h-[100dvh] w-60 bg-c1 text-white px-5">

            <div className="flex flex-col gap-5 my-20">
                <span className="font-bold">
                    {loggedInUser ? loggedInUser.username : "Login"}
                </span>    
            </div>

            <Link to="/"> Main Page </Link>
            <Link to="/allusers"> All Users </Link>
            <Link> All Tweets </Link>
            <Link> My Tweets </Link>
            <Link> My Comments </Link>
            <Link> My Likes </Link>
            <Link> My Retweets </Link>
            <Link className="mt-20 btn" to="/login" > {token ? "Logout" : "Login"}</Link>
        </nav>
    )
}