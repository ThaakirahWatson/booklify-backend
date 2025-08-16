// Thaakirah Watson, 230037550
package com.booklify.service;

import com.booklify.domain.Review;
import com.booklify.factory.ReviewFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    private Review review;

    @BeforeEach
    void setUp() {
        review = ReviewFactory.getInstance().createReview(5, "Great book!");
    }

    @Test
    @Order(1)
    void create() {
        Review created = reviewService.create(review);
        assertNotNull(created);
        assertEquals(5, created.getReviewRating());
    }

    @Test
    @Order(2)
    void read() {
        Review created = reviewService.create(review);
        Review found = reviewService.read(created.getReviewId());
        assertNotNull(found);
    }

    @Test
    @Order(3)
    void update() {
        Review created = reviewService.create(review);
        Review updated = new Review.Builder()
                .setReviewId(created.getReviewId())
                .setReviewRating(4)
                .setReviewComment("Good but not the best")
                .setReviewDate(created.getReviewDate())
                .build();

        Review result = reviewService.update(updated);
        assertEquals(4, result.getReviewRating());
    }

    @Test
    @Order(4)
    void delete() {
        Review created = reviewService.create(review);
        boolean deleted = reviewService.delete(created.getReviewId());
        assertTrue(deleted);
    }

    @Test
    @Order(5)
    void getAll() {
        List<Review> reviews = reviewService.getAll();
        assertNotNull(reviews);
    }
}
