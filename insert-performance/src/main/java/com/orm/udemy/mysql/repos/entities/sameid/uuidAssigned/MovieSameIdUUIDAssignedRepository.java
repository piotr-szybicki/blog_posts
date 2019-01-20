package com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned;

import com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.entities.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieSameIdUUIDAssignedRepository extends CrudRepository<Movie, UUID> {
}
