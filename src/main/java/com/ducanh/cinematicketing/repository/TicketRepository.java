package com.ducanh.cinematicketing.repository;

import com.ducanh.cinematicketing.entity.Seat;
import com.ducanh.cinematicketing.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("""
           select t.seat.id 
           from Ticket t
           where t.showtime.id = :showtimeId 
           """)
    public Set<Long> findBookedSeats(@Param("showtimeId") Long showtimeId);

    public List<Ticket> findAllByInvoiceId(Long invoiceId);
}
