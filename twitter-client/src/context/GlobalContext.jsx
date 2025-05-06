import { createContext, useState } from "react";


export const GlobalContext = createContext();
export default function GlobalContextProvider({children}){
    const [user,setUser] = useState("");


    return(
        <GlobalContext.Provider value={{user}}>
            {children}
        </GlobalContext.Provider>
    )


}