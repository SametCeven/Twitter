import { useEffect, useState } from "react"
import axiosInstance from "../services/api"


export default function useAxiosGet(url, token){

    const [data,setData] = useState([])
    const [loading,setLoading] = useState(true)
    const [error,setError] = useState(false)

    useEffect(()=>{
        const getData = async () => {
            setLoading(true)
            try{
                const res = await axiosInstance.get(url,{
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
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
    },[])

    return [data,loading,error]
}