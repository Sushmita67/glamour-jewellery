import axios from "axios";


const API = import.meta.env.VITE_API;


export const api = axios.create({
  baseURL: "http://localhost:8080/",
});