package com.ducanh.cinematicketing.mapper;

import com.ducanh.cinematicketing.dto.response.InvoiceResponse;
import com.ducanh.cinematicketing.dto.response.TicketResponse;
import com.ducanh.cinematicketing.entity.Invoice;
import com.ducanh.cinematicketing.entity.Showtime;
import com.ducanh.cinematicketing.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(target = "invoiceId", source = "invoice.id")
    @Mapping(target = "cinemaName", source = "showtime.room.cinema.name")
    @Mapping(target = "roomName", source = "showtime.room.name")
    @Mapping(target = "movieTitle", source = "showtime.movie.title")
    @Mapping(target = "startTime", source = "showtime.startTime")
    @Mapping(target = "tickets", source = "ticketResponses")
    public InvoiceResponse toInvoiceResponse(
            Invoice invoice,
            Showtime showtime,
            List<TicketResponse> ticketResponses);


}
