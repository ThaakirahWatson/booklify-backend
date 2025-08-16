// Thaakirah Watson, 230037550
package com.booklify.factory;

import com.booklify.domain.Review;
import java.time.LocalDate;

public class ReviewFactory {

    private static ReviewFactory reviewFactory;

    private ReviewFactory() {}

    // Singleton instance
    public static ReviewFactory getInstance() {
        if (reviewFactory == null) {
            reviewFactory = new ReviewFactory();
        }
        return reviewFactory;
    }

    public Review createReview(int rating, String comment) {
        return new Review.Builder()
                .setReviewRating(rating)
                .setReviewComment(comment)
                .setReviewDate(LocalDate.now())
                .build();
    }
}
