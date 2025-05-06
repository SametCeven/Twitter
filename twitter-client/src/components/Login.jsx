import { useContext, useEffect, useState } from "react"
import { GlobalContext } from "../context/GlobalContext"
import useAxiosPost from "../hooks/useAxiosPost";
import { useHistory } from "react-router-dom/cjs/react-router-dom.min";

export default function LoginPage() {

    const { loggedInUser, setLoggedInUser, token, setToken } = useContext(GlobalContext)
    const [user, setUser] = useState({
        email: "",
        password: "",
    });
    const [data, loading, error, postData] = useAxiosPost();
    const history = useHistory();

    function handleChange(e) {
        const { value, name } = e.target;
        setUser({ ...user, [name]: value })
    }

    function handleLoginSubmit(e) {
        e.preventDefault();
        postData("/auth/login/user", user)
    }

    function handleLogoutSubmit(e) {
        e.preventDefault();
        localStorage.removeItem("JWT_Token");
        setToken("");
        setLoggedInUser({});
    }

    function handleGoHomeSubmit(e) {
        e.preventDefault();
        history.push("/");
    }

    useEffect(() => {
        if (data && data.token) {
            setLoggedInUser({
                id: data.id,
                username: data.username,
                email: data.email,
            })
            setToken(data.token);
        }
    }, [data])

    if (token) {
        return (
            <div className="flex flex-col w-80 gap-10 mt-20 mx-20">
                <form className="flex flex-col items-center gap-10" onSubmit={handleLogoutSubmit}>
                    <p> Logged in as {loggedInUser.username} </p>
                    <button className="btn"> Logout </button>
                </form>
                <button className="btn" onClick={handleGoHomeSubmit}> Home </button>
            </div>
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

            <button className="btn" type="submit"> {loading ? "Logging In ..." : "Login"} </button>

            {error && <p> Login failed. Try again. </p>}

        </form>
    )
}