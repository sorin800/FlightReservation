package com.tsv.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender sender;

	public void sendMail(String toAddress, String filePath) {
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true); // true spune ca mailul va avea si un attachment
			messageHelper.setTo(toAddress);
			messageHelper.setSubject("Itinerary for your Flight");
			messageHelper.setText("You have your flight details attached!");
			messageHelper.addAttachment("FlightDetails", new File(filePath));
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} 

	}

}
