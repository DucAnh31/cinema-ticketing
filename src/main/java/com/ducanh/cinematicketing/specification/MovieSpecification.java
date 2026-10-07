//package com.ducanh.cinematicketing.specification;
//
//import com.ducanh.cinematicketing.common.util.SpecificationUtils;
//import com.ducanh.cinematicketing.dto.request.CinemaFilterRequest;
//import com.ducanh.cinematicketing.dto.request.MovieFilterRequest;
//import com.ducanh.cinematicketing.entity.Cinema;
//import com.ducanh.cinematicketing.entity.Movie;
//import org.springframework.data.jpa.domain.Specification;
//
//public class MovieSpecification {
//
//
//    public static Specification<Movie> buildMovieSpecification(MovieFilterRequest filterRequest)
//    {
//        return hasTitle(filterRequest.getTitle())
//                .and(hasAgeRating(filterRequest.getAgeRating()))
//                .and(hasGenre(filterRequest.getGenre()));
//    }
//
//    public static Specification<Movie> hasTitle(String title) {
//        return (root, query, criteriaBuilder) ->
//        {
//            if(title == null || title.isBlank())
//                return null;
//            else
//                return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
//                        SpecificationUtils.toPattern(title), '!');
//        };
//    }
//
//    public static Specification<Movie> hasGenre(String genre) {
//        return (root, query, criteriaBuilder) ->
//        {
//            if(genre == null || genre.isBlank())
//                return null;
//            else
//                return criteriaBuilder.like(criteriaBuilder.lower(root.get("genre")),
//                        SpecificationUtils.toPattern(genre), '!');
//        };
//    }
//
//    public static Specification<Movie> hasAgeRating(String ageRating)
//    {
//        return (root, query, criteriaBuilder) ->
//        {
//            if(ageRating == null || ageRating.isBlank())
//                return criteriaBuilder.conjunction();
//            else
//                return criteriaBuilder.like(criteriaBuilder.lower(root.get("ageRating")),
//                        SpecificationUtils.toPattern(ageRating), '!');
//        };
//    }
//
//
//}
