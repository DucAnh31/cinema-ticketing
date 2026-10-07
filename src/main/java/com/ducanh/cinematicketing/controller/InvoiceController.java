package com.ducanh.cinematicketing.controller;

import com.ducanh.cinematicketing.common.response.ApiResponse;
import com.ducanh.cinematicketing.common.response.PageResponse;
import com.ducanh.cinematicketing.dto.request.CinemaFilterRequest;
import com.ducanh.cinematicketing.dto.response.CinemaResponse;
import com.ducanh.cinematicketing.dto.response.InvoiceResponse;
import com.ducanh.cinematicketing.dto.response.MovieResponse;
import com.ducanh.cinematicketing.dto.response.ScreeningRoomResponse;
import com.ducanh.cinematicketing.service.CinemaService;
import com.ducanh.cinematicketing.service.InvoiceService;
import com.ducanh.cinematicketing.service.MovieService;
import com.ducanh.cinematicketing.service.ScreeningRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/{invoiceId}")
    public ResponseEntity<ApiResponse<InvoiceResponse>> getInvoice(
            @PathVariable Long invoiceId
    )
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(invoiceService.getInvoice(invoiceId)));

    }


}
