package com.ducanh.cinematicketing.repository;

import com.ducanh.cinematicketing.entity.Seat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @EntityGraph(attributePaths = {"seatType"})
    public List<Seat> findAllByRoomIdOrderByPositionAsc(Long roomId);

    @EntityGraph(attributePaths = {"seatType"})
    @Override
    List<Seat> findAllById(Iterable<Long> ids);
}
