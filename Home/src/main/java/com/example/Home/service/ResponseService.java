package com.example.Home.service;


import com.example.Home.dto.ResponseNDTO;
import com.example.Home.entity.Response;
import com.example.Home.entity.Review;
import com.example.Home.repository.ResponseRepo;
import com.example.Home.repository.ReviewRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResponseService {

    @Autowired
    private ResponseRepo responseRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Create a response to a review
    public ResponseNDTO saveResponse(Long revid, ResponseNDTO responseNDTO) {
        Review review = reviewRepo.findById(revid).orElse(null);
        if (review != null) {
            Response response = modelMapper.map(responseNDTO, Response.class);
            response.setReview(review);  // Link the response to the review
            responseRepo.save(response);
            return modelMapper.map(response, ResponseNDTO.class);
        } else {
            // If the review is not found, throw an exception with a clearer message
            throw new RuntimeException("Review not found with id: " + revid);
        }
    }

    public List<ResponseNDTO> getAllResponses(){
        List<Response>responseList=responseRepo.findAll();
        return modelMapper.map(responseList,new TypeToken<List<ResponseNDTO>>(){}.getType());
    }

    public ResponseNDTO updateResponse(ResponseNDTO responseNDTO){
        responseRepo.save(modelMapper.map(responseNDTO, Response.class));
        return responseNDTO;
    }

    public boolean deleteResponse(ResponseNDTO responseNDTO){
        responseRepo.delete(modelMapper.map(responseNDTO, Response.class));
        return true;
    }

    public ResponseNDTO getResponseById(Long resId) {
        Response response = responseRepo.findById(resId).orElse(null);
        return modelMapper.map(response, ResponseNDTO.class);
    }

    // In ResponseService.java

    public List<ResponseNDTO> getResponsesByReviewId(Long revid) {
        Review review = reviewRepo.findById(revid).orElse(null);
        if (review != null) {
            List<Response> responses = responseRepo.findByReview(review); // Assuming findByReview exists in ResponseRepo
            return modelMapper.map(responses, new TypeToken<List<ResponseNDTO>>(){}.getType());
        } else {
            throw new RuntimeException("Review not found with id: " + revid);
        }
    }

}
