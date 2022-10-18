import './App.css';
import Photo from './components/Posts/Posts';
import 'bootstrap/dist/css/bootstrap.min.css'
import Navbar from './components/Navbar';
import About from './components/About'
import { BrowserRouter, Routes, Route,Link } from 'react-router-dom'

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <Link to="/about">about</Link>{' '}
        <Link to="/home">home</Link>
        <Routes>
          <Route path="/about" element={<About/>}/>
          <Route path="/home" element={<Photo/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
