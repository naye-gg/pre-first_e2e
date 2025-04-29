package org.e2e.labe2e01.review.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.review.domain.Review;
import org.e2e.labe2e01.review.domain.ReviewService;
import org.e2e.labe2e01.review.infrastructure.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        Review existing_review = reviewRepository.findById(id).orElse(null);
        if (existing_review == null) {
            return ResponseEntity.notFound().build();
        }
        reviewRepository.delete(existing_review);
        return ResponseEntity.noContent().build();
    }

   /* @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Review>> getReviewsByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(reviewService.getReviewsByAuthor(authorId));
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Review>> getReviewsByRating(@PathVariable Integer rating) {
        return ResponseEntity.ok(reviewService.getReviewsByRating(rating));
    }

    */
}