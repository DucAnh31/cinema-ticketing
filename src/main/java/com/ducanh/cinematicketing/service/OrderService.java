package com.ducanh.cinematicketing.service;

import com.ducanh.cinematicketing.dto.request.CreateOrderRequest;
import com.ducanh.cinematicketing.dto.response.InvoiceResponse;
import com.ducanh.cinematicketing.dto.response.TicketResponse;
import com.ducanh.cinematicketing.entity.Invoice;
import com.ducanh.cinematicketing.entity.Seat;
import com.ducanh.cinematicketing.entity.Showtime;
import com.ducanh.cinematicketing.entity.Ticket;
import com.ducanh.cinematicketing.exception.ApiException;
import com.ducanh.cinematicketing.exception.ErrorCode;
import com.ducanh.cinematicketing.mapper.InvoiceMapper;
import com.ducanh.cinematicketing.mapper.TicketMapper;
import com.ducanh.cinematicketing.repository.InvoiceRepository;
import com.ducanh.cinematicketing.repository.SeatRepository;
import com.ducanh.cinematicketing.repository.ShowtimeRepository;
import com.ducanh.cinematicketing.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final TicketRepository ticketRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatRepository seatRepository;
    private final InvoiceRepository invoiceRepository;
    private final TicketMapper ticketMapper;
    private final InvoiceMapper invoiceMapper;


    @Transactional
    public InvoiceResponse createOrder(CreateOrderRequest request)
    {
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Set<Long> bookedSeatIds = ticketRepository.findBookedSeats(showtime.getId());
        for(Long id : bookedSeatIds)
        {
            if(request.getSeatIds().contains(id))
                throw new ApiException(ErrorCode.SEAT_BOOKED);
        }

        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());
        if (seats.size() != request.getSeatIds().size()) {
            throw new ApiException(ErrorCode.SEAT_NOT_FOUND);
        }

        for(Seat seat : seats)
        {
            if(!seat.getRoom().getId().equals(showtime.getRoom().getId()))
                throw new ApiException(ErrorCode.SEAT_NOT_IN_SHOWTIME_ROOM);
        }



        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Ticket> tickets = new ArrayList<>(seats.size());

        for(Seat seat : seats)
        {
             BigDecimal finalPrice = showtime.getBasePrice().add(seat.getSeatType().getSurcharge());
             Ticket ticket = Ticket.builder()
                    .showtime(showtime)
                    .seat(seat)
                    .finalPrice(finalPrice)
                    .build();
             tickets.add(ticket);
             totalAmount = totalAmount.add(finalPrice);
        }

        Invoice invoice = Invoice.builder()
                .issueAt(Instant.now())
                .totalAmount(totalAmount)
                .build();

        invoiceRepository.save(invoice);


        for(Ticket ticket : tickets)
            ticket.setInvoice(invoice);

        try {
            List<TicketResponse> ticketResponses = ticketMapper
                    .toListTicketResponse(ticketRepository.saveAllAndFlush(tickets));

            return invoiceMapper.toInvoiceResponse(invoice, showtime, ticketResponses);


        }
        catch (DataIntegrityViolationException e)
        {
            throw new ApiException(ErrorCode.TICKET_EXISTED);
        }

    }
}
