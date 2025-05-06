import axios from "axios";

const API_KEY = import.meta.env.VITE_SERVER_API_KEY

const axiosInstance = axios.create({
    baseURL: "http://localhost:3000/twitter/api",
    headers : {
        "X-API-KEY": API_KEY
    }
})

export default axiosInstance;