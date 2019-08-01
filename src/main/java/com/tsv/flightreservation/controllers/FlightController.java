package com.tsv.flightreservation.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsv.flightreservation.dto.CurrencyRestImpl;
import com.tsv.flightreservation.entities.Flight;
import com.tsv.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private CurrencyRestImpl currencyRest;

	private boolean foundFlights;
	private boolean foundCurrency;
	private boolean noFlightsFound;
	
	@RequestMapping(value = "/findFlights", method = RequestMethod.POST)
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
			Model model) {

		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);

		model.addAttribute("departure", from);
		model.addAttribute("destination", to);

		if (flights.isEmpty() || flights == null) {
			noFlightsFound = true;
			model.addAttribute("noFlightsFound", noFlightsFound);
			model.addAttribute("msg", "No flights were found!");
			
		} else {
			foundCurrency = true;
			Object currency = currencyRest.getCurrencies();
			model.addAttribute("foundCurrency", foundCurrency);
			model.addAttribute("currency", currency);
			model.addAttribute("flights", flights);
			foundFlights = true;
			model.addAttribute("foundFlights", foundFlights);
		}

		return "displayFlights";
	}
	
	@RequestMapping(value = "/searchFlights", method = RequestMethod.GET)
	public String searchFlights() {
		return "findFlights";
	}
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}

}
