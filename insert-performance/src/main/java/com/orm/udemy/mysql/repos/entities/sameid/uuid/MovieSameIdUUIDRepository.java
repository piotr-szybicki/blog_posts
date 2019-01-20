package com.orm.udemy.mysql.repos.entities.sameid.uuid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieSameIdUUIDRepository extends CrudRepository<Movie, UUID> {}
