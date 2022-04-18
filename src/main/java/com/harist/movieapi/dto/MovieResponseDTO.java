package com.harist.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@AllArgsConstructor

public class MovieResponseDTO {

    private BigInteger id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private ActorResponseDTO actorResponseDTO;

}
