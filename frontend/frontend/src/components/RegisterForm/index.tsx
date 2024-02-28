import { Link } from "react-router-dom";
import { useState, FormEvent } from "react";
import { userAuthService } from "../../services/userAuthService";
import { ErrorMessage } from "../ErrorMessage/";
import { FormDataType } from '../../interfaces/FormData';
import { Input } from "../Input";
import { FormButton } from "../FormButton";
import { LoadingScreen } from "../LoadingScreen";
import { Label } from "../Label";


export const RegisterForm = () => {

  const { registerUser, loading, error } = userAuthService();

  const [username, setUsername] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");


  function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();

    const data: FormDataType = {
      username,
      email,
      password,
    };

    registerUser(data, confirmPassword);
  }


  return (
    <form onSubmit={handleSubmit}>
      {loading && <LoadingScreen />}
      <Label label="Name">
        <Input
          type="text"
          value={username}
          setState={setUsername}
          placeholder="Full Name"
          required={true}
        />
      </Label>
      <Label label="E-mail">
        <Input
          type="email"
          value={email}
          setState={setEmail}
          placeholder="something@gmail.com"
          required={true}
        />
      </Label>
      <Label label="Create Password">
        <Input
          type="password"
          value={password}
          setState={setPassword}
          placeholder="Password must be at least eight characters"
          required={true}
        />
      </Label>
      <Label label="Confirm Password">
        <Input
          type="password"
          value={confirmPassword}
          setState={setConfirmPassword}
          placeholder="xxxxxxxx"
          required={true}
        />
      </Label>
      {error && <ErrorMessage message={error} />}
      <div className="form-actions">
          <FormButton>Register</FormButton>
          <br/>
        <Link to="/login">Already have an Account?</Link>

      </div>
    </form>
  );
}
