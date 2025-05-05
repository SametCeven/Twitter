import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:3000/twitter/api",
})

export default API;