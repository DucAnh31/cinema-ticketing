package com.ducanh.cinematicketing.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets",
    uniqueConstraints = @UniqueConstraint(
            name = "uk_tickets_showtime_seat",
            columnNames = {"showtime_id", "seat_id"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(name = "final_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal finalPrice;
}
