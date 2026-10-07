package com.ducanh.cinematicketing.mapper;

import com.ducanh.cinematicketing.dto.response.CinemaResponse;
import com.ducanh.cinematicketing.dto.response.MovieResponse;
import com.ducanh.cinematicketing.entity.Cinema;
import com.ducanh.cinematicketing.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    public MovieResponse toMovieResponse(Movie movie);
    public List<MovieResponse> toListMovieReponse(List<Movie> movies);
}
