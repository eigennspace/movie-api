package com.harist.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class ActorResponseDTO {

    private BigInteger id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
