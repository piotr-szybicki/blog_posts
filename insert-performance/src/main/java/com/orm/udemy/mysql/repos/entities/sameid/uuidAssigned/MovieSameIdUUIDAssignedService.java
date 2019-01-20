package com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned;

import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.entities.Actor;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.entities.Movie;
import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.entities.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
@Transactional
public class MovieSameIdUUIDAssignedService {

    @Autowired
    private MovieSameIdUUIDAssignedRepository repository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping("/sameIdUUIDAssigned")
    public UUID createAll(@RequestBody Movie movie){
        UUID movie_id = randomUUID();
        movie.setId(movie_id);
        movie.getMovieDetails().setMovie(movie);
        movie.getMovieDetails().setId(movie.getId());
        MovieDetails md = movie.getMovieDetails();
        for (Actor s : md.getActors()){
            s.setMovieDetails(md);
            s.setId(randomUUID());
        }
        repository.save(movie);
        return movie.getId();
    }

}
