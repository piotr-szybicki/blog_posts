package com.orm.udemy.mysql.repos.entities.sameid.sequence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSameSequenceRepository extends CrudRepository<Movie, Long> {}
