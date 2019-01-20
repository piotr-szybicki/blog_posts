package com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters.StringToUUID;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters.UUIDToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "movie_details_uuid_assigned")
@Table(name = "movie_details_uuid_assigned")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDetails {

    @Id
    @JsonSerialize(converter = UUIDToString.class)
    @JsonDeserialize(converter = StringToUUID.class)
    private UUID id;

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
