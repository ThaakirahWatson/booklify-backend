// Thaakirah Watson, 230037550
package com.booklify.service;

import com.booklify.domain.Review;
import com.booklify.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review create(Review review) {
        if (review.getUser() == null || review.getBook() == null) {
            throw new IllegalArgumentException("Review must be linked to both a User and a Book.");
        }
        return reviewRepository.save(review);
    }

    public Optional<Review> read(Long id) {
        return reviewRepository.findById(id);
    }

    public Review update(Review review) {
        if (review.getReviewId() == null || !reviewRepository.existsById(review.getReviewId())) {
            return null; // or throw an exception
        }
        return reviewRepository.save(review);
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

    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookBook(bookId);
    }

    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserUser(userId);
    }
}
