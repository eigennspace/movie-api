package com.harist.movieapi.services;

import com.harist.movieapi.helpers.ActorNotFoundException;
import com.harist.movieapi.models.entities.Actor;
import com.harist.movieapi.models.repos.ActorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ActorService {

    private final ActorRepo actorRepo;

    public List<Actor> getActors() {
        return this.actorRepo.findAll();
    }

    public Actor createActor(Actor actor) {
        return this.actorRepo.save(actor);
    }

    public Actor updateActor(Actor actor) {
        this.getOne(actor.getId());

        return this.actorRepo.save(actor);
    }

    public Actor getOne(BigInteger id) {
        Optional<Actor> optionalMovie = this.actorRepo.findById(id);
        if (!optionalMovie.isPresent()) {
            throw new ActorNotFoundException();
        }
        return optionalMovie.get();
    }

    public void deleteActor(Actor actor) {
        Actor deleteActor = this.getOne(actor.getId());

        this.actorRepo.delete(deleteActor);
    }
}
