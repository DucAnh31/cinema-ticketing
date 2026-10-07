package com.ducanh.cinematicketing.controller;

import com.ducanh.cinematicketing.common.response.ApiResponse;
import com.ducanh.cinematicketing.common.response.PageResponse;
import com.ducanh.cinematicketing.dto.request.CinemaFilterRequest;
import com.ducanh.cinematicketing.dto.response.CinemaResponse;
import com.ducanh.cinematicketing.dto.response.MovieResponse;
import com.ducanh.cinematicketing.dto.response.ScreeningRoomResponse;
import com.ducanh.cinematicketing.service.CinemaService;
import com.ducanh.cinematicketing.service.MovieService;
import com.ducanh.cinematicketing.service.ScreeningRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cinemas")
public class CinemaController {
    private final CinemaService cinemaService;
    private final ScreeningRoomService screeningRoomService;
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CinemaResponse>>> getCinemas(
            @ModelAttribute CinemaFilterRequest filterRequest,
            Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        PageResponse.from(cinemaService.getCinemas(filterRequest, pageable))));

    }

    @GetMapping("/{cinemaId}/rooms")
    public ResponseEntity<ApiResponse<PageResponse<ScreeningRoomResponse>>> getRooms(
            @PathVariable Long cinemaId,
            Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse
                        .success(PageResponse.from(screeningRoomService.getRooms(cinemaId, pageable))));

    }

    @GetMapping("/{cinemaId}/movies")
    public ResponseEntity<ApiResponse<PageResponse<MovieResponse>>> getAvailableMovie(
            @PathVariable Long cinemaId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(name = "date", required = false) LocalDate date,
            @PageableDefault(size = 10, sort = "title") Pageable pageable)
    {


        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(PageResponse
                        .from(movieService
                                .getAvailableMovie(
                                        cinemaId,
                                        date,
                                        pageable))));

    }

}
