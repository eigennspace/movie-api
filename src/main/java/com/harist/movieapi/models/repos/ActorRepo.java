package com.harist.movieapi.models.repos;

import com.harist.movieapi.models.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface ActorRepo extends JpaRepository<Actor, BigInteger> {
}
