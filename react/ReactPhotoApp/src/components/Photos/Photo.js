
import axios from "axios"
import { useEffect, useState } from "react"
import { Row, Col, Container,Spinner,  Card } from 'react-bootstrap'
import './Photo.css'



const Photo = () => {
    
    let [photos, setPhotos] = useState([])
    useEffect(() => {
        // axios.get("https://picsum.photos/v2/list")
        // .then(res=>console.log(res))
        // .catch(err=>console.log(err))
        const getdata = async () => {
            try {
             let   res = await axios.get("https://picsum.photos/v2/list")
             console.log(res.data.slice(10))
            //  console.log(res.data)
             setPhotos(res.data.splice(0,10))
            // console.log(res.data.splice(10))
            //  console.log(res.data)
            } catch (error) {
                console.log(error)
            }
        }
        getdata()
        
    }, [])


  
    return (
        <>
            <Container className='afterNavbar'>
                <Row>
                    {
                        photos.length?
                        photos.map((photo) => (
                            <Col md={3} key={photo.id} className="lonePhoto">

                                <Card >
                                    <Card.Header>{photo.author}</Card.Header>
                                    <Card.Img variant="top" className="img" src={photo.download_url} />
                                    <Card.Body>
                                        <a className="btn btn-dark" href={photo.url}>View Source</a>
                                    </Card.Body>
                                </Card>

                            </Col>
                        ))  :
                        <div className="spinner">
                            <Spinner animation="border" role="status">
                      </Spinner>
                        </div>
                    }
                </Row>

            </Container>
        </>
    )
}

export default Photo