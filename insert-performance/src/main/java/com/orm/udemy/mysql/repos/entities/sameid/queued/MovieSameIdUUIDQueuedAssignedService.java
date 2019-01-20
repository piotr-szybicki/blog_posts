package com.orm.udemy.mysql.repos.entities.sameid.queued;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Transactional
public class MovieSameIdUUIDQueuedAssignedService {

    @Autowired
    private CacheManager<Movie> cm;

    @PostMapping("/sameIdQueuedUUIDAssigned")
    public String createAll(@RequestBody Movie movie, @RequestParam("id") String uuid){
        UUID id = UUID.fromString(uuid);
        movie.setId(id);
        movie.getMovieDetails().setId(id);
        cm.save(movie);
        return "SUCCESS";
    }

    @GetMapping("/sameIdQueuedUUIDAssigned")
    public List<Movie> getAll(){
        return cm.getAll();
    }
}
