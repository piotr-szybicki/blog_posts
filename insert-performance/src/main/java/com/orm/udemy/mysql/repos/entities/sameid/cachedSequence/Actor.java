package com.orm.udemy.mysql.repos.entities.sameid.cachedSequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "actor_same_cached_sequence")
@Table(name = "actor_same_cached_sequence")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Actor {

    @Id
    @GenericGenerator(
            name = "actorSequenceGenerator",
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
                            value = "50"
                    )
            }
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actorSequenceGenerator"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_details_id")
    private MovieDetails movieDetails;

    private String firstName;

    private Integer age;

    private String lastName;
}
