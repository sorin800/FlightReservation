package com.tsv.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsv.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
