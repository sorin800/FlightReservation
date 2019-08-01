package com.tsv.flightreservation.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tsv.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to,
			@Param("dateOfDeparture") LocalDate departureDate);
	
	@Query("from Flight group by departureCity")
	  List<Flight> getDepartureCities();
	
	@Query("from Flight")
	  List<Flight> getFlights();

}
