import {Nav,Navbar,Container} from 'react-bootstrap'
import photologo from '../assets/logo.png'
import { NavLink } from 'react-router-dom'
const NavbarComponent = ()=>{
    return (
        <>
          <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand href="#home">
          <img
          
              src={photologo}
            //   src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.6y-vUpd6njBoIRKYLJ8FaAHaFI%26pid%3DApi%26h%3D160&f=1"
              width="30"
              height="30"
              style={{borderRadius:'100%'}}
              className="d-inline-block align-top "
              alt="React Bootstrap logo"
            />{' '}
              Photo App</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link to='/' as={NavLink}>Home</Nav.Link>
            <Nav.Link as={NavLink} to='/photos'>aboutUs</Nav.Link>
            <Nav.Link href="#contactus">Contactus</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link href="#signup">signup</Nav.Link>
            <Nav.Link href="#login">login</Nav.Link>

          </Nav>
        </Container>
      </Navbar>
        </>
    )
}
export default NavbarComponent