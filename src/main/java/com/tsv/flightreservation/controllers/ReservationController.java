package com.tsv.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsv.flightreservation.dto.ReservationRequest;
import com.tsv.flightreservation.entities.Flight;
import com.tsv.flightreservation.entities.Reservation;
import com.tsv.flightreservation.repos.FlightRepository;
import com.tsv.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	FlightRepository flightRepository;

	@Autowired
	private ReservationService reservationService;

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, Model model) {
		System.out.println(flightId);
		Flight flight = flightRepository.findById(flightId).get();
		model.addAttribute("flight", flight);
		return "completeReservation";
	}

	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String completeReservation(@ModelAttribute("reservation") ReservationRequest request, Model model) {
		Reservation reservation = reservationService.bookFlight(request);
		model.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId());
		return "reservationConfirmation";
	}
}
