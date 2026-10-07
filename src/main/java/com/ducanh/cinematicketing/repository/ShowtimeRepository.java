package com.ducanh.cinematicketing.repository;

import com.ducanh.cinematicketing.entity.Showtime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long>, JpaSpecificationExecutor<Showtime> {

    @EntityGraph(attributePaths = {"movie", "room"})
    @Query("""
           select s
           from Showtime s
           where s.movie.id = :movieId and
                 s.room.id = :roomId and
                 s.startTime >= :from and
                 s.startTime < :to and
                 s.startTime > :currentTime           
           """)
    public Page<Showtime> getAvailableShowtimes(
            @Param("movieId") Long movieId,
            @Param("roomId") Long roomId,
            @Param("from") Instant from,
            @Param("to") Instant to,
            @Param("currentTime") Instant currentTime,
            Pageable pageable);
}
