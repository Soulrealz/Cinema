import React from 'react';
import { Card, Table, ButtonGroup, Button } from 'react-bootstrap';
import axios from 'axios';

function MovieList() {


    const [movies, setMovies] = React.useState([]);


    React.useEffect(() => {
        axios.get("http://localhost:8080/movies").then(response => response.data)
            .then(data => setMovies(data))
            .catch(err => console.log(err));
    }, []);

    return (
        <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>Movie List</Card.Header>
            <Card.Body>
                <Table bordered hover striped variant="dark">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Duration</th>
                            <th>Genre</th>
                            <th>Year</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            movies.length === 0 ?
                                <tr align="center">
                                    <td colSpan="4">No Movies Available.</td>
                                </tr> :
                                movies.map((movie) => (
                                    <tr key={movie.id}>
                                        <td>{movie.name}</td>
                                        <td>{movie.duration}</td>
                                        <td>{movie.genre}</td>
                                        <td>{movie.year}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Button size="sm" variant="outline-primary"> Edit </Button>
                                                <Button size="sm" variant="outline-danger"> Delete </Button>
                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                ))
                        }
                    </tbody>
                </Table>
            </Card.Body>
        </Card>
    )
}

export default MovieList;