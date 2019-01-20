package com.orm.udemy.mysql.repos.entities.separate.sequence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSeparateSequenceRepository extends CrudRepository<Movie, Long> {}
