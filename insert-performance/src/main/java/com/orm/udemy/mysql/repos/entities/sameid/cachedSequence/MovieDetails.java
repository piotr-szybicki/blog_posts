package com.orm.udemy.mysql.repos.entities.sameid.cachedSequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "movie_details_same_cached_sequence")
@Table(name = "movie_details_same_cached_sequence")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDetails {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Movie movie;

    @OneToMany(
            mappedBy = "movieDetails",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Actor> actors = new ArrayList<>();

    private Long usGross;
    private Long worldwideGross;
    private Long productionBudget;
    private String releaseDate;
    private String mpaaRating;
    private Integer runningTimeMin;
    private String distributor;
    private String source;
    private String majorGenre;
    private String creativeType;
    private String director;
    private String rottenTomatoesRating;
    private Double imdbRating;
    private Long imdbVotes;

}
