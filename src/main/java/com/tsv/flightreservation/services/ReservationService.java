package com.tsv.flightreservation.services;

import com.tsv.flightreservation.dto.ReservationRequest;
import com.tsv.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
