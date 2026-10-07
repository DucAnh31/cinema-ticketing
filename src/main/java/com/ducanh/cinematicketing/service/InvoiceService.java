package com.ducanh.cinematicketing.service;

import com.ducanh.cinematicketing.dto.response.InvoiceResponse;
import com.ducanh.cinematicketing.entity.Invoice;
import com.ducanh.cinematicketing.entity.Showtime;
import com.ducanh.cinematicketing.entity.Ticket;
import com.ducanh.cinematicketing.exception.ApiException;
import com.ducanh.cinematicketing.exception.ErrorCode;
import com.ducanh.cinematicketing.mapper.InvoiceMapper;
import com.ducanh.cinematicketing.mapper.TicketMapper;
import com.ducanh.cinematicketing.repository.InvoiceRepository;
import com.ducanh.cinematicketing.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final TicketRepository ticketRepository;
    private final InvoiceMapper invoiceMapper;
    private final TicketMapper ticketMapper;

    public InvoiceResponse getInvoice(Long invoiceId)
    {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ApiException(ErrorCode.INVOICE_NOT_FOUND));

        List<Ticket> tickets = ticketRepository.findAllByInvoiceId(invoiceId);

        Showtime showtime = tickets.get(0).getShowtime();

        return invoiceMapper.toInvoiceResponse(
                invoice,
                showtime,
                ticketMapper.toListTicketResponse(tickets)
        );

    }
}
