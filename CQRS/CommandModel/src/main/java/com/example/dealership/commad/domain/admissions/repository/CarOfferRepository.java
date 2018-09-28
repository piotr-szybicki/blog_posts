package com.example.dealership.commad.domain.admissions.repository;

import com.example.dealership.commad.domain.admissions.repository.entites.CarOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarOfferRepository extends JpaRepository<CarOffer, UUID> { }

