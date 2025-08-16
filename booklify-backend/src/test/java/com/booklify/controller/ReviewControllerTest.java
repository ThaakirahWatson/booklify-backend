// Thaakirah Watson, 230037550
package com.booklify.controller;

import com.booklify.domain.Review;
import com.booklify.factory.ReviewFactory;
import com.booklify.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewService reviewService;

    // Use Spring’s auto-configured mapper (handles JavaTimeModule, etc.)
    @Autowired
    private ObjectMapper objectMapper;

    private Review review;

    @BeforeEach
    void setUp() {
        // Persist a fresh review and ensure it has an ID
        review = ReviewFactory.getInstance().createReview(5, "Amazing read!");
        review = reviewService.create(review);
        // Optional: re-read to ensure managed entity
        review = reviewService.read(review.getReviewId());
    }

    @Test
    void create() throws Exception {
        Review newReview = ReviewFactory.getInstance().createReview(4, "Good book");

        mockMvc.perform(
                        post("/review/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(newReview))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewRating").value(4))
                .andExpect(jsonPath("$.reviewComment").value("Good book"));
    }

    @Test
    void read() throws Exception {
        mockMvc.perform(get("/review/read/{id}", review.getReviewId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewRating").value(5))
                .andExpect(jsonPath("$.reviewComment").value("Amazing read!"));
    }

    @Test
    void update() throws Exception {
        Review updated = new Review.Builder()
                .copy(review)
                .setReviewRating(3)
                .setReviewComment("Average experience")
                .build();

        mockMvc.perform(
                        put("/review/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updated))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewRating").value(3))
                .andExpect(jsonPath("$.reviewComment").value("Average experience"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/review/delete/{id}", review.getReviewId()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/review/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }
}
