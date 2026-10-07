package com.ducanh.cinematicketing.dto.response;

import com.ducanh.cinematicketing.common.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {
    private Long id;
    private String position;
    private String seatTypeName;
    private BigDecimal price;
    private SeatStatus status;
}
