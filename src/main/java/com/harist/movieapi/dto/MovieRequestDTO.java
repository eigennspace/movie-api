package com.harist.movieapi.dto;

import com.harist.movieapi.models.entities.Movie;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRequestDTO {

    private BigInteger id;

    @NotEmpty(message = "Title is Mandatory")
    private String title;

    @NotEmpty(message = "Description is Mandatory")
    private String description;

    private ActorRequestDTO actorRequestDTO;

    public Movie convertToEntity() {
        return Movie.builder().id(this.id).title(this.title).description(this.description).actor(this.actorRequestDTO.convertToEntity()).build();
    }
}
