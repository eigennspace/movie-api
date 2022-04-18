package com.harist.movieapi.services;

import com.harist.movieapi.helpers.MovieNotFoundException;
import com.harist.movieapi.models.entities.Movie;
import com.harist.movieapi.models.repos.MovieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepo movieRepo;

    public List<Movie> getMovies() {
        return this.movieRepo.findAll();
    }

    public Movie createMovie(Movie movie) {
        return this.movieRepo.save(movie);
    }

    public Movie getOne(BigInteger id) {
        Optional<Movie> optionalMovie = this.movieRepo.findById(id);
        if (!optionalMovie.isPresent()) {
            throw new MovieNotFoundException();
        }
        return optionalMovie.get();
    }

    public Movie updateMovie(Movie movie) {
        this.getOne(movie.getId());

        return this.movieRepo.save(movie);
    }

    public void deleteMovie(Movie movie) {
        Movie deleteMovie = this.getOne(movie.getId());

        this.movieRepo.delete(deleteMovie);
    }

    public List<Movie> getMoviesByActor(BigInteger id) {
        return this.movieRepo.findAllByActorId(id);
    }
}
