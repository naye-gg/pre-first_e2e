package org.e2e.labe2e01.review.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.review.domain.Review;
import org.e2e.labe2e01.review.domain.ReviewService;
import org.e2e.labe2e01.review.infrastructure.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        reviewRepository.delete(review);
        return ResponseEntity.noContent().build();
    }

}
