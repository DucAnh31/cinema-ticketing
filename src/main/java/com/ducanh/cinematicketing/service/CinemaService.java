package com.ducanh.cinematicketing.service;

import com.ducanh.cinematicketing.common.response.PageResponse;
import com.ducanh.cinematicketing.common.util.PageableUtils;
import com.ducanh.cinematicketing.dto.request.CinemaFilterRequest;
import com.ducanh.cinematicketing.dto.response.CinemaResponse;
import com.ducanh.cinematicketing.entity.Cinema;
import com.ducanh.cinematicketing.mapper.CinemaMapper;
import com.ducanh.cinematicketing.repository.CinemaRepository;
import com.ducanh.cinematicketing.specification.CinemaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    private final Set<String> CINEMA_SORT_FIELDS =
            Set.of("name");
    private final Sort CINEMA_DEFAULT_SORT =
            Sort.by(Sort.Direction.ASC, "name");
    private final CinemaMapper cinemaMapper;

    public Page<CinemaResponse> getCinemas(CinemaFilterRequest filterRequest, Pageable pageable)
    {
        pageable = PageableUtils.validateAndApplyDefaultSort(pageable, CINEMA_SORT_FIELDS, CINEMA_DEFAULT_SORT);
        Specification<Cinema> specification = CinemaSpecification.buildCinemaSpecification(filterRequest);

        return cinemaRepository
                .findAll(specification, pageable)
                .map(cinema -> cinemaMapper.toCinemaResponse(cinema));


    }


}
