package com.ducanh.cinematicketing.service;


import com.ducanh.cinematicketing.common.util.PageableUtils;
import com.ducanh.cinematicketing.dto.response.MovieResponse;
import com.ducanh.cinematicketing.mapper.CinemaMapper;
import com.ducanh.cinematicketing.mapper.MovieMapper;
import com.ducanh.cinematicketing.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final Set<String> MOVIE_SORT_FIELDS =
            Set.of("title", "genre");
    private final Sort MOVIE_DEFAULT_SORT =
            Sort.by(Sort.Direction.ASC, "title");
    private final CinemaMapper cinemaMapper;

    public Page<MovieResponse> getAvailableMovie(Long cinemaId, LocalDate date, Pageable pageable)
    {
        date = date != null ? date : LocalDate.now(ZoneOffset.UTC);

        Instant from = date
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();

        Instant to = date
                .plusDays(1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();
        pageable = PageableUtils.validateAndApplyDefaultSort(pageable, MOVIE_SORT_FIELDS, MOVIE_DEFAULT_SORT);


        return movieRepository.findAvailableMovies(
                pageable,
                Instant.now(),
                cinemaId,
                from,
                to).map(movie -> movieMapper.toMovieResponse(movie));
    }
}
