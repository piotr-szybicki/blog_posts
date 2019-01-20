package com.orm.udemy.mysql.repos.entities.sameid.uuid;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name="movie_uuid_generated")
@Table(name="movie_uuid_generated")
@NoArgsConstructor
@Data
public class Movie {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID id;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private MovieDetails movieDetails;

    private String title;

    private String shortDescription;

}
