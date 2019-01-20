package com.orm.udemy.mysql.repos.entities.sameid.sequence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="movie_same_sequence")
@Table(name="movie_same_sequence")
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
