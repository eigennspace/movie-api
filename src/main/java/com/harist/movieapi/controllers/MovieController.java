package com.harist.movieapi.controllers;

import com.harist.movieapi.dto.MovieRequestDTO;
import com.harist.movieapi.dto.MovieResponseDTO;
import com.harist.movieapi.models.entities.Movie;
import com.harist.movieapi.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        List<Movie> movies = this.movieService.getMovies();

        List<MovieResponseDTO> movieResponseDTOS = movies.stream()
                .map(movie -> movie.convertToResponse())
                .collect(Collectors.toList());

        return ResponseEntity.ok(movieResponseDTOS);
    }

    @PostMapping("/movie")
    public ResponseEntity<MovieResponseDTO> createMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        Movie newMovie = movieRequestDTO.convertToEntity();

        Movie movie = this.movieService.createMovie(newMovie);
        MovieResponseDTO movieResponseDTO = movie.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponseDTO);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable("id") BigInteger id, @Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        Movie movie = movieRequestDTO.convertToEntity();
        movie.setId(id);

        Movie updatedMovie = this.movieService.updateMovie(movie);

        return ResponseEntity.ok(updatedMovie.convertToResponse());
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieResponseDTO> getMovie(@PathVariable("id") BigInteger id) {
        Movie movie = this.movieService.getOne(id);

        MovieResponseDTO movieResponseDTO = movie.convertToResponse();

        return ResponseEntity.ok(movieResponseDTO);
    }

    @DeleteMapping("/movie/{id}")
    public void deletedMovie(@PathVariable("id") BigInteger id) {
        Movie movie = new Movie();
        movie.setId(id);

        this.movieService.deleteMovie(movie);
    }

    @GetMapping("/movies/actor{id}")
    public ResponseEntity<List<MovieResponseDTO>> getMovieActors(@PathVariable("id") BigInteger id) {
        List<Movie> movies = this.movieService.getMoviesByActor(id);

        List<MovieResponseDTO> movieResponseDTOS = movies.stream()
                .map(movie -> movie.convertToResponse())
                .collect(Collectors.toList());

        return ResponseEntity.ok(movieResponseDTOS);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
