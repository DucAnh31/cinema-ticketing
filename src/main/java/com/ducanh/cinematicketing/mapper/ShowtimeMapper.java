package com.ducanh.cinematicketing.mapper;

import com.ducanh.cinematicketing.dto.response.CinemaResponse;
import com.ducanh.cinematicketing.dto.response.ShowtimeResponse;
import com.ducanh.cinematicketing.entity.Cinema;
import com.ducanh.cinematicketing.entity.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {
    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "movieTitle", source = "movie.title")
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "roomName", source = "room.name")
    public ShowtimeResponse toShowtimeResponse(Showtime showtime);
}
