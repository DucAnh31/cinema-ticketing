package com.ducanh.cinematicketing.service;

import com.ducanh.cinematicketing.common.enums.SeatStatus;
import com.ducanh.cinematicketing.common.util.PageableUtils;
import com.ducanh.cinematicketing.dto.response.SeatResponse;
import com.ducanh.cinematicketing.dto.response.ShowtimeResponse;
import com.ducanh.cinematicketing.entity.Seat;
import com.ducanh.cinematicketing.entity.Showtime;
import com.ducanh.cinematicketing.exception.ApiException;
import com.ducanh.cinematicketing.exception.ErrorCode;
import com.ducanh.cinematicketing.mapper.SeatMapper;
import com.ducanh.cinematicketing.mapper.ShowtimeMapper;
import com.ducanh.cinematicketing.repository.SeatRepository;
import com.ducanh.cinematicketing.repository.ShowtimeRepository;
import com.ducanh.cinematicketing.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final ShowtimeMapper showtimeMapper;
    private final TicketRepository ticketRepository;
    private final SeatMapper seatMapper;
    private final SeatRepository seatRepository;
    private final Set<String> SHOWTIME_SORT_FIELDS =
            Set.of("startTime", "basePrice");
    private final Sort SHOWTIME_DEFAULT_SORT =
            Sort.by(Sort.Direction.ASC, "startTime");

    public Page<ShowtimeResponse> getShowtimes(Long movieId, Long roomId, LocalDate date, Pageable pageable) {

        pageable = PageableUtils.validateAndApplyDefaultSort(pageable, SHOWTIME_SORT_FIELDS, SHOWTIME_DEFAULT_SORT);

        date = date != null ? date : LocalDate.now(ZoneOffset.UTC);

        Instant from = date
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();

        Instant to = date
                .plusDays(1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();


        return showtimeRepository.getAvailableShowtimes(movieId, roomId, from, to, Instant.now(), pageable)
                .map(showtime -> showtimeMapper.toShowtimeResponse(showtime));

    }

    public List<SeatResponse> getSeats(Long showtimeId)
    {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ApiException(ErrorCode.SHOWTIME_NOT_FOUND));

        Set<Long> bookedSeatIds = ticketRepository.findBookedSeats(showtimeId);



        List<Seat> seats = seatRepository.findAllByRoomIdOrderByPositionAsc(showtime.getRoom().getId());

        List<SeatResponse> seatResponses = new ArrayList<>();

        for(Seat seat : seats)
        {
            SeatStatus status = bookedSeatIds.contains(seat.getId())
                    ? SeatStatus.BOOKED
                    : SeatStatus.AVAILABLE;
            BigDecimal price = seat.getSeatType().getSurcharge().add(showtime.getBasePrice());

            SeatResponse seatResponse = seatMapper.toSeatResponse(seat);
            seatResponse.setPrice(price);
            seatResponse.setStatus(status);

            seatResponses.add(seatResponse);
        }

        return seatResponses;

    }
}