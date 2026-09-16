package com.pedromolon.eventticketsplatform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_events")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(length = 150, nullable = false)
    private String location;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false, name = "available_tickets")
    private int availableTickets;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}
