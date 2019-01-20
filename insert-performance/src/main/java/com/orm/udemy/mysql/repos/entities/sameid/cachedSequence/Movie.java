package com.orm.udemy.mysql.repos.entities.sameid.cachedSequence;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="movie_same_cached_sequence")
@Table(name="movie_same_cached_sequence")
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GenericGenerator(
            name = "movieSequenceGenerator",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "optimizer",
                            value = "pooled"
                    ),
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value",
                            value = "1"
                    ),
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size",
                            value = "20"
                    )
            }
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movieSequenceGenerator"
    )
    private Long id;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private MovieDetails movieDetails;

    private String title;

    private String shortDescription;

}
