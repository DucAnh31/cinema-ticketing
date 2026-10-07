package com.ducanh.cinematicketing.controller;

import com.ducanh.cinematicketing.common.response.ApiResponse;
import com.ducanh.cinematicketing.common.response.PageResponse;
import com.ducanh.cinematicketing.dto.response.SeatResponse;
import com.ducanh.cinematicketing.dto.response.ShowtimeResponse;
import com.ducanh.cinematicketing.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ShowtimeResponse>>> getShowtimes(
            @RequestParam(value = "movieId") Long movieId,
            @RequestParam(value = "roomId") Long roomId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(name = "date", required = false) LocalDate date,
            Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(PageResponse
                        .from(showtimeService
                                .getShowtimes(
                                        movieId,
                                        roomId,
                                        date,
                                        pageable))));
    }


    @GetMapping("/{showtimeId}/seats")
    public ResponseEntity<ApiResponse<List<SeatResponse>>> getSeats(@PathVariable("showtimeId") Long showtimeId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(showtimeService.getSeats(showtimeId)));
    }
}
