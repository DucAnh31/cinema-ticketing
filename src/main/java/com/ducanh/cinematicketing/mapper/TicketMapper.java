package com.ducanh.cinematicketing.mapper;

import com.ducanh.cinematicketing.dto.response.TicketResponse;
import com.ducanh.cinematicketing.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "position", source = "seat.position")
    public TicketResponse toTicketResponse(Ticket ticket);

    public List<TicketResponse> toListTicketResponse(List<Ticket> tickets);
}
