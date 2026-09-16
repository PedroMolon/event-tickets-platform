package com.pedromolon.eventticketsplatform.repository;

import com.pedromolon.eventticketsplatform.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findByCustomerId(Long customerId, Pageable pageable);
}
