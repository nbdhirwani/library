import React from 'react';
import './App.css';
import Home from './components/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BookList from './components/BookList';
import BookEdit from './components/BookEdit';

function App() {

  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/books' exact={true} element={<BookList/>}/>
        <Route path='/books/new' exact={true} element={<BookEdit/>}/>
      </Routes>
    </Router>
  );
}

export default App;
