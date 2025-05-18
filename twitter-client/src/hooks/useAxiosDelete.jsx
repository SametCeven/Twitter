import { useEffect, useState } from "react"
import axiosInstance from "../services/api"


export default function useAxiosDelete(url, token) {
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(false)

    const deleteData = async (url, token) => {
        setLoading(true)
        try {
            const res = await axiosInstance.delete(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                },
            })
            //console.log(res)
            setLoading(false)
            setError(false)
        }
        catch (err) {
            console.error(err.message)
            setLoading(false)
            setError(true)
        }
    }


    return [loading, error, deleteData]
}