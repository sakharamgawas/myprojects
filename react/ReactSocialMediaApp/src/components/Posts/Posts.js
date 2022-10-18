
import axios from "axios"
import { useEffect, useState } from "react"
import { Row, Col, Container, Spinner, Card, Button, Badge } from 'react-bootstrap'
import './Posts.css'
import { AiFillLike, AiOutlineLike } from 'react-icons/ai'
import { FaRegComment,FaUserCircle } from 'react-icons/fa'
import dummyText from '../../assets/dummyText.json'


// sdffdfdsaefsdf@gmail.com
const Photo = () => {

    let [photos, setPhotos] = useState([])
    useEffect(() => {
        // axios.get("https://picsum.photos/v2/list")
        // .then(res=>console.log(res))
        // .catch(err=>console.log(err))
        const getdata = async () => {
            try {
                let res = await axios.get("https://picsum.photos/v2/list")
                console.log(res.data.slice(10))
                //  console.log(res.data)
                setPhotos(res.data.splice(0, 3))
                // console.log(res.data.splice(10))
                //  console.log(res.data)
            } catch (error) {
                console.log(error)
            }
        }
        getdata()

    }, [])



    return (
        <Container className="posts-container">
            <Row>
                {
                    photos.length ?
                        photos.map((photo) => (
                            <Col md={12} key={photo.id} className="lonePhoto">

                                <Card >
                                    <Card.Header className="post-header">
                                       <div>
                                       <FaUserCircle/>{' '}
                                        {photo.author}
                                       </div>
                                       <p className="post-time">few seconds ago</p>
                                       </Card.Header>
                                    <Card.Img variant="top" className="img" src={photo.download_url} />
                                    <Card.Body>
                                        <p className="lead">{dummyText[Math.ceil(Math.random()*3)]}</p>
                                        <Row>
                                            <Col md={{span:7,offset:5}}>
                                                <Button variant="light">
                                                    <AiOutlineLike />{' '}
                                                    <Badge pill bg="dark" >dark</Badge>
                                                </Button> {''}
                                                <Button variant="light">
                                                    {/* <p className="lead">121 Comments</p> */}
                                                    <FaRegComment/>{' '}
                                                    <Badge pill bg="dark" >dark</Badge>
                                                </Button>

                                            </Col>
                                        </Row>
                                    </Card.Body>
                                </Card>

                            </Col>
                        )) :
                        <div className="spinner">
                            <Spinner animation="border" role="status">
                            </Spinner>
                        </div>
                }
            </Row>

        </Container >
    )
}

export default Photo