package com.tsv.flightreservation.controllers;

import java.util.ArrayList;
import java.util.List;

import com.tsv.flightreservation.dto.CurrencyRestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsv.flightreservation.entities.Flight;
import com.tsv.flightreservation.entities.User;
import com.tsv.flightreservation.repos.FlightRepository;
import com.tsv.flightreservation.repos.UserRepository;
import com.tsv.flightreservation.services.SecurityService;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private CurrencyRestImpl currencyRest;

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		try {
			boolean loginResponse = securityService.login(email, password);
			if (loginResponse) {
				
				List<Flight> distinctDepartureCities = flightRepository.getDepartureCities();
				model.addAttribute("departureCities", distinctDepartureCities);
				
				List<Flight> flights = flightRepository.getFlights();
				model.addAttribute("flights", flights);
				
				return "findFlights";
			} else {
				model.addAttribute("msg", "Invalid username or password.Please try again!");
			}
		} catch (Exception e) {
			model.addAttribute("msg", "Invalid username or password.Please try again!");
			e.printStackTrace();
		}
		return "login/login";
	}
	
	@RequestMapping(value = "/showLogin", method = RequestMethod.GET)
	public String getLogin(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			List<Flight> distinctDepartureCities = flightRepository.getDepartureCities();
			model.addAttribute("departureCities", distinctDepartureCities);
			
			List<Flight> flights = flightRepository.getFlights();
			model.addAttribute("flights", flights);
			return "findFlights";
		}
		
		return "login/login";
	}
}
