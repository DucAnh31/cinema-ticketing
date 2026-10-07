package com.ducanh.cinematicketing.repository;

import com.ducanh.cinematicketing.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Query("""
            select m
            from Movie m
            where m.id in 
                (
                    select s.movie.id
                    from Showtime s
                    where s.room.cinema.id = :cinemaId and
                          s.startTime >= :from and
                          s.startTime < :to and
                          s.startTime > :currentTime                              
                )                                      
            """)
    public Page<Movie> findAvailableMovies(
            Pageable pageable,
            @Param("currentTime")Instant currentTime,
            @Param("cinemaId") Long cinemaId,
            @Param("from") Instant from,
            @Param("to") Instant to);
}
