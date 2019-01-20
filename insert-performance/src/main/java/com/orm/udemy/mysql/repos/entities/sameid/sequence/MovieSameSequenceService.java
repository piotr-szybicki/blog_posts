package com.orm.udemy.mysql.repos.entities.sameid.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class MovieSameSequenceService {

    @Autowired
    private MovieSameSequenceRepository movieRepo;

    @PostMapping("/sameFromSequence")
    public String saveAll(@RequestBody Movie movie){
        movie.getMovieDetails().setMovie(movie);
        movieRepo.save(movie);
        return "SUCESFUL";
    }
}
