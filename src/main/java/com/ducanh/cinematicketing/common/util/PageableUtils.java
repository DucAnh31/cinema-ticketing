package com.ducanh.cinematicketing.common.util;

import com.ducanh.cinematicketing.exception.ApiException;
import com.ducanh.cinematicketing.exception.ErrorCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

public final class PageableUtils {
//    public static void validateSortFields(Pageable pageable, Set<String> allowedFields)
//    {
//        for(Sort.Order order : pageable.getSort())
//        {
//            if(!allowedFields.contains(order.getProperty()))
//                throw new ApiException(ErrorCode.VALIDATION_ERROR);
//        }
//    }

    public static Pageable validateAndApplyDefaultSort(
            Pageable pageable,
            Set<String> allowedFields,
            Sort defaultSort
    ) {
        Sort sort = pageable.getSort();

        if (sort.isUnsorted()) {
            sort = defaultSort;
        }

        for(Sort.Order order : sort)
        {
            if(!allowedFields.contains(order.getProperty()))
                throw new ApiException(ErrorCode.VALIDATION_ERROR);
        }



        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
    }
}
