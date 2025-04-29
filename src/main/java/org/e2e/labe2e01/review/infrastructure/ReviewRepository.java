package org.e2e.labe2e01.review.infrastructure;

import org.e2e.labe2e01.review.domain.Review;
import org.e2e.labe2e01.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAuthorId(Long authorId);
    Long countByAuthorId(Long authorId);
    List<Review> findByRating(Integer rating);
}
