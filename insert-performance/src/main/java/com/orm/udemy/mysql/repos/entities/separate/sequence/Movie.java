package com.orm.udemy.mysql.repos.entities.separate.sequence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name="movie_separate_sequence")
@Table(name="movie_separate_sequence")
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private MovieDetails movieDetails;

    private String title;

    private String shortDescription;

}
