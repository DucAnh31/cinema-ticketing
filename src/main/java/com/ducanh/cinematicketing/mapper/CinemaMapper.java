package com.ducanh.cinematicketing.mapper;

import com.ducanh.cinematicketing.dto.response.CinemaResponse;
import com.ducanh.cinematicketing.entity.Cinema;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    public CinemaResponse toCinemaResponse(Cinema cinema);
}
