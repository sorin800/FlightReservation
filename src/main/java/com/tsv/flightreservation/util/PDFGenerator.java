package com.tsv.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tsv.flightreservation.entities.Reservation;

@Component
public class PDFGenerator {
	public void generateItinerary(Reservation reservation, String filePath) {

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			// nr de coloane din pdf
			document.add(generateTable(reservation));
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

	private PdfPTable generateTable(Reservation reservation) {
		PdfPTable table = new PdfPTable(2); // nr de coloane din pdf

		PdfPCell cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);// campul asta se intinde pe 2 coloane
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Flight Details"));
		cell.setColspan(2);// campul asta se intinde pe 2 coloane
		table.addCell(cell);

		table.addCell("Airlines ");
		table.addCell(reservation.getFlight().getOperatingAirlines());

		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDepartureCity());

		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());

		table.addCell("Flight Number");
		table.addCell(reservation.getFlight().getFlightNumber());

		table.addCell("Departure date");
		table.addCell(reservation.getFlight().getDateOfDeparture().toString());

		table.addCell("Departure time");
		table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());

		cell = new PdfPCell(new Phrase("Passenger Details"));
		cell.setColspan(2);// campul asta se intinde pe 2 coloane
		table.addCell(cell);

		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFirstName());

		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastName());

		table.addCell("Email");
		table.addCell(reservation.getPassenger().getEmail());

		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhone());

		return table;
	}
}
