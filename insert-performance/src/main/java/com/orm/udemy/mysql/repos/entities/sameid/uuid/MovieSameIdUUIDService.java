package com.orm.udemy.mysql.repos.entities.sameid.uuid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Transactional
public class MovieSameIdUUIDService {

    @Autowired
    private MovieSameIdUUIDRepository repo;

    @PostMapping("/sameIdUUIDGenerated")
    public UUID creteAll(@RequestBody Movie movie){
        movie.getMovieDetails().setMovie(movie);
        MovieDetails md = movie.getMovieDetails();
        for (Actor s : md.getActors()){
            s.setMovieDetails(md);
        }
        Movie save = repo.save(movie);
        return save.getId();
    }
}
