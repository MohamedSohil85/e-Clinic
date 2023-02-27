package com.example.clinic.controllers;

import com.example.clinic.dto.GetReviewsDTO;
import com.example.clinic.dto.ReviewsDTO;
import com.example.clinic.services.ReviewsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewsResource {

    private final ReviewsService reviewsService;

    public ReviewsResource(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping(value = "/review",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReviewsDTO postNewReview(@RequestBody ReviewsDTO reviewsDTO){
        return reviewsService.addReviews(reviewsDTO);
    }
    @GetMapping(value = "/reviews")
    public List<GetReviewsDTO>loadAllReviews(){
        return reviewsService.showAllReviews();
    }

}
