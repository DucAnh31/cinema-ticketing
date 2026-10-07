package com.ducanh.cinematicketing.specification;

import com.ducanh.cinematicketing.common.util.SpecificationUtils;
import com.ducanh.cinematicketing.dto.request.CinemaFilterRequest;
import com.ducanh.cinematicketing.entity.Cinema;
import org.springframework.data.jpa.domain.Specification;

public class CinemaSpecification {

    public static Specification<Cinema> buildCinemaSpecification(CinemaFilterRequest filterRequest)
    {
        return hasName(filterRequest.getName()).and(hasAddress(filterRequest.getAddress()));
    }

    public static Specification<Cinema> hasName(String name) {
        return (root, query, criteriaBuilder) ->
        {
            if(name == null || name.isBlank())
                return null;
            else
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        SpecificationUtils.toPattern(name), '!');
        };
    }

    public static Specification<Cinema> hasAddress(String address)
    {
        return (root, query, criteriaBuilder) ->
        {
            if(address == null || address.isBlank())
                return null;
            else
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("address")),
                        SpecificationUtils.toPattern(address), '!');
        };
    }



}
