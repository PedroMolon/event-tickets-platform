package com.pedromolon.eventticketsplatform.service;

import com.pedromolon.eventticketsplatform.exception.BusinessException;
import com.pedromolon.eventticketsplatform.exception.ResourceNotFoundException;
import com.pedromolon.eventticketsplatform.model.Customer;
import com.pedromolon.eventticketsplatform.model.Event;
import com.pedromolon.eventticketsplatform.model.Ticket;
import com.pedromolon.eventticketsplatform.model.TicketStatus;
import com.pedromolon.eventticketsplatform.repository.CustomerRepository;
import com.pedromolon.eventticketsplatform.repository.EventRepository;
import com.pedromolon.eventticketsplatform.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final CustomerRepository customerRepository;

    public TicketService(TicketRepository ticketRepository, EventRepository eventRepository, CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void createTicket(Long customerId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        if (event.getAvailableTickets() <= 0) {
            throw new BusinessException("No tickets available");
        }

        event.setAvailableTickets(event.getAvailableTickets() - 1);
        eventRepository.save(event);

        Ticket ticket = new Ticket();
        ticket.setCode(UUID.randomUUID().toString());
        ticket.setStatus(TicketStatus.RESERVED);
        ticket.setPurchaseDate(LocalDateTime.now());
        ticket.setEvent(event);
        ticket.setCustomer(customer);

        ticketRepository.save(ticket);
    }

}
