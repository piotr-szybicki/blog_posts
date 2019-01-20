package com.orm.udemy.mysql.repos.entities.sameid.uuid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "actor_uuid_generated")
@Table(name = "actor_uuid_generated")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Actor {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID id;

    private String firstName;

    private Integer age;

    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_details_id")
    private MovieDetails movieDetails;
}
