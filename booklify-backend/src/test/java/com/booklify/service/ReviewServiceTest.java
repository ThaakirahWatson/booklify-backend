// Thaakirah Watson, 230037550
package com.booklify.service;

import com.booklify.domain.Book;
import com.booklify.domain.Review;
import com.booklify.domain.User;
import com.booklify.factory.ReviewFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    private Review review;
    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
//        user = new User.Builder()
//                .setUserId(1L)
//                .setUserName("testUser")
//                .setUserEmail("test@example.com")
//                .setUserPassword("password123")
//                .setUserDateJoined(LocalDate.now())
//                .build();
//
//        book = new Book.Builder()
//                .setBookID(1L)
//                .setTitle("JUnit Book")
//                .setAuthor("Test Author")
//                .setCondition("New")
//                .setPrice(150.0)
//                .setDescription("Book for testing purposes")
//                .setUploadDate(LocalDate.now())
//                .build();
//
//        review = ReviewFactory.createReview(5, "Great book!", LocalDate.now(), user, book);
    }

    @Test
    @Order(1)
    void create() {
        Review created = reviewService.create(review);
        assertNotNull(created);
        assertEquals(5, created.getReviewRating());
        assertEquals("Great book!", created.getReviewComment());
        assertNotNull(created.getUser());
        assertNotNull(created.getBook());
    }

    @Test
    @Order(2)
    void read() {
        Review created = reviewService.create(review);
        Review found = reviewService.read(created.getReviewId()).orElse(null);
        assertNotNull(found);
        assertEquals(created.getReviewId(), found.getReviewId());
    }

    @Test
    @Order(3)
    void update() {
        Review created = reviewService.create(review);

        Review updated = new Review.Builder()
                .copy(created)
                .setReviewRating(4)
                .setReviewComment("Good but not the best")
                .build();

        Review result = reviewService.update(updated);
        assertNotNull(result);
        assertEquals(4, result.getReviewRating());
        assertEquals("Good but not the best", result.getReviewComment());
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
        assertFalse(reviews.isEmpty());
    }
}
