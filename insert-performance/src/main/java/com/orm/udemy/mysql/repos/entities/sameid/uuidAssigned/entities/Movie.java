package com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters.StringToUUID;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters.UUIDToString;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name="movie_uuid_assigned")
@Table(name="movie_uuid_assigned")
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @JsonSerialize(converter = UUIDToString.class)
    @JsonDeserialize(converter = StringToUUID.class)
    private UUID id;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private MovieDetails movieDetails;

    private String title;

    private String shortDescription;

}
