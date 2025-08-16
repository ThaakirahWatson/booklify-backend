// Thaakirah Watson, 230037550
package com.booklify.controller;

import com.booklify.domain.Review;
import com.booklify.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public Review create(@RequestBody Review review) {
        return reviewService.create(review);
    }

    @GetMapping("/read/{id}")
    public Review read(@PathVariable Long id) {
        return reviewService.read(id);
    }

    @PutMapping("/update")
    public Review update(@RequestBody Review review) {
        return reviewService.update(review);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return reviewService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Review> getAll() {
        return reviewService.getAll();
    }
}
