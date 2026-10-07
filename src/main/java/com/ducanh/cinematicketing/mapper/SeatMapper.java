package com.ducanh.cinematicketing.mapper;

import com.ducanh.cinematicketing.dto.response.CinemaResponse;
import com.ducanh.cinematicketing.dto.response.SeatResponse;
import com.ducanh.cinematicketing.entity.Cinema;
import com.ducanh.cinematicketing.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(target = "seatTypeName", source = "seatType.name")
    public SeatResponse toSeatResponse(Seat seat);
}
