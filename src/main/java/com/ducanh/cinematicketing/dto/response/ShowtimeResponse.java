package com.ducanh.cinematicketing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeResponse {
    private Long id;
    private String movieTitle;
    private Long movieId;
    private String roomName;
    private Long roomId;
    private Instant startTime;
    private Instant endTime;
    private BigDecimal basePrice;
}
