import { AuthContextProvider } from './context/AuthContext';
import { AppRoutes } from './routes/AppRoutes';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const App = () => {
  return (

    <AuthContextProvider>
      <AppRoutes />

    </AuthContextProvider>

  )
}

export default App;
