package com.ducanh.cinematicketing.controller;

import com.ducanh.cinematicketing.common.response.ApiResponse;
import com.ducanh.cinematicketing.dto.request.CreateOrderRequest;
import com.ducanh.cinematicketing.dto.response.InvoiceResponse;
import com.ducanh.cinematicketing.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping()
    public ResponseEntity<ApiResponse<InvoiceResponse>> bookTickets(@Valid @RequestBody CreateOrderRequest request)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(orderService.createOrder(request)));
    }
}
