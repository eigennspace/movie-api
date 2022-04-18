package com.harist.movieapi.models.entities;

import com.harist.movieapi.dto.ActorResponseDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotEmpty(message = "Name is Mandatory")
    private String name;

    @OneToMany
    private List<Movie> movie;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ActorResponseDTO convertToResponse() {

        return ActorResponseDTO.builder().id(this.id).name(this.name)
                .createdAt(this.createdAt).updatedAt(this.updatedAt).build();
    }
}
