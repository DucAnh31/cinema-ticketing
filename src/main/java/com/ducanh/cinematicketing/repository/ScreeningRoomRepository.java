package com.ducanh.cinematicketing.repository;

import com.ducanh.cinematicketing.entity.ScreeningRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, Long> {
    public Page<ScreeningRoom> findAllByCinemaId(Long cinemaId, Pageable pageable);
}
