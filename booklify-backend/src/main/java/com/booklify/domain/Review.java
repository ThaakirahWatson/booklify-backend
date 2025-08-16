// Thaakirah Watson, 230037550
package com.booklify.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private int reviewRating;
    private String reviewComment;
    private LocalDate reviewDate;

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.reviewRating = builder.reviewRating;
        this.reviewComment = builder.reviewComment;
        this.reviewDate = builder.reviewDate;
    }

    public Review() {}

    public Long getReviewId() { return reviewId; }
    public int getReviewRating() { return reviewRating; }
    public String getReviewComment() { return reviewComment; }
    public LocalDate getReviewDate() { return reviewDate; }

    public static class Builder {
        private Long reviewId;
        private int reviewRating;
        private String reviewComment;
        private LocalDate reviewDate;

        public Builder setReviewId(Long reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setReviewRating(int reviewRating) {
            this.reviewRating = reviewRating;
            return this;
        }

        public Builder setReviewComment(String reviewComment) {
            this.reviewComment = reviewComment;
            return this;
        }

        public Builder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.getReviewId();
            this.reviewRating = review.getReviewRating();
            this.reviewComment = review.getReviewComment();
            this.reviewDate = review.getReviewDate();
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
