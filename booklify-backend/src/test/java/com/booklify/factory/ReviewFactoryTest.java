// Thaakirah Watson, 230037550
package com.booklify.factory;

import com.booklify.domain.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

    private ReviewFactory reviewFactory;

    @BeforeEach
    void setUp() {
        reviewFactory = ReviewFactory.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(reviewFactory, "Factory instance should not be null");
        ReviewFactory anotherInstance = ReviewFactory.getInstance();
        assertSame(reviewFactory, anotherInstance, "Factory should be a singleton");
    }

    @Test
    void createReview() {
        Review review = reviewFactory.createReview(5, "Excellent book!");
        assertNotNull(review, "Review should not be null");
        assertEquals(5, review.getReviewRating(), "Rating should match");
        assertEquals("Excellent book!", review.getReviewComment(), "Comment should match");
        assertNotNull(review.getReviewDate(), "Review date should be set");
    }
}
