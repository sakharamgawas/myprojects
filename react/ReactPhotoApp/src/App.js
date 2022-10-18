import './App.css';
import Photo from './components/Photos/Photo';
import 'bootstrap/dist/css/bootstrap.min.css'
import Navbar from './components/Navbar';
import About from './components/About'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <Routes>
          <Route path="/" element={<About/>}/>
          <Route path="/Photos" element={<Photo/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
