package com.tsv.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsv.flightreservation.dto.ReservationUpdate;
import com.tsv.flightreservation.entities.Reservation;
import com.tsv.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		return reservationRepository.findById(id).get();
	}

	// RequestBody - la runtime ReservationUpdate e format folosind json
	// practic valuarea e primita ca json dupa care cand se face request-ul
	// e deserializat obiectul in ReservationUpdate si returnata rezervarea
	// actualizata
	@RequestMapping(value = "/reservations", method = RequestMethod.POST)
	public Reservation updateReservation(@RequestBody ReservationUpdate request) {
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		Reservation updatedReservation = reservationRepository.save(reservation);
		return updatedReservation;
	}
}
