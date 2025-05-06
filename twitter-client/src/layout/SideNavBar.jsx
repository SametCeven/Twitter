import { useContext } from "react"
import { GlobalContext } from "../context/GlobalContext"

export default function SideNavBar(){

    const {loggedInUser} = useContext(GlobalContext);

    console.log(loggedInUser)

    return(
        <div>
            <p>
                {loggedInUser ? loggedInUser.username : "Login"}
            </p>
            <p> nav bar </p>
        </div>
    )
}