package com.orm.udemy.mysql.repos.entities.sameid.cachedSequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class MovieSameIdFromCachedSequenceService {

    @Autowired
    private MovieSameIdFromCachedSequenceRepository movieRepository;

    @PostMapping("/sameIdFromCachedSequence")
    public Long creteAll(@RequestBody Movie movie){
        movie.getMovieDetails().setMovie(movie);
        MovieDetails md = movie.getMovieDetails();
        for (Actor s : md.getActors()){
            s.setMovieDetails(md);
        }
        Movie save = movieRepository.save(movie);
        return save.getId();
    }
}
