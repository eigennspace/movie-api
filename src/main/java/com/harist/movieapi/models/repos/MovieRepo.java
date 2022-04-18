package com.harist.movieapi.models.repos;

import com.harist.movieapi.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public interface MovieRepo extends JpaRepository<Movie, BigInteger> {
    List<Movie> findAllByActorId(BigInteger id);
}
