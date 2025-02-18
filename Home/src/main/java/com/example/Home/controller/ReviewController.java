package com.example.Home.controller;

import com.example.Home.dto.ReviewDTO;
import com.example.Home.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/review")
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/getReviews")
    public List<ReviewDTO> getReviews(){
        return reviewService.getAllReviews();
    }

    @PostMapping("/saveReview")
    public ReviewDTO saveReview(@RequestBody ReviewDTO reviewDTO){
        return reviewService.saveReview(reviewDTO);
    }

    @DeleteMapping("/deleteReview")
    public boolean deleteReview(@RequestBody ReviewDTO reviewDTO){
        return reviewService.deleteReview(reviewDTO);
    }

    @GetMapping("/getReviewById/{revId}")
    public ReviewDTO getReviewById(@PathVariable Long revId){
        return reviewService.getReviewById(revId);
    }
}
