package com.tsv.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsv.flightreservation.dto.ReservationRequest;
import com.tsv.flightreservation.entities.Flight;
import com.tsv.flightreservation.entities.Passenger;
import com.tsv.flightreservation.entities.Reservation;
import com.tsv.flightreservation.repos.FlightRepository;
import com.tsv.flightreservation.repos.PassengerRepository;
import com.tsv.flightreservation.repos.ReservationRepository;
import com.tsv.flightreservation.util.EmailUtil;
import com.tsv.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PDFGenerator pdfGenerator;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	public Reservation bookFlight(ReservationRequest request) {

		// Make Payment

		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());

		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = "./" + savedReservation.getId() + ".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		emailUtil.sendMail(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
