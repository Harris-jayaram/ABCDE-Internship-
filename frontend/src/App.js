import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import ItemList from './components/ItemList';
import './App.css';

const App = () => {
  const [token, setToken] = useState(null);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login setToken={setToken} />} />
        <Route path="/items" element={<ItemList token={token} />} />
      </Routes>
    </Router>
  );
};

export default App;
