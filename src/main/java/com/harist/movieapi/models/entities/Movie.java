package com.harist.movieapi.models.entities;

import com.harist.movieapi.dto.MovieResponseDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotEmpty(message = "Title is Mandatory")
    private String title;

    @NotEmpty(message = "Description is Mandatory")
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;

    public MovieResponseDTO convertToResponse() {
        return MovieResponseDTO.builder().id(this.id)
                .title(this.title)
                .description(this.description).createdAt(this.createdAt).updatedAt(this.updatedAt).actorResponseDTO(this.actor.convertToResponse()).build();
    }


}
