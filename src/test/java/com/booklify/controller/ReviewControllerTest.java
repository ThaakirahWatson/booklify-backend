// Thaakirah Watson, 230037550
package com.booklify.controller;

import com.booklify.domain.Book;
import com.booklify.domain.Review;
import com.booklify.domain.User;
import com.booklify.factory.ReviewFactory;
import com.booklify.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

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

//        book = new Book.Builder()
//                .setBookID(1L)
//                .setTitle("Test Book")
//                .setAuthor("Test Author")
//                .setCondition("New")
//                .setPrice(199.99)
//                .setDescription("Unit test book")
//                .setUploadDate(LocalDate.now())
//                .build();

        review = ReviewFactory.createReview(5, "Amazing read!", LocalDate.now(), user, book);
        review = reviewService.create(review);
    }

    @Test
    @Order(1)
    void create() throws Exception {
        Review newReview = ReviewFactory.createReview(4, "Good book", LocalDate.now(), user, book);

        mockMvc.perform(
                        post("/reviews/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(newReview))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewRating").value(4))
                .andExpect(jsonPath("$.reviewComment").value("Good book"));
    }

    @Test
    @Order(2)
    void read() throws Exception {
        mockMvc.perform(get("/reviews/read/{id}", review.getReviewId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewRating").value(5))
                .andExpect(jsonPath("$.reviewComment").value("Amazing read!"));
    }

    @Test
    @Order(3)
    void update() throws Exception {
        Review updated = new Review.Builder()
                .copy(review)
                .setReviewRating(3)
                .setReviewComment("Average experience")
                .build();

        mockMvc.perform(
                        put("/reviews/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updated))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewRating").value(3))
                .andExpect(jsonPath("$.reviewComment").value("Average experience"));
    }

    @Test
    @Order(4)
    void testDelete() throws Exception {
        mockMvc.perform(delete("/reviews/delete/{id}", review.getReviewId()))
                .andExpect(status().isNoContent()); // ✅ updated to match ResponseEntity.noContent()
    }

    @Test
    @Order(5)
    void getAll() throws Exception {
        mockMvc.perform(get("/reviews/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }
}
