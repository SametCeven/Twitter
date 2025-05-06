import { createContext, useEffect, useState } from "react";
import useLocalStorage from "../hooks/useLocalStorage";
import axiosInstance from "../services/api";


export const GlobalContext = createContext();
export default function GlobalContextProvider({children}){
    const [loggedInUser,setLoggedInUser] = useState({
        id:"",
        username:"",
        email:"",
    });
    const [token, setToken] = useLocalStorage("JWT_Token","");
   
    
    
    
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
        <GlobalContext.Provider value={{loggedInUser, setLoggedInUser, token, setToken}}>
            {children}
        </GlobalContext.Provider>
    )


}