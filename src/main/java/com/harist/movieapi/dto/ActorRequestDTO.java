package com.harist.movieapi.dto;

import com.harist.movieapi.models.entities.Actor;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorRequestDTO {

    private BigInteger id;

    @NotEmpty(message = "Name is Mandatory")
    private String name;

    public Actor convertToEntity() {
        return Actor.builder().id(this.id).name(this.name).build();
    }
}
