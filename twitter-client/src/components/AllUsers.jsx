import { useContext } from "react"
import { GlobalContext } from "../context/GlobalContext"
import User from "./User";

export default function AllUsers() {

    const { allUsers, loadingAllUsers, errorAllUsers } = useContext(GlobalContext);

    return (
        <div className="flex flex-col gap-5 mx-10 my-10">
            {loadingAllUsers ? <p> Loading ... </p> : 

                allUsers.map((user)=>{
                    return <User 
                        key={user.id} 
                        id={user.id} 
                        username={user.username} 
                        email = {user.email}
                        firstName = {user.firstName}
                        lastName = {user.lastName}
                        profilePicture = {user.profilePicture}
                        ></User>
                })

            }


        </div>
    )
}