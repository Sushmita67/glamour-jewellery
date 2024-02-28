import { useAuthContext } from '../../hooks/useAuthContext';
import { Link } from 'react-router-dom';
import { SearchBar } from "../SearchBar";

import { AiOutlineHeart, AiOutlineUserAdd, AiOutlineUser, AiOutlinePlus, AiOutlineUnorderedList, AiOutlineLogin } from "react-icons/ai";

import Logo from "../../assets/logo.svg";
import styles from "./style.module.css";


export const Header = () => {

  const { token } = useAuthContext();
  const role = localStorage.getItem("role");

  console.log('Role::',role);

  return (
    <header className={`${styles.header} container-padding`}>
      <div className={`${styles.headerArea} max-width`}>
        <Link to="/">
          <img className={styles.logo} src={Logo} alt="Logo" />
        </Link>
        <SearchBar />
        <div className={styles.actions}>
          {token ?
            <>
              {role === "Admin" && (
                  <Link to="/add-product"><AiOutlinePlus/></Link>
              )}
              {role === "Admin" && (
                  <Link to="/list-product"><AiOutlineUnorderedList/> </Link>
              )}
              {role === "Customer" && (
              <Link to="/favorites">
                <AiOutlineHeart />
              </Link>
              )}
              <Link to="/profile">
                <AiOutlineUser />
              </Link>

            </>
            :
            <>
              <Link to="/register">
                <AiOutlineUserAdd />
              </Link>
              <Link to="/login">
                <AiOutlineLogin />
              </Link>
            </>
          }
        </div>
      </div>
    </header>
  );
}
