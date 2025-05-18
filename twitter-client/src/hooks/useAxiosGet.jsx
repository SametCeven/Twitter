import { useEffect, useState } from "react"
import axiosInstance from "../services/api"


export default function useAxiosGet(url,token,params={}){
    const [data,setData] = useState(null)
    const [loading,setLoading] = useState(true)
    const [error,setError] = useState(false)

    useEffect(()=>{
        if(!url) return;

        const getData = async () => {
            setLoading(true)
            try{
                const res = await axiosInstance.get(url,{
                    headers: {
                        Authorization: `Bearer ${token}`
                    },
                    params,
                })
                //console.log(res)
                setData(res.data)
                setLoading(false)
                setError(false)
            }
            catch(err) {
                console.error(err.message)
                setLoading(false)
                setError(true)
            }
        }
        getData()
    },[url, token, JSON.stringify(params)])

    return [data,loading,error]
}