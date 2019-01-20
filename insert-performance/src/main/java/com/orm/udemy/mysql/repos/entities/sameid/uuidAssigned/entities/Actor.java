package com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters.StringToUUID;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters.UUIDToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "actor_uuid_assigned")
@Table(name = "actor_uuid_assigned")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Actor {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @JsonSerialize(converter = UUIDToString.class)
    @JsonDeserialize(converter = StringToUUID.class)
    private UUID id;

    private String firstName;

    private Integer age;

    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_details_id")
    private MovieDetails movieDetails;
}
