import React from 'react';

import { Card, Form, Button, Col } from 'react-bootstrap';
import axios from 'axios';

class Movie extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.movieChange = this.movieChange.bind(this);
        this.submitMovie = this.submitMovie.bind(this);
    }

    initialState = {
        Title: '',
        Duration: '',
        Genre: '',
        Year: ''
    }

    submitMovie(event) {
        // alert('Title: ' + this.state.title + '\nDuration: ' + this.state.duration + '\nGenre: ' + this.state.genre + '\nYear: ' + this.state.year);
        event.preventDefault();

        const moviee = {
            name: this.state.title,
            duration: this.state.duration,
            genre: this.state.genre,
            year: this.state.year
        }

        console.log(moviee);

        const json = JSON.stringify(moviee);

        console.log(json);

        axios.post("http://localhost:8080/movies", moviee)
            .then(response => {
                console.log(response);
                console.log(response.data);
            }).catch(err => console.log(err.response));

        this.setState(this.initialState);
    }

    movieChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }





    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Add Movie</Card.Header>
                <Card.Body>
                    <Form onSubmit={this.submitMovie} id="movieForm">
                        <Form.Group className="mb-3" controlId="titleForm">
                            <Form.Label>Title</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text" name="title"
                                value={this.state.title}
                                onChange={this.movieChange}
                                className={"bg-dark text-white"}
                                placeholder="Enter Movie Title" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="durationForm">
                            <Form.Label>Duration</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text" name="duration"
                                value={this.state.duration}
                                onChange={this.movieChange}
                                className={"bg-dark text-white"}
                                placeholder="Enter Movie Duration" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="genreForm">
                            <Form.Label>Genre</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text" name="genre"
                                value={this.state.genre}
                                onChange={this.movieChange}
                                className={"bg-dark text-white"}
                                placeholder="Enter Movie Genre" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="yearForm">
                            <Form.Label>Year</Form.Label>
                            <Form.Control required autoComplete="off"
                                type="text" name="year"
                                value={this.state.year}
                                onChange={this.movieChange}
                                className={"bg-dark text-white"}
                                placeholder="Enter Movie Year" />
                        </Form.Group>

                        <Button variant="success" type="submit">
                            Submit
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        );
    }
}

export default Movie;