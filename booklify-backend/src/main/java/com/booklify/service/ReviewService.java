// Thaakirah Watson, 230037550
package com.booklify.service;

import com.booklify.domain.Review;
import com.booklify.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public Review read(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review update(Review review) {
        if (reviewRepository.existsById(review.getReviewId())) {
            return reviewRepository.save(review);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
}
