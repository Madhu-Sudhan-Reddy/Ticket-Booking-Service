package com.ticketBooking.ticket_booking_service.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ticketBooking.ticket_booking_service.entity.Ticket;
import com.ticketBooking.ticket_booking_service.repository.TicketRepository;

@RestController
public class TicketController {
	@Autowired
	TicketRepository repo;
	@Autowired
	RestTemplate restTemplate;
	@Value("${payment.url}")
	String paymenturl;
	@Value("${booking.status.success}")
	String bookingStatusSuccess;
	@Value("${booking.status.failure}")
	String bookingStatusFailure;
	@PostMapping
	public String bookTicket(@RequestBody Ticket tickDetails) {
		Ticket payload=null;
		//final String baseUrl= "http://localhost:8082/pay";
		URI uri;
		try {
			uri=new URI(paymenturl);
			HttpHeaders headers=new HttpHeaders();
			headers.set("sample", "true");
			headers.set("sample1", "subscribe");
			
			HttpEntity<Ticket> request=new HttpEntity<>(tickDetails,headers);
			ResponseEntity<Ticket> result=restTemplate.postForEntity(uri, request, Ticket.class);
			payload=result.getBody();
			repo.save(payload);
			//return payload;
		}
		catch(URISyntaxException e) {
			e.printStackTrace();
		}
		if(payload.getStatus().equals("Booked"))
		return bookingStatusSuccess;
		
		return bookingStatusFailure;
		}

}
