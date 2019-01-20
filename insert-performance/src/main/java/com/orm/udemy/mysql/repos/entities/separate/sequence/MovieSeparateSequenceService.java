package com.orm.udemy.mysql.repos.entities.separate.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class MovieSeparateSequenceService {

    @Autowired
    private MovieSeparateSequenceRepository movieRepo;

    @PostMapping("/separateFromSequence")
    public Long saveAll(@RequestBody Movie movie){
        movie.getMovieDetails().setMovie(movie);
        Movie save = movieRepo.save(movie);
        return save.getId();
    }
}
