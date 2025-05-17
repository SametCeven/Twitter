import { createContext, useEffect, useState } from "react";
import useLocalStorage from "../hooks/useLocalStorage";
import axiosInstance from "../services/api";
import useAxiosGet from "../hooks/useAxiosGet"

export const GlobalContext = createContext();
export default function GlobalContextProvider({children}){
    const [loggedInUser,setLoggedInUser] = useState({
        id:"",
        username:"",
        email:"",
    });
    const [token, setToken] = useLocalStorage("JWT_Token","");
    const [allUsers,loadingAllUsers,errorAllUsers] = useAxiosGet("/user", token);
     const [allTweets,loadingTweets,errorTweets] = useAxiosGet("/tweet/getAll", token);


    
    useEffect(()=>{
        if(token){
            axiosInstance.get("/auth/me",{
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            .then(res => {
                setLoggedInUser({
                    id:res.data.id,
                    username:res.data.username,
                    email:res.data.email,
                })
            })
            .catch(err => {
                console.error(err);
                setToken("");
            })
    
        }
    },[])



    return(
        <GlobalContext.Provider value={{loggedInUser, setLoggedInUser, token, setToken, allUsers, loadingAllUsers, errorAllUsers, allTweets, loadingTweets, errorTweets}}>
            {children}
        </GlobalContext.Provider>
    )


}