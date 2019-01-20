package com.orm.udemy.mysql.repos.entities.sameid.cachedSequence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSameIdFromCachedSequenceRepository extends CrudRepository<Movie, Long> {
}
