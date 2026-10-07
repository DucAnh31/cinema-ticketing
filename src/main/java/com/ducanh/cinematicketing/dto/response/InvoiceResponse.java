package com.ducanh.cinematicketing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceResponse {
    private Long invoiceId;
    private Instant issueAt;

    private String cinemaName;
    private String roomName;
    private String movieTitle;
    private Instant startTime;

    private List<TicketResponse> tickets;
    private BigDecimal totalAmount;
}
