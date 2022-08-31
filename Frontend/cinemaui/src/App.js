import './App.css';
import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Movie from './components/Movie';
import MovieList from './components/MovieList';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';

function App() {

  const marginTop = {
    marginTop: "20px"
  }

  return (

    <Router>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
            <Routes>
              <Route exact path="" element={<Welcome />}></Route>
              <Route exact path="/add" element={<Movie />}></Route>
              <Route exact path="/list" element={<MovieList />}></Route>
            </Routes>
          </Col>
        </Row>
      </Container>
    </Router>
  );
}

export default App;
