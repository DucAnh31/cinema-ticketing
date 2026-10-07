package com.ducanh.cinematicketing.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Uncategorized error"),
    SHOWTIME_NOT_FOUND(HttpStatus.NOT_FOUND, "Showtime not found"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Invalid data"),
    SEAT_NOT_IN_SHOWTIME_ROOM(HttpStatus.BAD_REQUEST, "Seat not in showtime room"),
    SEAT_NOT_FOUND(HttpStatus.BAD_REQUEST, "Seat not found"),
    SEAT_BOOKED(HttpStatus.BAD_REQUEST, "Seat has been booked"),
    TICKET_EXISTED(HttpStatus.BAD_REQUEST, "Ticket existed"),
    INVOICE_NOT_FOUND(HttpStatus.BAD_REQUEST, "Invoice not found");
    private final HttpStatus status;
    private final String defaultMessage;
}
