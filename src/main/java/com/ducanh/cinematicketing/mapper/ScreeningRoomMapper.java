package com.ducanh.cinematicketing.mapper;

import com.ducanh.cinematicketing.dto.response.ScreeningRoomResponse;
import com.ducanh.cinematicketing.entity.ScreeningRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScreeningRoomMapper {
    public ScreeningRoomResponse toScreeningRoomResponse(ScreeningRoom room);
}
