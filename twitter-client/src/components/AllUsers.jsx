import { useContext } from "react"
import { GlobalContext } from "../context/GlobalContext"

export default function AllUsers() {

    const { allUsers, loadingAllUsers, errorAllUsers } = useContext(GlobalContext);

    console.log(loadingAllUsers);
    console.log(allUsers)

    return (
        <div>
            {loadingAllUsers ? <p> Loading ... </p> : 

                allUsers.map((user) => {
                    return (
                        <p key={user.id}> {user.username} </p>
                    )
                })
            }


        </div>
    )
}