package com.example.Home.repository;

import com.example.Home.entity.Response;
import com.example.Home.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepo extends JpaRepository<Response, Long> {
    // Custom query to fetch responses for a specific review
    List<Response> findByReview(Review review);
}
