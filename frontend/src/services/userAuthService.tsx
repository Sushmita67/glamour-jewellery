import { useState } from 'react';
import { FormDataType } from '../interfaces/FormData';
import { useNavigate } from 'react-router-dom';
import { requestErrorMessages } from '../constants/requestErrorMessages';
import { useAuthContext } from '../hooks/useAuthContext';
import { setLocalStorageItem } from '../utils/localStorageActions';
import { api } from "./api";
import axios from "axios";
import {toast} from "react-toastify";


export const userAuthService = () => {

  const navigate = useNavigate();
  const { setToken } = useAuthContext();
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);


  async function registerUser(data: FormDataType, confirmPassword: string) {

    setLoading(true);
    setError(null);

    const arePasswordEquals = data.password !== confirmPassword;
    const passwordLength = data.password && data.password.length < 8;

    try {

      if (arePasswordEquals) {
        throw requestErrorMessages.differentPasswords;
      } else if (passwordLength) {
        throw requestErrorMessages.passwordLength;
      }

      const response: any = await axios.post("http://localhost:8080/system-user/save", data);


      // if (response) {
      //
      //
      //   setToken(response.token);
      //   console.log('I am setting token:::', response.token);
      //   setLocalStorageItem("token", response.data.token);
      //   setLocalStorageItem("id", response.data.userId);
      //
      // }

    } catch (error: any) {

      setLoading(false);

      if (error == requestErrorMessages.differentPasswords) {
        setError(error)
        return;
      } else if (error == requestErrorMessages.passwordLength) {
        setError(error)
        return;
      } else if (error.response.data == requestErrorMessages.emailAlreadyExists) {
        setError(requestErrorMessages.emailAlreadyExists)
        return;
      } else {
        setError(requestErrorMessages.genericError);
        return;
      };
    }

    setLoading(false);
    navigate("/");
  }


  async function loginUser(data: FormDataType) {
    setLoading(true);
    setError(null);

    try {
      const response = await axios.post("http://localhost:8080/authenticate", data);
      toast.success("Logged In Successfully!");
      console.log("Token::", response.data.data.token);
      if (response.data && response.data.data.token) {
        setToken(response.data.data.token);
        setLocalStorageItem("token", response.data.data.token);
        setLocalStorageItem("id", response.data.data.userId);
        setLocalStorageItem("role", response.data.data.role);
      }
    } catch (error) {
      toast.error("Error! Please Try Again!!")
      setLoading(false);
      setError(requestErrorMessages.genericError);
      console.error("Error during login:", error);
    }

    setLoading(false);
    // navigate("/");
  }


  function logoutUser() {

    localStorage.removeItem("token");
    localStorage.removeItem("id");
    localStorage.removeItem("role");

    setToken(null);
    navigate("/home");

  }


  return ({
    registerUser,
    loginUser,
    logoutUser,
    loading,
    error
  });
}
