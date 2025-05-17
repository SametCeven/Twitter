export default function User(props){
    const {id, username, email, firstName, lastName, profilePicture} = props;



    return(
        <div className="border-2 rounded-xl w-100 px-5 py-5 ">
            <div className="flex justify-between">
                <span className="font-bold mr-5"> Username : </span>
                <span> {username} </span>
            </div>
            <div className="flex justify-between">
                <span className="font-bold mr-5"> Email : </span>
                <span> {email} </span>
            </div>
            <div className="flex justify-between">
                <span className="font-bold mr-5"> First Name : </span>
                <span> {firstName} </span>
            </div>
            <div className="flex justify-between">
                <span className="font-bold mr-5"> Last Name : </span>
                <span> {lastName} </span>
            </div>
            <div className="flex justify-between">
                <span> {profilePicture} </span>
            </div>
            

        </div>

    )
}
