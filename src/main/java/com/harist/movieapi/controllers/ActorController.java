package com.harist.movieapi.controllers;

import com.harist.movieapi.dto.ActorRequestDTO;
import com.harist.movieapi.dto.ActorResponseDTO;
import com.harist.movieapi.models.entities.Actor;
import com.harist.movieapi.services.ActorService;
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
public class ActorController {
    private ActorService actorService;

    @GetMapping("/actors")
    public ResponseEntity<List<ActorResponseDTO>> getAllActors() {
        List<Actor> actors = this.actorService.getActors();
        List<ActorResponseDTO> actorResponseDTOS = actors.stream()
                .map(actor -> actor.convertToResponse())
                .collect(Collectors.toList());

        return ResponseEntity.ok(actorResponseDTOS);
    }

    @PostMapping("/actor")
    public ResponseEntity<ActorResponseDTO> createActor(@Valid @RequestBody ActorRequestDTO actorRequestDTO) {
        Actor newActor = actorRequestDTO.convertToEntity();

        Actor actor = this.actorService.createActor(newActor);
        ActorResponseDTO actorResponseDTO = actor.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(actorResponseDTO);
    }

    @GetMapping("/actor/{id}")
    public ResponseEntity<ActorResponseDTO> getActor(@PathVariable("id") BigInteger id) {
        Actor actor = this.actorService.getOne(id);

        ActorResponseDTO actorResponseDTO = actor.convertToResponse();

        return ResponseEntity.ok(actorResponseDTO);
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<ActorResponseDTO> updateActor(@PathVariable("id") BigInteger id, @Valid @RequestBody ActorRequestDTO actorRequestDTO) {
        Actor actor = actorRequestDTO.convertToEntity();
        actor.setId(id);

        Actor updatedActor = this.actorService.updateActor(actor);

        return ResponseEntity.ok(updatedActor.convertToResponse());
    }

    @DeleteMapping("actor/{id}")
    public void deletedMovie(@PathVariable("id") BigInteger id) {
        Actor actor = new Actor();
        actor.setId(id);

        this.actorService.deleteActor(actor);
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
