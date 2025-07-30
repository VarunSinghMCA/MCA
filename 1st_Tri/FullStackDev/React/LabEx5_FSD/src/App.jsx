import './App.css'
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
  Navigate,
} from "react-router-dom";

import ErrPage from './components/ErrPage';
// import Welcome from './components/Welcome';
import Navbar from './components/Navbar';
import Home from './Home';
import Contact from './components/Contact';
import Menu from './components/Menu';

function App() {

  const routes = [
    {
      path: "home",
      element: <Home />
    },{
      path: "contact",
      element: <Contact/>
    },{
      path: "menu",
      element: <Menu/>
    }

  ];

  return (
    <>
      <Router>
        <Routes>
          {/* Redirect root to /auth/login */}
          <Route path="/" element={<Navigate to="/home" replace />} />
          {/* Main app layout */}
          <Route element={<Layout />}>
            {routes.map((r, index) => (
              <Route key={index} path={r.path} element={r.element} />
            ))}
          </Route>

          {/* <Route path="/share/:hash" element={<ShareView />} /> */}
          {/* Auth routes wrapped in AuthLayout */}
          {/* <Route path="/auth" element={<AuthLayout />}>
            <Route path="login" element={<Login />} />
            <Route path="signup" element={<Signup />} />
          </Route> */}

          <Route path="*" element={<ErrPage />} />
        </Routes>
      </Router>
    </>
  )
}

function Layout() {
  return (
    <>
      <div className='h-dvh w-dvw realtive m-0 p-0 bg-black bg-gradient-to-r from-[#254252] to-[#1E2E3E] '>
        <div className='sticky md:top-6 top-0 z-50 w-full flex items-center justify-center'>
          <Navbar />
        </div>
        {/* Main content area */}
        <Outlet />
      </div>
    </>
  );
}

// function AuthLayout() {
//   return (
//     <>
//       <div 
//         className='h-dvh w-dvw grid place-items-center m-0 p-0'
//         style={{
//           background: "linear-gradient(to bottom, #fda0a0 0%, #fda0a0 30%, #fff 100%)",
//         }}
//       >
//         <div className='h-full w-[90%] flex items-center justify-evenly gap-16'>
//           <span className='w-full h-full flex flex-col items-center'>
//             <img src="/authImage.svg" alt="Logo" className='h-[600px] w-auto rounded-md ' />
//             <h1 className='text-[#e1434b] text-4xl pl-2'><b>Second Brain App</b></h1>
//           </span>
//           <main className='w-full max-w-[40%] h-[80%] mr-12 flex justify-center items-center'>
//             <Outlet />
//           </main>
//         </div>
//       </div>
//     </>
//   );
// }

export default App
