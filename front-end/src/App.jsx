import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import HelloWorld from './HelloWorld'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import EmployeeComponent from './components/EmployeeComponent'
function App() {

  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/*//http://localhost:8700 */}
          <Route path="/" element={<ListEmployeeComponent />}></Route>
          {/*//http://localhost:8700/employees */}
          <Route path="/employees" element={<ListEmployeeComponent />}></Route>
          {/*//http://localhost:8700/add-employee */}
          <Route path="/add-employee" element={<EmployeeComponent />}></Route>
        </Routes>
        <FooterComponent/>
      </BrowserRouter>

    </>
  )
}

export default App
