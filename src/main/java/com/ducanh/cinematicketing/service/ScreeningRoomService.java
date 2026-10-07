package com.ducanh.cinematicketing.service;

import com.ducanh.cinematicketing.common.util.PageableUtils;
import com.ducanh.cinematicketing.dto.response.ScreeningRoomResponse;
import com.ducanh.cinematicketing.mapper.ScreeningRoomMapper;
import com.ducanh.cinematicketing.repository.ScreeningRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScreeningRoomService {
    private final ScreeningRoomRepository screeningRoomRepository;
    private final ScreeningRoomMapper screeningRoomMapper;
    private final Set<String> SCREENING_ROOM_SORT_FIELDS = Set.of("name");
    private final Sort SCREENING_ROOM_DEFAULT_SORT =
            Sort.by(Sort.Direction.DESC, "name");

    public Page<ScreeningRoomResponse> getRooms(Long cinemaId, Pageable pageable)
    {
        pageable = PageableUtils.validateAndApplyDefaultSort
                (pageable, SCREENING_ROOM_SORT_FIELDS, SCREENING_ROOM_DEFAULT_SORT);
        return screeningRoomRepository
                .findAllByCinemaId(cinemaId, pageable)
                .map(screeningRoom -> screeningRoomMapper
                        .toScreeningRoomResponse(screeningRoom));

    }
}
