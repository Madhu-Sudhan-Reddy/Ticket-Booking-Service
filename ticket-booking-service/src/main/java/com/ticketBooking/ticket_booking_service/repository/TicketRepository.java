package com.ticketBooking.ticket_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketBooking.ticket_booking_service.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long>{

}
