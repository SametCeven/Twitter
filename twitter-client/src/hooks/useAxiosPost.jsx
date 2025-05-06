import { useEffect, useState } from "react"
import axiosInstance from "../services/api"


export default function useAxiosPost(url, body){

    const [data,setData] = useState(null)
    const [loading,setLoading] = useState(false)
    const [error,setError] = useState(false)

    const postData = async (url, body) => {
        setLoading(true)
        try{
            const res = await axiosInstance.post(url, body)
            setData(res.data)
            setLoading(false)
            setError(false)
        }
        catch(err) {
            console.error(err.message)
            console.error(err.response.data.message)
            setLoading(false)
            setError(true)
        }
    }

    return [data,loading,error,postData]
}