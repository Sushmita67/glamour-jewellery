import { useState } from "react";
import { UserDataType } from '../interfaces/UserData';
import { requestErrorMessages } from "../constants/requestErrorMessages";
import { useAuthContext } from '../hooks/useAuthContext';
import { getLocalStorageItem } from '../utils/localStorageActions';
import { api } from "./api";
import {useNavigate} from "react-router-dom";


export const fetchUserDataService = () => {

  const userID = getLocalStorageItem("id") || 0; // Set userID to 0 if it's null

  const navigate = useNavigate();
  const { token } = useAuthContext();
  const [userData, setUserData] = useState<UserDataType | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);


  async function getUserData() {

    setLoading(true);
    setError(null);

    try {
      if (userID!=0) {

        const response: any = await api.get(`http://localhost:8080/system-user/getById/${userID}`, {
          headers: {Authorization: "Bearer " + token}
        });

        setUserData(response);
        console.log('User Data Response:::', response);

      }

    } catch (error) {

      setError(requestErrorMessages.genericError);
      setLoading(false);

    }

    setLoading(false);
  }

  return ({ getUserData, userData, loading, error });
}
