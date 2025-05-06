import { useContext, useEffect, useState } from "react"
import { GlobalContext } from "../context/GlobalContext"
import useAxiosPost from "../hooks/useAxiosPost";

export default function LoginPage() {

    const {loggedInUser, setLoggedInUser, token, setToken } = useContext(GlobalContext)
    const [user, setUser] = useState({
        email: "",
        password: "",
    });
    const [data, loading, error, postData] = useAxiosPost();

    function handleChange(e) {
        const {value, name} = e.target;
        setUser({ ...user, [name]: value })
    }

    function handleLoginSubmit(e) {
        e.preventDefault();
        postData("/auth/login/user", user)
    }

    function handleLogoutSubmit(e){
        e.preventDefault();
        localStorage.removeItem("JWT_Token");
        setToken("");
        setLoggedInUser({});
    }

    useEffect(()=>{
        if (data && data.token) {
            setLoggedInUser({
                id: data.id,
                username: data.username,
                email: data.email,
            })
            setToken(data.token);
        }
    },[data])

    if(token){
        return(
            <form onSubmit={handleLogoutSubmit}>
                <p> Logged In </p>
                <button> Log Out </button>
            </form>
        )
    }

    

    return (
        <form
            className="flex flex-col w-80 gap-10 mt-20 mx-20"
            onSubmit={handleLoginSubmit}>

            <div className="input-label">
                <label htmlFor="email"> Email </label>
                <input className="input" type="text" name="email" id="email" onChange={handleChange} />
            </div>

            <div className="input-label">
                <label htmlFor="password"> Password </label>
                <input className="input" type="text" name="password" id="password" onChange={handleChange} />
            </div>

            <button className="hover:bg-amber-800" type="submit"> {loading ? "Logging In ..." : "Login"} </button>

            {error && <p> Login failed. Try again. </p>}

        </form>
    )
}