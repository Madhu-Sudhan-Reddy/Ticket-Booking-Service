package com.moneyRelated.payment_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticketBooking.ticket_booking_service.entity.Ticket;

//import com.ticketBooking.ticket_booking_service.entity.Ticket;

@RestController
public class PaymentController {
	@PostMapping("/pay")
	public Ticket doPayment(@RequestBody Ticket tick) {
		tick.setStatus("Booked");
		return tick;
	}

}
