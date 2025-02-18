package com.example.Home.service;


import com.example.Home.dto.ReviewDTO;
import com.example.Home.entity.Review;
import com.example.Home.repository.ReviewRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ReviewDTO saveReview(ReviewDTO reviewDTO){
        reviewRepo.save(modelMapper.map(reviewDTO, Review.class));
        return reviewDTO;
    }

    public List<ReviewDTO> getAllReviews(){
        List<Review>reviewList=reviewRepo.findAll();
        return modelMapper.map(reviewList,new TypeToken<List<ReviewDTO>>(){}.getType());
    }

    public boolean deleteReview(ReviewDTO reviewDTO){
        reviewRepo.delete(modelMapper.map(reviewDTO, Review.class));
        return true;
    }

    public ReviewDTO getReviewById(Long revId) {
        Review review = reviewRepo.findById(revId).orElse(null);
        return modelMapper.map(review, ReviewDTO.class);
    }
}
